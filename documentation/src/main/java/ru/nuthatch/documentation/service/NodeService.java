package ru.nuthatch.documentation.service;

import org.springframework.stereotype.Service;
import ru.nuthatch.documentation.entity.Node;
import ru.nuthatch.documentation.repository.CommonRepository;
import ru.nuthatch.documentation.repository.NodeRepository;

@Service
public class NodeService extends CommonService<Node, NodeRepository> {

    public NodeService(CommonRepository<Node> repository) {
        super(repository);
    }
}
