package ssodamproject.server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "small_keyword")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SmallKeyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long smallKeywordId;

    private String smallKeywordName;
    private Boolean isAnalyzed;
    private Integer weight;

    // 의존 관계 매핑 (BigKeyword)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "big_keyword_id")
    private BigKeyword bigKeyword;
}
