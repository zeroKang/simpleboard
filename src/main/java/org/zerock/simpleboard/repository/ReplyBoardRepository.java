package org.zerock.simpleboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.zerock.simpleboard.domain.ReplyBoard;
import org.zerock.simpleboard.domain.SimpleBoard;

public interface ReplyBoardRepository extends JpaRepository<SimpleBoard, Long>, QuerydslPredicateExecutor<ReplyBoard> {

}
