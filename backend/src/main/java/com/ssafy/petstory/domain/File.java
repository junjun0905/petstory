package com.ssafy.petstory.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "file")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // protected 생성자 생성
public class File {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String filePath;

    @Builder
    public File(Long id, String title, String filePath) {
        this.id = id;
        this.title = title;
        this.filePath = filePath;
    }
}
