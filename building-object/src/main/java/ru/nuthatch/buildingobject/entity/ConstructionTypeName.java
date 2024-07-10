package ru.nuthatch.buildingobject.entity;

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
