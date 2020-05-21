package org.zerock.simpleboard.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.zerock.simpleboard.domain.Reply;
import org.zerock.simpleboard.domain.ReplyBoard;
import org.zerock.simpleboard.dto.list.ListReplyResponseDTO;
import org.zerock.simpleboard.dto.ReplyDTO;
import org.zerock.simpleboard.dto.requestpage.PageRequestDTO;
import org.zerock.simpleboard.repository.ReplyRepository;

@Service
@Log4j2
public class ReplyServiceImpl implements ReplyService{

    private ReplyRepository replyRepository;

    @Autowired
    public ReplyServiceImpl(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    @Override
    public void register(ReplyDTO replyDTO) {

        Reply reply = replyDTO.getEntity();

        replyRepository.save(reply);

    }

    @Override
    public ListReplyResponseDTO getListByReplyBoard(Long bno, PageRequestDTO pageRequestDTO) {

        ReplyBoard replyBoard = ReplyBoard.builder().bno(bno).build();
        Page<Reply> result = replyRepository.findByReplyBoard(replyBoard, pageRequestDTO.getPageable(Sort.by("rno").ascending()));

        return new ListReplyResponseDTO(result);
    }

    @Override
    public void remove(Long rno) {

        replyRepository.deleteById(rno);

    }


}
