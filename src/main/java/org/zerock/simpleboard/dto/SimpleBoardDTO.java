package org.zerock.simpleboard.dto;

import lombok.*;
import org.zerock.simpleboard.domain.SimpleBoard;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SimpleBoardDTO {

    private Long bno;

    private String title;

    private String content;

    private String writer;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    private String regDateStr;

    private String modDateStr;

    public SimpleBoardDTO(SimpleBoard board){
        this.bno = board.getBno();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writer = board.getWriter();
        this.regDate = board.getRegDate();
        this.modDate = board.getModDate();

        this.regDateStr = regDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        this.modDateStr = modDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public SimpleBoard getEntity(){
        return SimpleBoard.builder()
                .bno(this.bno)
                .title(this.title)
                .content(this.content)
                .writer(this.writer)
                .build();
    }
}
