package org.zerock.simpleboard.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zerock.simpleboard.domain.SimpleBoard;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
@ToString
public class ListResponseDTO {

    private List<SimpleBoardDTO> boardList;

    private Pageable pageInfo;

    private int page;

    private int size;

    private int totalPage;

    private int start, end;

    private boolean prev, next;

    private int prevPage, nextPage;

    private List<Integer> pageList;

    public ListResponseDTO(ListRequestDTO listRequestDTO, Page<SimpleBoard> result){

        boardList = result.getContent().stream().map(board -> new SimpleBoardDTO(board)).collect(Collectors.toList());

        pageInfo = result.getPageable();

        // JPA start num from 0
        page = listRequestDTO.getPage();

        size = listRequestDTO.getSize();

        totalPage = result.getTotalPages();

        //temp end page
        int tempEnd = (int)(Math.ceil(page/10.0)) * 10;

        start = tempEnd - 9;

        prev = start > 1;

        if(prev) { prevPage = start -1; }

        end = totalPage > tempEnd ? tempEnd: totalPage;

        next = totalPage > tempEnd;

        if(next) { nextPage = tempEnd + 1; }

        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }
}

