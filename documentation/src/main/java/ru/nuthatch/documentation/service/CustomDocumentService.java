package ru.nuthatch.documentation.service;

import org.springframework.stereotype.Service;
import ru.nuthatch.documentation.entity.CustomDocument;
import ru.nuthatch.documentation.repository.CustomDocumentRepository;

@Service
public class CustomDocumentService extends CommonService<CustomDocument, CustomDocumentRepository> {

    public CustomDocumentService(CustomDocumentRepository repository) {
        super(repository);
    }
}
