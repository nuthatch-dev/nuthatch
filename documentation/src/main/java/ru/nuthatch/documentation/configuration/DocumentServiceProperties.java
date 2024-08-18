package ru.nuthatch.documentation.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "document.service")
public class DocumentServiceProperties {
    /*
    Параметры конфигурации соединения с AWS S3 сервисом хранения файлов
     */
    protected String awsAccessKeyId;
    protected String awsSecretAccessKey;
    protected String awsRegion;
    protected String awsUri;
    protected String awsBucket;

}
