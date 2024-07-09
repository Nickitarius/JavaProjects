package continentsManager.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    private String code;
    private String name;
    private String continent;
    private Double surfaceArea;
    private Integer population;
}
