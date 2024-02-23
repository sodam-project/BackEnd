package ssodamproject.server.chatbot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ssodamproject.server.chating.entity.Chatting;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Chatbot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatbotId;

    private String chatbotName;

    private Integer chatbotVisit;


    @OneToMany(mappedBy = "chatbot", cascade = CascadeType.REMOVE)
    private List<Chatting> chattings;


}
