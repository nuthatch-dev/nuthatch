package ru.nuthatch.documentation.service;

import org.springframework.stereotype.Service;
import ru.nuthatch.documentation.entity.CustomDocument;
import ru.nuthatch.documentation.repository.CommonRepository;
import ru.nuthatch.documentation.repository.CustomDocumentRepository;

@Service
public class CustomDocumentService extends CommonService<CustomDocument, CustomDocumentRepository> {

    public CustomDocumentService(CommonRepository<CustomDocument> repository) {
        super(repository);
    }
}
