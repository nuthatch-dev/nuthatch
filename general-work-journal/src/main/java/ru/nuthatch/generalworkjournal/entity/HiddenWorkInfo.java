package ru.nuthatch.generalworkjournal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Описание комплексного типа: hiddenWorkInfo
 * Тип, содержащий в себе описание работы
 */
@Data
@Embeddable
public class HiddenWorkInfo {

    /**
     * Id работы
     * Необязательный элемент
     * Строгий формат:
     * хххххххх-хххх-хххх-хххх-хххххххххххх
     * Наложенные ограничения
     * [0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}
     */
    @Column(name = "work_id")
    protected UUID workId;

    /**
     * Наименование работы
     * Обязательный элемент
     * Минимум 1 символ
     */
    @Column(name = "work_name", nullable = false)
    protected String workName;

    /**
     * Структурный элемент здания, строения или сооружения
     * Необязательный элемент
     */
    @Embedded
    protected ConstructionStructureElement constructionStructureElement;

    /**
     * Методы выполнения работы
     * Необязательный элемент
     * Минимум 1 символ
     * Список
     */
    @ManyToMany
    @JoinTable(name = "hidden_work_work_method")
    protected Set<WorkMethod> workMethodSet = new HashSet<>();

}
