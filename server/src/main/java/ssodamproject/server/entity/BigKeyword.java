package ssodamproject.server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    // 의존관계 매핑(SmallKeyword)
    @OneToMany(mappedBy = "bigKeyword", cascade = CascadeType.ALL)
    private List<SmallKeyword> smallKeywordList = new ArrayList<>();
}
