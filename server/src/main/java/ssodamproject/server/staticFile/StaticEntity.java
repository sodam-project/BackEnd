package ssodamproject.server.staticFile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StaticEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long staticEntityId;

    private Integer totalVisit;
    private Integer todayVisit;

}
