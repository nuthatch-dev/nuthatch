package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nuthatch.generalworkjournal.common.CommonEntity;
import ru.nuthatch.generalworkjournal.entity.representative.OrganizationWithOptionalSro;

import java.io.Serializable;
import java.util.*;

/**
 * Описание комплексного типа: designerSupervisionRepresentativesListItem
 * Лицо, осуществляющее подготовку проектной документации,
 * выполняющее проверку соответствия выполняемых работ проектной документации (авторский надзор)
 * и его представители (id)
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "designer_supervision_representatives_list_item")
public class DesignerSupervisionRepresentativesSetItem extends CommonEntity implements Serializable {

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
    @JoinTable(name = "designer_supervision_representative")
    protected Set<UUID> representativesIdsSet = new HashSet<>();

    /**
     * Лицо, осуществляющее подготовку проектной документации,
     * выполняющее проверку соответствия выполняемых работ проектной документации
     * (авторский надзор)
     * Обязательный элемент
     */
    @OneToOne(cascade = CascadeType.ALL)
    protected OrganizationWithOptionalSro organization;

    /**
     * Сведения о разделах проектной документации, подготовленных лицами,
     * осуществляющими подготовку проектной документации
     * Обязательный элемент
     * Список
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "designer_project_doc_section")
    protected Set<ProjectDocSection> projectDocSectionsSet = new HashSet<>();
}
