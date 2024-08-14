package ru.nuthatch.projectdocumentation.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Описание комплексного типа GraphicPart
 * Графическая часть проекта
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "graphic_part")
public class GraphicPart extends CommonEntity {
    /*
     Порядковый номер ревизии проекта
     */
    @ManyToOne
    protected RevisionInfo revisionInfo;
    /*
     Список ссылок на графические документы
     */
    @ElementCollection
    protected Set<UUID> attachmentList = new HashSet<>();

    /*
    Отношение к ProjectInfo
     */
    @ManyToOne
    protected ProjectInfo projectInfo;
}
