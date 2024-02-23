package ssodamproject.server.staticFile;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class StaticEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long staticEntityId;

    private Integer totalVisit;
    private Integer todayVisit;

}
