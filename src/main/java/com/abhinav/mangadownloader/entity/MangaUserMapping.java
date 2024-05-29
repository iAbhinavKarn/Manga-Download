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
public class MangaUserMapping {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "chapter")
    private Integer chapterNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mangaId")
    private Manga manga;
}
