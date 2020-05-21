package org.zerock.simpleboard.dto.list;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
@ToString
public abstract class AbstractListResponseDTO<D, EN> {

    private List<D> dtoList;

    private int page;

    private int size;

    private int totalPage;

    private int start, end;

    private boolean prev, next;

    private int prevPage, nextPage;

    private List<Integer> pageList;

    public AbstractListResponseDTO(Page<EN> result){

        dtoList = makeDTOList(result.getContent());

        makePage(result);
    }

    protected abstract List<D> makeDTOList(List<EN> entityList);

    private void makePage(Page<EN> result){
        // JPA starts num with 0
        page = result.getNumber() +1;

        size = result.getSize();

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
