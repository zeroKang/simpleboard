package org.zerock.simpleboard.service;

import org.springframework.transaction.annotation.Transactional;
import org.zerock.simpleboard.dto.ListRequestDTO;
import org.zerock.simpleboard.dto.ListResponseDTO;
import org.zerock.simpleboard.dto.SimpleBoardDTO;

@Transactional
public interface SimpleBoardService {

    void register(SimpleBoardDTO simpleBoardDTO);

    SimpleBoardDTO get(Long bno);

    void remove(Long bno);

    void modify(SimpleBoardDTO simpleBoardDTO);

    ListResponseDTO listPage(ListRequestDTO listRequestDTO);

    ListResponseDTO listSearchPage(ListRequestDTO listRequestDTO);


}
