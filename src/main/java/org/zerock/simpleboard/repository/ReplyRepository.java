package org.zerock.simpleboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.simpleboard.domain.Reply;
import org.zerock.simpleboard.domain.ReplyBoard;

public interface ReplyRepository extends JpaRepository<Reply, Long> {


    Page<Reply> findByReplyBoard(ReplyBoard replyBoard, Pageable pageable);

}
