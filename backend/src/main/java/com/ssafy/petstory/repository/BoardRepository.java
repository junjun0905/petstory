package com.ssafy.petstory.repository;

import com.ssafy.petstory.domain.Board;
import com.ssafy.petstory.domain.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor // final, nonnull인 field를 가지고 생성자를 만들어줌
public class BoardRepository {

    private final EntityManager em;

    /**
     * 게시물 생성
     */
    public void save(Board board) {
        em.persist(board);
    }


    /**
     * 이미지 생성 테스트
     */
    public void saveImg(Image image){em.persist(image);}
}
