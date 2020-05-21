package org.zerock.simpleboard.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zerock.simpleboard.domain.Reply;
import org.zerock.simpleboard.domain.ReplyBoard;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReplyDTO {

    private Long rno;

    private Long bno;

    private String replyText;

    private String replyer;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    public ReplyDTO(Reply reply){

        rno = reply.getRno();
        bno = reply.getReplyBoard().getBno();
        replyText = reply.getReplyText();
        replyer = reply.getReplyer();
        regDate = reply.getRegDate();
        modDate = reply.getModDate();
    }

    @JsonIgnore
    public Reply getEntity(){
        return Reply.builder().replyText(replyText)
                .replyer(replyer)
                .replyBoard(ReplyBoard.builder().bno(bno).build())
                .build();
    }

}
