package com.abhinav.mangadownloader.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "manga")
public class Manga {

    @Id
    @GeneratedValue
    private Long id;

    private String mangaId;

    private String title;

    private String author;

    private String url;
}
