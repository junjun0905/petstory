package com.ssafy.petstory.service;

import com.ssafy.petstory.domain.Board;
import com.ssafy.petstory.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true) // 데이터의 변경이 없는 읽기 전용 메서드에 사용, 영속성 컨텍스트를 플러시 하지 않으므로 약간의 성능 향상(읽기 전용에는 다 적용)
@RequiredArgsConstructor // final, nonnull인 field를 가지고 생성자를 만들어줌
public class BoardService {

    private final BoardRepository boardRepository;


    /**
     * 게시물 생성
     */
    @Transactional // 트랜잭션, 영속성 컨텍스트 -> 영속성 컨텍스트가 자동 변경
    // 값 세팅이 끝난 후 Transactional에 의해 commit이 되고 jpa는 flush(영속성 context중 변경 내역을 찾음)를 날림 -> 변경 내역이 있을 경우 변경 감지(dirty checking)
//    public Long create(Long profileId, String title, String context) {
    public Long create(String title, String context) {

        // Entity 조회
//        Profile profile = profileRepository.findOne(profileId);

        // 해쉬태그 생성 -> 생성시 해쉬태그 중복체크
//        BoardHashtag boardHashtag = BoardHashtag.createBoardHashtag();

        // 좋아요 누른 유저 검증 및 상태유지

        // 게시물 생성
//        Board board = Board.createBoard(profile, title, context, boardHashtag);
        Board board = Board.createBoard(title, context);

        // 게시물 저장
        boardRepository.save(board);

        return board.getId();
    }

//    @Transactional
//    public Long order(Long memberId, Long itemId, int count) {
//
//        // 엔티티 조회
//        Member member = memberRepository.findOne(memberId);
//        Item item = itemRepository.findOne(itemId);
//
//        // 배송 정보 생성
//        Delivery delivery = new Delivery();
//        delivery.setAddress(member.getAddress());
//        delivery.setStatus(DeliveryStatus.READY);
//
//        // 주문 상품 생성
//        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
//
//        // 주문 생성
//        Order order = Order.createOrder(member, delivery, orderItem);
//
//        // 주문 저장
//        orderRepository.save(order);
//
//        return order.getId();
//    }

    /**
     * 게시물 읽기
     */

    /**
     * 게시물 수정
     */


    /**
     * 게시물 삭제
     */




}
