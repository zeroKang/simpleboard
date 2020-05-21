package org.zerock.simpleboard.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.simpleboard.domain.Reply;
import org.zerock.simpleboard.domain.ReplyBoard;
import org.zerock.simpleboard.domain.SimpleBoard;

import java.util.Arrays;
import java.util.stream.IntStream;

@SpringBootTest
public class ReplyBoardRepositoryTests {

    @Autowired
    private ReplyBoardRepository replyBoardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void insertDummies(){

        IntStream.rangeClosed(1,300).forEach(i -> {
            ReplyBoard board = ReplyBoard.builder().title("Reply Board Title..." + i).content("Content..." +i)
                    .writer("user" + (i % 10)).build();
            System.out.println(replyBoardRepository.save(board));
        });
    }

    @Test
    public void insertReply(){

        IntStream.rangeClosed(1,1000).forEach(i -> {

            long bno = (int)(Math.random() * 21) + 280;

            ReplyBoard board = ReplyBoard.builder().bno(bno).build();

            Reply reply = Reply.builder().replyBoard(board).replyText("Reply............." + bno +"...." + i)
                    .replyer("replyer" + (i % 10)).build();

            System.out.println(replyRepository.save(reply));

        });
    }

    @Test
    public void testSelectReplyBoard(){

        PageRequest pageRequest = PageRequest.of(0,10, Sort.by("bno").descending());

        Page<ReplyBoard> result = replyBoardRepository.findAll(pageRequest);

        System.out.println(result);

        result.getContent().stream().forEach(replyBoard -> {
            System.out.println(replyBoard);
        });
    }

    @Test
    public void testSelectReplyBoardWithCount(){

        PageRequest pageRequest = PageRequest.of(0,10, Sort.by("bno").descending());

        Page<Object[]> result = replyBoardRepository.getListWithReplyCount(pageRequest);

        System.out.println(result);

        result.getContent().stream().forEach(arr -> {
            System.out.println(Arrays.toString(arr));
        });

    }

    @Test
    public void testReplyPage(){

        ReplyBoard replyBoard = ReplyBoard.builder().bno(300L).build();

        Pageable pageable = PageRequest.of(0,10);

        Page<Reply> result = replyRepository.findByReplyBoard(replyBoard, pageable);

        System.out.println(result);

        result.getContent().forEach(reply -> {
            System.out.println(reply);
            System.out.println(reply.getReplyBoard());
        });

    }




















}
