package ssodamproject.server.heart;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ssodamproject.server.chatbot.Chatbot;
import ssodamproject.server.user.entity.User;

import javax.persistence.*;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Heart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long heartId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatbot_id")
    private Chatbot chatbot;

}
