package tuplaus.Pelitapahtuma;

import org.springframework.data.jpa.domain.AbstractPersistable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pelitapahtuma extends AbstractPersistable<Long>{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String tunniste;

    private Integer panos;

    //TODO enum
    private String valinta;

    private Integer arvottuKortti;

    private Integer voitonSuuruus;
}
