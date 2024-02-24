package ssodamproject.server.user.entity;

import lombok.*;
import ssodamproject.server.heart.Heart;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userIp;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Heart> hearts;
}
