package ru.nuthatch.documentation.service;

import org.springframework.stereotype.Service;
import ru.nuthatch.documentation.entity.Node;
import ru.nuthatch.documentation.repository.NodeRepository;

import java.util.Collection;
import java.util.UUID;

@Service
public class NodeService extends CommonService<Node, NodeRepository> {

    public NodeService(NodeRepository repository) {
        super(repository);
    }

    public Collection<Node> findAllByParentNodeUuid(UUID uuid) {
        return repository.findAllByParentNodeUuid(uuid).stream().sorted().toList();
    }
}
