package com.ssafy.petstory.service;

import com.ssafy.petstory.domain.File;
import com.ssafy.petstory.dto.FileDto;
import com.ssafy.petstory.repository.FileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor // -> required??
public class FileService {

    private AwsS3Service awsS3Service;
    private FileRepository fileRepository;

    public void save(FileDto fileDto) {
        fileRepository.save(fileDto.toEntity());
    }

    private FileDto convertEntityToDto(File file) {
        return FileDto.builder()
                .id(file.getId())
                .title(file.getTitle())
                .filePath(file.getFilePath())
                .imgFullPath("https://" + awsS3Service.CLOUD_FRONT_DOMAIN_NAME + "/" + file.getFilePath())
                .build();
    }


}
