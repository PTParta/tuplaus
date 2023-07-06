package tuplaus.Pelitapahtuma;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuplaus.Dtos.PelitapahtumaPyyntoDto;

@Service
public class PelitapahtumaService {
    
    @Autowired
    PelitapahtumaRepository pelitapahtumaRepository;

    public void luoPelitapahtuma(PelitapahtumaPyyntoDto pelitapahtumaDto){

        Pelitapahtuma pelitapahtuma = new Pelitapahtuma();
        pelitapahtuma.setAikaleima(new Date());
        pelitapahtuma.setTunniste(pelitapahtumaDto.getTunniste());
        pelitapahtuma.setPanos(pelitapahtumaDto.getPanos());
        pelitapahtuma.setValinta(pelitapahtumaDto.getValinta());
        pelitapahtuma.setArvottuKortti(2);
        pelitapahtuma.setMahdollisenVoitonSuuruus(2 * pelitapahtumaDto.getPanos());

        pelitapahtumaRepository.save(pelitapahtuma);
    }
}
