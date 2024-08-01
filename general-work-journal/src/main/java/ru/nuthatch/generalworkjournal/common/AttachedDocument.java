package ru.nuthatch.generalworkjournal.common;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Описание комплексного типа: AttachedDocument
 * Информация о файле, загруженном в хранилище
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "attached_document")
public class AttachedDocument extends CommonEntity implements Serializable {

    /**
     * Имя файла
     * Обязательный элемент
     * Минимум 1 символ
     */
    protected String name;

    /**
     * Описание
     * Необязательный элемент
     * Минимум 1 символ
     */
    protected String description;

    /**
     * Контрольная сумма файла
     * Необязательный элемент
     * Минимум 1 символ
     */
    protected String checksum;
}
