package org.zerock.simpleboard.controller;

import groovy.util.logging.Log4j;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.simpleboard.dto.ReplyDTO;
import org.zerock.simpleboard.dto.list.ListReplyResponseDTO;
import org.zerock.simpleboard.dto.requestpage.PageRequestDTO;
import org.zerock.simpleboard.service.ReplyService;
import org.springframework.http.MediaType;

import java.util.Map;

@RestController
@RequestMapping("/replies/")
@AllArgsConstructor
@Log4j2
public class ReplyController {

    private ReplyService replyService;


    @GetMapping("/test")
    public String test(){
        return "HelloWorld";
    }

    @GetMapping("/rboard/{bno}")
    public ResponseEntity<ListReplyResponseDTO> getReplyList(@PathVariable("bno") Long bno, PageRequestDTO pageRequestDTO){

        ListReplyResponseDTO listReplyResponseDTO = replyService.getListByReplyBoard(bno, pageRequestDTO);

        return new ResponseEntity<>(listReplyResponseDTO,HttpStatus.OK);
    }

    @PostMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE ,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> addReply(@RequestBody ReplyDTO replyDTO){

        log.info("replydto: " + replyDTO);

        replyService.register(replyDTO);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @DeleteMapping(value ="/{rno}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> remove(@PathVariable("rno") Long rno) {

        log.info("delte ..." + rno);

        replyService.remove(rno);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }


}
