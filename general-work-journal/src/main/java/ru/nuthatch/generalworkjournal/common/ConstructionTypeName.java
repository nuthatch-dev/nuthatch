package ru.nuthatch.generalworkjournal.common;

/**
 * Наименование вида строительства
 * Наложенные ограничения –только одно из значений:
 * - строительство
 * - реконструкция
 * - капитальный ремонт
 */
public enum ConstructionTypeName {
    BUILDING,
    RECONSTRUCTION,
    PERMANENT_REBUILD
}
