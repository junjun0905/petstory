package com.ssafy.petstory.controller;

import com.ssafy.petstory.dto.FileDto;
import com.ssafy.petstory.service.AwsS3Service;
import com.ssafy.petstory.service.BoardService;
import com.ssafy.petstory.service.FileService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor // final, nonnull인 field를 가지고 생성자를 만들어줌
public class BoardApiController {

    private final BoardService boardService;
    private final FileService fileService;
    private final AwsS3Service awsS3Service;

    /**
     * 이미지 생성 테스트
     * -> db에 넣어보기
     */
    @PostMapping("/api/board/file")
//    public HttpStatus fileUpload(FileDto fileDto, List<MultipartFile> files) throws IOException{
    public HttpStatus fileUpload(FileDto fileDto, MultipartFile file) throws IOException{
        System.out.println("=======================================================");
        String imgPath = awsS3Service.upload(file); // dto아래서 빼서 넣을라면 반복문 코드 서비스에서 빼와야됨, 아니면 서비스로 가든가
//        String imgPath = awsS3Service.upload(files); // dto아래서 빼서 넣을라면 반복문 코드 서비스에서 빼와야됨, 아니면 서비스로 가든가
        fileDto.setFilePath(imgPath);

        fileService.save(fileDto);

        return HttpStatus.OK;

    }

    /**
     * 게시물 조회
     */
    @GetMapping("/api/board/")
    /**
     * findAll, findOne -> 무한 스크롤 로직에 맞춰서 -> 일단, Paging 처리같음 -> FE에 맞춰서 비즈니스 로직 설계
     */


    /**
     * 게시물 생성
     */
    @PostMapping("/api/board/create")
    // @RequestBody : JSON으로 온 body를 Board로 Mapping해서 넣어줌
//    public CreateBoardResponse createBoard(@RequestParam("profileId") Long profileId, @RequestBody @Valid CreateBoardRequest request) {
    public CreateBoardResponse createBoard(@RequestBody @Valid CreateBoardRequest request) {

//        Long id = boardService.create(profileId, request.title, request.context);
        Long id = boardService.create(request.title, request.context);

        return new CreateBoardResponse(id);
    }

    @Data
    static class CreateBoardRequest {
        private String title;
        private String context;

//        private String image;
    }

    @Data
    static class CreateBoardResponse {
        private Long id;

        public CreateBoardResponse(Long id) {
            this.id = id;
        }
    }




}
