package org.zerock.simpleboard.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
@Setter
@ToString
public class ListRequestDTO {

    private int page;
    private int size;

    private String type;
    private String keyword;

    public ListRequestDTO(){
        this.page = 1;
        this.size = 10;
    }
    public void setPage(int page){
        this.page = page <= 0? 1: page;
    }

    public void setSize(int size){
        this.size = size < 10? 10: size;
    }

    public Pageable getPageable(Sort sort){

        return PageRequest.of(page -1, size, sort);
    }

    public String getLink(String url, String key, Object value){

        UriComponentsBuilder uriComponents = UriComponentsBuilder.newInstance();
        uriComponents.path(url);
        uriComponents.queryParam("page", page);
        uriComponents.queryParam("size", size);
        uriComponents.queryParam("type", type);
        uriComponents.queryParam("keyword", keyword);

        if(key != null && key.isEmpty() == false) {
            uriComponents.replaceQueryParam(key, value);
        }
        return uriComponents.toUriString();

    }

}
