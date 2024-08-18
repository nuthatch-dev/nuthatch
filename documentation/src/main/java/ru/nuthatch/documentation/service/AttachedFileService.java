package ru.nuthatch.documentation.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.nuthatch.documentation.configuration.DocumentServiceProperties;
import ru.nuthatch.documentation.entity.AttachedFile;
import ru.nuthatch.documentation.repository.AttachedFileRepository;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

@Service
public class AttachedFileService extends CommonService<AttachedFile, AttachedFileRepository> {

    private final S3Client s3Client;
    private final DocumentServiceProperties properties;

    public AttachedFileService(AttachedFileRepository repository,
                               S3Client s3Client,
                               DocumentServiceProperties properties) {
        super(repository);
        this.s3Client = s3Client;
        this.properties = properties;
    }

    public AttachedFile upload(MultipartFile file) throws IOException {
        String BUCKET = properties.getAwsBucket();
        String keyName = file.getOriginalFilename();
        byte[] data = file.getBytes();
        Checksum crc32 = new CRC32();
        crc32.update(data, 0, data.length);
        String checksum = String.valueOf(crc32.getValue());

        // Upload file
        s3Client.putObject(PutObjectRequest
                        .builder()
                        .bucket(BUCKET)
                        .key(keyName)
                        .checksumCRC32(checksum)
                        .build(),
                RequestBody.fromBytes(data));

        // Get uploaded file attributes
        GetUrlRequest request = GetUrlRequest
                .builder()
                .bucket(BUCKET)
                .key(keyName)
                .build();
        AttachedFile attachedFile = new AttachedFile();
        attachedFile.setName(s3Client.utilities().getUrl(request).toExternalForm());
        attachedFile.setUuid(UUID.randomUUID());
        attachedFile.setChecksum(checksum);

        return attachedFile;
    }
}
