package org.zerock.simpleboard.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.simpleboard.domain.QSimpleBoard;
import org.zerock.simpleboard.domain.SimpleBoard;
import org.zerock.simpleboard.dto.requestpage.SearchRequestDTO;
import org.zerock.simpleboard.dto.list.ListBoardResponseDTO;
import org.zerock.simpleboard.dto.SimpleBoardDTO;
import org.zerock.simpleboard.repository.SimpleBoardRepository;

import java.util.Arrays;
import java.util.Optional;

@Service
@Log4j2
@AllArgsConstructor
class SimpleBoardServiceImpl implements SimpleBoardService {

    private SimpleBoardRepository repository;

    @Override
    public void register(SimpleBoardDTO simpleBoardDTO) {

        SimpleBoard board = simpleBoardDTO.getEntity();

        repository.save(board);
    }

    @Override
    public SimpleBoardDTO get(Long bno) {
        Optional<SimpleBoard> result = repository.findById(bno);
        return result.isPresent() ? new SimpleBoardDTO(result.get()) : null;
    }

    @Override
    public void remove(Long bno) {

        repository.deleteById(bno);

    }

    @Override
    public void modify(SimpleBoardDTO simpleBoardDTO) {

        Optional<SimpleBoard> result = repository.findById(simpleBoardDTO.getBno());

        if(result.isPresent()){

            SimpleBoard simpleBoard = result.get();

            simpleBoard.changeTitle(simpleBoardDTO.getTitle());
            simpleBoard.changeContent(simpleBoardDTO.getContent());

            repository.save(simpleBoard);
        }


    }

    @Override
    public ListBoardResponseDTO listPage(SearchRequestDTO searchRequestDTO) {

        Pageable pageable = searchRequestDTO.getPageable(Sort.by("bno").descending());

        Page<SimpleBoard> result = repository.findAll(pageable);

        return new ListBoardResponseDTO(searchRequestDTO, result );

    }

    @Override
    public ListBoardResponseDTO listSearchPage(SearchRequestDTO searchRequestDTO) {

        String type = searchRequestDTO.getType();
        String keyword = searchRequestDTO.getKeyword();

        if(type == null || type.isEmpty() || keyword == null|| keyword.isEmpty()){
            return listPage(searchRequestDTO);
        }

        Pageable pageRequest = searchRequestDTO.getPageable(Sort.by("bno").descending());

        String[] typeArr = type.split("");

        BooleanBuilder builder = new BooleanBuilder();

        QSimpleBoard qBoard = QSimpleBoard.simpleBoard;

        BooleanBuilder innerBuilder = new BooleanBuilder();


        Arrays.stream(typeArr).forEach(word -> {
            System.out.println(word);

            if(word.equals("t")){
                BooleanExpression expression = qBoard.title.contains(keyword);
                innerBuilder.or(expression);
            }
            if(word.equals("c")){
                BooleanExpression expression = qBoard.content.contains(keyword);
                innerBuilder.or(expression);
            }
            if(word.equals("w")){
                BooleanExpression expression = qBoard.writer.contains(keyword);
                innerBuilder.or(expression);
            }
        });

        builder.and(innerBuilder);
        builder.and(qBoard.bno.gt(0L));

        Page<SimpleBoard> result = repository.findAll(builder, pageRequest);

        return new ListBoardResponseDTO(searchRequestDTO, result);
    }
}
