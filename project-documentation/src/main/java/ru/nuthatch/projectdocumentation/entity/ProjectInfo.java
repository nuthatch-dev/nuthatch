package ru.nuthatch.projectdocumentation.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.NamedNativeQuery;

import java.util.UUID;

/**
 * Описание комплексного типа ProjectInfo
 * Данные по проекту
 */
@NamedNativeQuery(name = "ProjectInfo.findAllGraphicPart_Named",
        query = "SELECT * FROM graphic_part AS g WHERE g.project_info_uuid = :uuid",
        resultClass = GraphicPart.class)
@NamedNativeQuery(name = "ProjectInfo.findAllVolumeSheet_Named",
        query = "SELECT * FROM volume_sheet AS v WHERE v.project_info_uuid = :uuid",
        resultClass = VolumeSheet.class)
@NamedNativeQuery(name = "ProjectInfo.findAllSpecification_Named",
        query = "SELECT * FROM specification AS s WHERE s.project_info_uuid = :uuid",
        resultClass = Specification.class)
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "project_info")
public class ProjectInfo extends CommonEntity {
    /*
     Ссылка на объект строительства
     */
    protected UUID buildingObject;

    /*
     Наименование раздела проекта
     */
    protected String title;

    /*
     Шифр проекта
     */
    protected String code;

    /*
     Главный инженер проекта
     */
    @Embedded
    protected Person chiefEngineer;

    /*
     Графическая часть проекта
     Named native query graphicPartSet
     */

    /*
     Ведомости объемов работ
     Named native query volumeSheetSet
     */

    /*
     Спецификации проекта
     Named native query specificationSet
     */

}
