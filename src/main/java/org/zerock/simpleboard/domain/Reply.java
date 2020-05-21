package org.zerock.simpleboard.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "replyBoard")
@Table(indexes = {@Index(name="idx_reply_board", columnList = "reply_board_bno")})
public class Reply extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String replyText;

    private String replyer;

    @ManyToOne
    private ReplyBoard replyBoard;


}
