package org.zerock.simpleboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.zerock.simpleboard.domain.ReplyBoard;


public interface ReplyBoardRepository extends JpaRepository<ReplyBoard, Long>, QuerydslPredicateExecutor<ReplyBoard> {


    @Query("select b , count(r) from ReplyBoard b left outer join Reply r on r.replyBoard = b group by b")
    Page<Object[]> getListWithReplyCount(Pageable pageable);
}
