package ssodamproject.server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "big_keyword")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BigKeyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bigKeywordId;

    private String bigKeywordName;
}
