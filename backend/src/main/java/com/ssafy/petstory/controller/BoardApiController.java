package com.ssafy.petstory.controller;

import com.ssafy.petstory.domain.Board;
import com.ssafy.petstory.domain.File;
import com.ssafy.petstory.dto.FileDto;
import com.ssafy.petstory.repository.BoardRepository;
import com.ssafy.petstory.service.AwsS3Service;
import com.ssafy.petstory.service.BoardService;
import com.ssafy.petstory.service.FileService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor // final, nonnull인 field를 가지고 생성자를 만들어줌
public class BoardApiController {

    private final BoardService boardService;
    private final FileService fileService;
    private final AwsS3Service awsS3Service;
    private final BoardRepository boardRepository;


    /**
     * 이미지 생성 테스트
     * -> db에 넣어보기
     */
//    @PostMapping("/api/board/file")
////    public HttpStatus fileUpload(FileDto fileDto, List<MultipartFile> files) throws IOException{
//    public HttpStatus fileUpload(FileDto fileDto, MultipartFile file) throws IOException{
//        System.out.println("=======================================================");
//        String imgPath = awsS3Service.upload(file); // dto아래서 빼서 넣을라면 반복문 코드 서비스에서 빼와야됨, 아니면 서비스로 가든가
////        String imgPath = awsS3Service.upload(files); // dto아래서 빼서 넣을라면 반복문 코드 서비스에서 빼와야됨, 아니면 서비스로 가든가
//        fileDto.setFilePath(imgPath);
//
//        fileService.save(fileDto);
//
//        return HttpStatus.OK;
//
//    }

    /**
     * 게시물 전체 조회
     * 1. 무한 스크롤 (페이징 처리) -> 아직 안 함 (V5 고민중)
     * 일단 v3 시도
     */
    @GetMapping("/api/board/findAll")
    public List<BoardDto> fildAllV3() {
//        List<> galleryDtoList = galleryService.getList();
//        model.addAttribute("galleryList", galleryDtoList);
//        return "/gallery";

        List<Board> boards = boardRepository.findAll();
        List<BoardDto> result = boards.stream()
                .map(b -> new BoardDto(b))
                .collect(Collectors.toList());
        return result;
    }

    /**
     * 게시물 생성
     */
    @PostMapping("/api/board/create")
    // @RequestBody : JSON으로 온 body를 Board로 Mapping해서 넣어줌
//    public CreateBoardResponse createBoard(@RequestParam("profileId") Long profileId, @RequestBody @Valid CreateBoardRequest request) {
    public CreateBoardResponse createBoard(CreateBoardRequest request, MultipartFile file) throws IOException {

//        Long id = boardService.create(profileId, request.title, request.context);

        FileDto fileDto = new FileDto();
        Long id = boardService.create(request.title, request.context, file, fileDto);

        return new CreateBoardResponse(id);
    }

    @Data
    static class CreateBoardRequest {
        private String title;
        private String context;

    }

    @Data
    static class CreateBoardResponse {
        private Long id;

        public CreateBoardResponse(Long id) {
            this.id = id;
        }
    }


    @Data
    private class BoardDto {

        private Long boardId;
        private String title;
        private String context;
        private LocalDateTime boardDate; // 글생성 시간
        private File file; // 사진 여러장 -> 리스트로 바꿔야함
//        private long likeNum;
//        private long reportNum;
//        private List<BoardHashtag> boardHashtags;
//        private List<Comment> comments;

        public BoardDto(Board board) {
            boardId = board.getId();
            title = board.getTitle();
            context = board.getContext();
            boardDate = board.getBoardDate();
            file = board.getFile();
        }
    }

}
