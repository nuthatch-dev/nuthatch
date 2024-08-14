package ru.nuthatch.projectdocumentation.service;

import org.springframework.stereotype.Service;
import ru.nuthatch.projectdocumentation.entity.GraphicPart;
import ru.nuthatch.projectdocumentation.repository.GraphicPartRepository;

@Service
public class GraphicPartService extends CommonService<GraphicPart, GraphicPartRepository> {

    protected GraphicPartService(GraphicPartRepository repository) {
        super(repository);
    }
}
