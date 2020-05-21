package org.zerock.simpleboard.dto.list;

import lombok.Getter;
import org.springframework.data.domain.Page;
import org.zerock.simpleboard.domain.SimpleBoard;
import org.zerock.simpleboard.dto.requestpage.SearchRequestDTO;
import org.zerock.simpleboard.dto.SimpleBoardDTO;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ListBoardResponseDTO extends AbstractListResponseDTO<SimpleBoardDTO, SimpleBoard>{

    private String type;
    private String keyword;

    public ListBoardResponseDTO(SearchRequestDTO searchRequestDTO, Page<SimpleBoard> result){

        super(result);

        type = searchRequestDTO.getType();

        keyword = searchRequestDTO.getKeyword();

    }


    @Override
    protected List<SimpleBoardDTO> makeDTOList(List<SimpleBoard> entityList) {
        return entityList.stream().map(simpleBoard -> new SimpleBoardDTO(simpleBoard)).collect(Collectors.toList());
    }
}

