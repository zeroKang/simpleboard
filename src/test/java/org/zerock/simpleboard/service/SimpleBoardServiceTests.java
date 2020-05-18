package org.zerock.simpleboard.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.SmartFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.simpleboard.dto.ListRequestDTO;
import org.zerock.simpleboard.dto.ListResponseDTO;
import org.zerock.simpleboard.dto.SimpleBoardDTO;

import java.util.List;

@SpringBootTest
public class SimpleBoardServiceTests {

    @Autowired
    private SimpleBoardService service;

    @Test
    public void testRegister(){

        SimpleBoardDTO simpleBoardDTO = SimpleBoardDTO.builder().title("Test Title")
                .content("Test Content......")
                .writer("user11")
                .build();

        service.register(simpleBoardDTO);
    }

    @Test
    public void testGet(){

        Long bno = 100L;

        SimpleBoardDTO simpleBoardDTO = service.get(bno);

        System.out.println(simpleBoardDTO);
    }

    @Test
    public void testDelete(){

        Long bno = 2L;

        service.remove(bno);

    }

    @Test
    public void testeModify(){

        SimpleBoardDTO dto = SimpleBoardDTO.builder()
                .bno(101L)
                .title("Changed Title")
                .content("Changed Content")
                .build();
        service.modify(dto);
    }


    @Test
    public void testList1(){

        ListRequestDTO listRequestDTO = new ListRequestDTO();

        listRequestDTO.setPage(1);
        listRequestDTO.setSize(10);

        ListResponseDTO responseDTO = service.listPage(listRequestDTO);

        System.out.println(responseDTO);

        responseDTO.getBoardList().stream().forEach(simpleBoardDTO -> {
            System.out.println(simpleBoardDTO);
        });

        System.out.println(responseDTO.getPageList());
    }

    @Test
    public void testSearch(){

        ListRequestDTO listRequestDTO = new ListRequestDTO();

        listRequestDTO.setPage(1);
        listRequestDTO.setSize(10);
        listRequestDTO.setType("tc");
        listRequestDTO.setKeyword("10");

        ListResponseDTO responseDTO = service.listSearchPage(listRequestDTO);

        System.out.println(responseDTO);

        responseDTO.getBoardList().stream().forEach(simpleBoardDTO -> {
            System.out.println(simpleBoardDTO);
        });

        System.out.println(responseDTO.getPageList());
    }


}












