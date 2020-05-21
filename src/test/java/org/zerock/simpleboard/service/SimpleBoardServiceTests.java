package org.zerock.simpleboard.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.simpleboard.dto.requestpage.SearchRequestDTO;
import org.zerock.simpleboard.dto.list.ListBoardResponseDTO;
import org.zerock.simpleboard.dto.SimpleBoardDTO;

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

        SearchRequestDTO searchRequestDTO = new SearchRequestDTO();

        searchRequestDTO.setPage(1);
        searchRequestDTO.setSize(10);

        ListBoardResponseDTO responseDTO = service.listPage(searchRequestDTO);

        System.out.println(responseDTO);

        responseDTO.getDtoList().stream().forEach(simpleBoardDTO -> {
            System.out.println(simpleBoardDTO);
        });

        System.out.println(responseDTO.getPageList());
    }

    @Test
    public void testSearch(){

        SearchRequestDTO searchRequestDTO = new SearchRequestDTO();

        searchRequestDTO.setPage(1);
        searchRequestDTO.setSize(10);
        searchRequestDTO.setType("tc");
        searchRequestDTO.setKeyword("10");

        ListBoardResponseDTO responseDTO = service.listSearchPage(searchRequestDTO);

        System.out.println(responseDTO);

        responseDTO.getDtoList().stream().forEach(simpleBoardDTO -> {
            System.out.println(simpleBoardDTO);
        });

        System.out.println(responseDTO.getPageList());
    }


}












