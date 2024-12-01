import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Coche implements Serializable {

    private final static long serialVersionUID = 1L;
    private int id;
    private String matricula;
    private String marca;
    private String modelo;
    private String color;

}
