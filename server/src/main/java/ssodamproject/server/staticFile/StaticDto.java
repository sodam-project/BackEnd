package ssodamproject.server.staticFile;

import lombok.*;

@Getter
@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaticDto {

    private Integer todayVisit;
    private Integer totalVisit;
}
