package ssodamproject.server.chating.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ssodamproject.server.chatbot.entity.Chatbot;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Chatting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chattingId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatbot_id")
    Chatbot chatbot;

    private String chattingContent;

    @Column(length = 100000)
    private Integer chattingEvaluation;
}
