package org.zerock.simpleboard.service;

import org.springframework.data.domain.Pageable;
import org.zerock.simpleboard.dto.list.ListReplyResponseDTO;
import org.zerock.simpleboard.dto.ReplyDTO;
import org.zerock.simpleboard.dto.requestpage.PageRequestDTO;

public interface ReplyService {

    void register(ReplyDTO replyDTO);

    ListReplyResponseDTO getListByReplyBoard(Long bno, PageRequestDTO pageRequestDTO);

    void remove(Long rno);

}
