package ru.nuthatch.documentation.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Описание комплексного типа: attachedFile
 * Информация о файле, загруженном в хранилище
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "attached_file")
public class AttachedFile extends CommonEntity {
    /*
    Имя файла
    Обязательный элемент
    Минимум 1 символ
     */
    protected String name;

    /*
    Описание
    Необязательный элемент
    Минимум 1 символ
     */
    protected String description;

    /*
    Контрольная сумма файла
    Необязательный элемент
    Минимум 1 символ
     */
    protected String checksum;

    /*
    Имена миниатюр отдельных листов документа
     */
    @ElementCollection
    @CollectionTable(name = "attached_file_thumbnail",
            joinColumns = @JoinColumn(name = "attached_file_uuid"))
    @OrderColumn // order of list is persistent
    @Column(name = "thumbnail")
    protected List<String> thumbnails = new ArrayList<>();

}
