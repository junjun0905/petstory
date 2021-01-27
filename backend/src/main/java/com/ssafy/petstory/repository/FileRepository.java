package com.ssafy.petstory.repository;

import com.ssafy.petstory.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {

//    @Override
//    List<File> findAllFiles();
}
