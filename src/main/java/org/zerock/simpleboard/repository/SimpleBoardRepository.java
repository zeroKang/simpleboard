package org.zerock.simpleboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.zerock.simpleboard.domain.SimpleBoard;
import org.zerock.simpleboard.dto.SimpleBoardDTO;

public interface SimpleBoardRepository extends JpaRepository<SimpleBoard, Long> , QuerydslPredicateExecutor<SimpleBoard> {

}
