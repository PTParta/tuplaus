package tuplaus.Pelitapahtuma;

import java.util.Date;

import org.springframework.data.jpa.domain.AbstractPersistable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class Pelitapahtuma extends AbstractPersistable<Long>{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date aikaleima;

    //private String tunniste;

    private Integer panos;

    private String valinta;

    private Integer arvottuKortti;

    private Integer mahdollisenVoitonSuuruus;
}
