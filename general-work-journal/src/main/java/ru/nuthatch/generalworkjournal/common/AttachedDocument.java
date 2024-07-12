package ru.nuthatch.generalworkjournal.common;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

/**
 * Описание комплексного типа: AttachedDocument
 * Информация о файле, загруженном в хранилище
 */
@Data
@Entity
@Table(name = "attached_document")
public class AttachedDocument implements Serializable {

    /**
     * Id файла в хранилище
     * Обязательный элемент
     * Строгий формат:
     * хххххххх-хххх-хххх-хххх-хххххххххххх
     * Наложенные ограничения
     * [0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}
     */
    @Id
    @GeneratedValue
    protected UUID uuid;

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
