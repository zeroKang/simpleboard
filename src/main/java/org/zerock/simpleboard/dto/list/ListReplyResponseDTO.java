package org.zerock.simpleboard.dto.list;

import lombok.Getter;
import org.springframework.data.domain.Page;
import org.zerock.simpleboard.domain.Reply;
import org.zerock.simpleboard.dto.ReplyDTO;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ListReplyResponseDTO extends AbstractListResponseDTO<ReplyDTO, Reply>{


    public ListReplyResponseDTO(Page<Reply> result) {
        super(result);
    }

    @Override
    protected List<ReplyDTO> makeDTOList(List<Reply> entityList) {
        return entityList.stream().map(reply -> new ReplyDTO(reply)).collect(Collectors.toList());
    }
}
