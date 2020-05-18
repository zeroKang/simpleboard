package org.zerock.simpleboard.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.simpleboard.domain.QSimpleBoard;
import org.zerock.simpleboard.domain.SimpleBoard;
import org.zerock.simpleboard.dto.SimpleBoardDTO;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;


@SpringBootTest
public class SimpleBoardRepositoryTests {

    @Autowired
    SimpleBoardRepository repository;

    @Test
    public void insertDummies(){

        IntStream.rangeClosed(1,100).forEach(i -> {
            SimpleBoard board = SimpleBoard.builder().title("Title..." + i).content("Content..." +i)
                    .writer("user" + (i % 10)).build();
            System.out.println(repository.save(board));

        });
    }

    @Test
    public void testRead(){

        Optional<SimpleBoard> result = repository.findById(100L);

        System.out.println(result.get());

    }

    @Test
    public void testDelete(){

        repository.deleteById(1L);
    }

    @Test
    public void testUpdate() {

        Optional<SimpleBoard> result = repository.findById(100L);

        if(result.isPresent()){

            SimpleBoard board = result.get();

            board.changeTitle("Updated Title...");

            repository.save(board);
        }
    }

    @Test
    public void testList(){

        Pageable pageRequest  = PageRequest.of(0,10, Sort.by("bno").descending());

        Page<SimpleBoard> result = repository.findAll(pageRequest);

        result.getContent().stream().forEach(simpleBoard -> {
            System.out.println(simpleBoard);
        });
    }

    @Test
    public void testSearch(){

        String type = "tc";
        String keyword = "10";

        PageRequest pageRequest = PageRequest.of(0,10, Sort.Direction.DESC, "bno");

        String[] typeArr = type.split("");

        BooleanBuilder builder = new BooleanBuilder();

        QSimpleBoard qBoard = QSimpleBoard.simpleBoard;

        BooleanBuilder innerBuilder = new BooleanBuilder();


        Arrays.stream(typeArr).forEach(word -> {
            System.out.println(word);

            if(word.equals("t")){
                BooleanExpression expression = qBoard.title.contains(keyword);
                innerBuilder.or(expression);
            }
            if(word.equals("c")){
                BooleanExpression expression = qBoard.content.contains(keyword);
                innerBuilder.or(expression);
            }
            if(word.equals("w")){
                BooleanExpression expression = qBoard.writer.contains(keyword);
                innerBuilder.or(expression);
            }
        });

        System.out.println(innerBuilder.getValue());

        builder.and(innerBuilder);
        builder.and(qBoard.bno.gt(0L));

        Page<SimpleBoard> result = repository.findAll(builder, pageRequest);

        System.out.println(result);

        result.get().forEach(board -> System.out.println(board));
    }


}
