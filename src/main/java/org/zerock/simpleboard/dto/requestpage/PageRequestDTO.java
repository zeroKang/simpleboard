package org.zerock.simpleboard.dto.requestpage;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


public class PageRequestDTO {

    protected int page;
    protected int size;

    public PageRequestDTO(){
        this.page = 1;
        this.size = 10;
    }

    public Pageable getPageable(Sort sort){
        return PageRequest.of(page -1, size, sort);
    }

    public void setPage(int page){
        this.page = page <= 0? 1: page;
    }

    public void setSize(int size){
        this.size = size < 10? 10: size;
    }

    public UriComponentsBuilder setLink(String url){

        UriComponentsBuilder uriComponents = UriComponentsBuilder.newInstance();
        uriComponents.path(url);
        uriComponents.queryParam("page", page);
        uriComponents.queryParam("size", size);

        return uriComponents;
    }

}
