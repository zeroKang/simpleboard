package org.zerock.simpleboard.service;

import org.springframework.transaction.annotation.Transactional;
import org.zerock.simpleboard.dto.requestpage.SearchRequestDTO;
import org.zerock.simpleboard.dto.list.ListBoardResponseDTO;
import org.zerock.simpleboard.dto.SimpleBoardDTO;

@Transactional
public interface SimpleBoardService {

    void register(SimpleBoardDTO simpleBoardDTO);

    SimpleBoardDTO get(Long bno);

    void remove(Long bno);

    void modify(SimpleBoardDTO simpleBoardDTO);

    ListBoardResponseDTO listPage(SearchRequestDTO searchRequestDTO);

    ListBoardResponseDTO listSearchPage(SearchRequestDTO searchRequestDTO);


}
