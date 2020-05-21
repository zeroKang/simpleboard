package org.zerock.simpleboard.dto.requestpage;

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
public class SearchRequestDTO extends PageRequestDTO {

    private String type;
    private String keyword;



    public String getLink(String url, String key, Object value){

        UriComponentsBuilder uriComponents = setLink(url);
        uriComponents.queryParam("type", type);
        uriComponents.queryParam("keyword", keyword);

        if(key != null && key.isEmpty() == false) {
            uriComponents.replaceQueryParam(key, value);
        }
        return uriComponents.toUriString();
    }

}
