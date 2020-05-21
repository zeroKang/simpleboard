package org.zerock.simpleboard.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.zerock.simpleboard.dto.ReplyDTO;
import org.zerock.simpleboard.dto.list.ListReplyResponseDTO;

@SpringBootTest
public class ReplyServiceTests {

    @Autowired
    private ReplyService replyService;

    @Test
    public void testRegister(){
        ReplyDTO dto = ReplyDTO.builder().bno(300L).replyText("Test Reply").replyer("replyer1").build();
        replyService.register(dto);
    }

    @Test
    public void testReplyListOfReplyBoard(){

        Pageable pageable = PageRequest.of(0,20);

        ListReplyResponseDTO listReplyResponseDTO = replyService.getListByReplyBoard(300L, pageable);

        System.out.println(listReplyResponseDTO);

        for (ReplyDTO replyDTO : listReplyResponseDTO.getDtoList()) {
            System.out.println(replyDTO);
        }
    }

    @Test
    public void testRemoveReply(){

        long rno = 456L;

        replyService.remove(rno);

    }



}
