package com.ssafy.petstory.dto;

import com.ssafy.petstory.domain.File;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FileDto {

    private Long id;
    private String title;
    private String filePath;
    private String imgFullPath;

    public File toEntity() {
        File build = File.builder()
                .id(id)
                .title(title)
                .filePath(filePath)
                .build();
        return build;
    }

    @Builder
    public FileDto(Long id, String title, String filePath, String imgFullPath) {
        this.id = id;
        this.title = title;
        this.filePath = filePath;
        this.imgFullPath = imgFullPath;
    }
}