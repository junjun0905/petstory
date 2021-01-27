package com.ssafy.petstory.controller;

import com.ssafy.petstory.service.BoardService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor // final, nonnull인 field를 가지고 생성자를 만들어줌
public class BoardApiController {

    private final BoardService boardService;


    /**
     * 이미지 생성 테스트
     * -> db에 넣어보기
     */
//    @PostMapping("/api/board/img")
//    public Long creatImage(){
//
//    }


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
