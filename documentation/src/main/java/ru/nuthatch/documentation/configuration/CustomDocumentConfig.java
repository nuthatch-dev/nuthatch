package ru.nuthatch.documentation.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

@Configuration
public class CustomDocumentConfig {

    private final DocumentServiceProperties properties;

    @Autowired
    public CustomDocumentConfig(DocumentServiceProperties properties) {
        this.properties = properties;
    }

    @Bean
    public S3Client client() {
        String ACCESS_KEY_ID = properties.getAwsAccessKeyId();
        String SECRET_ACCESS_KEY = properties.getAwsSecretAccessKey();
        String REGION = properties.getAwsRegion();
        String AWS_URI = properties.getAwsUri();

        AwsCredentials CREDENTIALS = AwsBasicCredentials.create(ACCESS_KEY_ID, SECRET_ACCESS_KEY);
        Region region = Region.of(REGION);

        return S3Client
                .builder()
                .httpClientBuilder(ApacheHttpClient.builder())
                .region(region)
                .credentialsProvider(StaticCredentialsProvider.create(CREDENTIALS))
                .endpointOverride(URI.create(AWS_URI))
                .build();
    }
}
