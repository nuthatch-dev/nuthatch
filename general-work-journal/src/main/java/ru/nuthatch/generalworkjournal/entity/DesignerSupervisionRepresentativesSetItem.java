package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.*;
import lombok.Data;
import ru.nuthatch.generalworkjournal.common.OrganizationWithOptionalSroAndId;

import java.io.Serializable;
import java.util.*;

/**
 * Описание комплексного типа: designerSupervisionRepresentativesListItem
 * Лицо, осуществляющее подготовку проектной документации,
 * выполняющее проверку соответствия выполняемых работ проектной документации (авторский надзор)
 * и его представители (id)
 */
@Data
@Entity
@Table(name = "designer_supervision_representatives_list_item")
public class DesignerSupervisionRepresentativesSetItem implements Serializable {

    @Id
    @GeneratedValue
    protected UUID uuid;

    /**
     * Уполномоченные представители лица, осуществляющего подготовку проектной документации,
     * выполняющего проверку соответствия выполняемых работ проектной документации (id)
     * Обязательный элемент
     * Строгий формат:
     * _хххххххх-хххх-хххx-хххx-хххххххххххх
     * Наложенные ограничения
     * _[0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}
     * Список
     */
    @ElementCollection
    @Column(name = "representatives_ids_set")
    protected Set<UUID> representativesIdsSet = new HashSet<>();

    /**
     * Лицо, осуществляющее подготовку проектной документации,
     * выполняющее проверку соответствия выполняемых работ проектной документации
     * (авторский надзор)
     * Обязательный элемент
     */
    @ManyToOne
    protected OrganizationWithOptionalSroAndId organization;

    /**
     * Сведения о разделах проектной документации, подготовленных лицами,
     * осуществляющими подготовку проектной документации
     * Обязательный элемент
     * Список
     */
    @ManyToMany(fetch = FetchType.LAZY)
    protected Set<ProjectDocSection> projectDocSectionsSet = new HashSet<>();
}
