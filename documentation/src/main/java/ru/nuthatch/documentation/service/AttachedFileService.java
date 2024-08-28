package ru.nuthatch.documentation.service;

import jakarta.transaction.Transactional;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.nuthatch.documentation.configuration.DocumentServiceProperties;
import ru.nuthatch.documentation.entity.AttachedFile;
import ru.nuthatch.documentation.repository.AttachedFileRepository;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

@Service
public class AttachedFileService extends CommonService<AttachedFile, AttachedFileRepository> {

    private final S3Client s3Client;
    private final DocumentServiceProperties properties;
    private final float BASE_THUMBNAIL_DPI = 72f;
    private final String BASE_THUMBNAIL_FORMAT = "png";
    private final String BASE_DOCUMENTS_FOLDER = "documents/";
    private final String BASE_THUMBNAILS_FOLDER = "thumbnails/";

    public AttachedFileService(AttachedFileRepository repository,
                               S3Client s3Client,
                               DocumentServiceProperties properties) {
        super(repository);
        this.s3Client = s3Client;
        this.properties = properties;
    }

    @Transactional(rollbackOn = {IOException.class, AwsServiceException.class})
    public AttachedFile upload(MultipartFile file) throws IOException {

        String keyName = getFileKey(file.getOriginalFilename());
        byte[] data = file.getBytes();
        Checksum crc32 = new CRC32();
        crc32.update(data, 0, data.length);
        String checksum = String.valueOf(crc32.getValue());

        // Main file
        uploadData(data, BASE_DOCUMENTS_FOLDER + keyName);
        AttachedFile attachedFile = new AttachedFile();
        attachedFile.setName(keyName);
        attachedFile.setUuid(UUID.randomUUID());
        attachedFile.setChecksum(checksum);

        // Thumbnails
        Map<Integer, byte[]> thumbnailsMap = getThumbnailMap(data);
        thumbnailsMap.entrySet().forEach(entry -> {
            uploadData(entry.getValue(), BASE_THUMBNAILS_FOLDER + keyName + "/"
                        + entry.getKey() + "." + BASE_THUMBNAIL_FORMAT);
            attachedFile.getThumbnails().add(entry.getKey() + "." + BASE_THUMBNAIL_FORMAT);
        });

        return repository.save(attachedFile);
    }

    /**
     * Получить миниатюры для переданного PDF файла документа
     *
     * @param input переданный массив байт {@code byte[]} исходного документа
     * @return список с массивами байт миниатюр страниц документа
     * @throws IOException ошибки при загрузке
     */
    private Map<Integer, byte[]> getThumbnailMap(byte[] input) throws IOException {
        Map<Integer, byte[]> thumbnails = new HashMap<>();
        try (PDDocument document = Loader.loadPDF(input)) {
            PDFRenderer renderer = new PDFRenderer(document);
            for (int page = 0; page < document.getNumberOfPages(); ++page) {
                BufferedImage image = renderer.renderImageWithDPI(page, BASE_THUMBNAIL_DPI, ImageType.RGB);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ImageIO.write(image, BASE_THUMBNAIL_FORMAT, byteArrayOutputStream);
                thumbnails.put(page, byteArrayOutputStream.toByteArray());
            }
        }
        return thumbnails;
    }

    /**
     * Получение уникального имени для сохраняемого файла
     *
     * @param name исходное имя файла
     * @return MD5 хеш имени и даты загрузки
     */
    private String getFileKey(String name) {
        return DigestUtils.md5Hex(name + new Date());
    }

    /**
     * Upload data
     *
     * @param data массив байт {@code byte[]} выгружаемого файла
     * @param keyName имя
     */
    private void uploadData(byte[] data, String keyName) {
        String BUCKET = properties.getAwsBucket();
        // Upload file
        s3Client.putObject(PutObjectRequest
                        .builder()
                        .bucket(BUCKET)
                        .key(keyName)
                        .build(),
                RequestBody.fromBytes(data));
    }
}
