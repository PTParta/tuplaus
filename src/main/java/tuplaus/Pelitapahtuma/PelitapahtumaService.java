package tuplaus.Pelitapahtuma;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuplaus.Dtos.Requests.KotiutaVoitotPyyntoDto;
import tuplaus.Dtos.Requests.PelitapahtumaPyyntoDto;
import tuplaus.Dtos.Responses.PelitapahtumaVastausDto;
import tuplaus.Laskenta.Laskenta;
import tuplaus.Pelaaja.Pelaaja;
import tuplaus.Pelaaja.PelaajaRepository;

@Service
public class PelitapahtumaService {

    @Autowired
    PelitapahtumaRepository pelitapahtumaRepository;

    @Autowired
    PelaajaRepository pelaajaRepository;

    public PelitapahtumaVastausDto luoPelitapahtuma(PelitapahtumaPyyntoDto pelitapahtumaDto){

        Laskenta laskenta = new Laskenta();
        Integer arvotunKortinSuuruus = laskenta.arvoKortinSuuruus();
        Integer mahdollisenVoitonSuuruus = 2 * pelitapahtumaDto.getPanos();

        Pelitapahtuma pelitapahtuma = new Pelitapahtuma();
        pelitapahtuma.setAikaleima(new Date());
        pelitapahtuma.setTunniste(pelitapahtumaDto.getTunniste());
        pelitapahtuma.setPanos(pelitapahtumaDto.getPanos());
        pelitapahtuma.setValinta(pelitapahtumaDto.getValinta());
        pelitapahtuma.setArvottuKortti(arvotunKortinSuuruus);
        pelitapahtuma.setMahdollisenVoitonSuuruus(mahdollisenVoitonSuuruus);

        PelitapahtumaVastausDto pelitapahtumaVastausDto = new PelitapahtumaVastausDto();
        pelitapahtumaVastausDto.setArvottuKortti(arvotunKortinSuuruus);
        pelitapahtumaVastausDto.setVoitto(true);
        pelitapahtumaVastausDto.setMahdollisenVoitonSuuruus(mahdollisenVoitonSuuruus);
        pelitapahtumaVastausDto.setPelitilinSaldo(100);

        pelitapahtumaRepository.save(pelitapahtuma);

        return pelitapahtumaVastausDto;
    }

    public void kotiutaVoitot(KotiutaVoitotPyyntoDto kotiutaVoitotPyyntoDto){

        String tunniste = kotiutaVoitotPyyntoDto.getTunniste();
        Integer voitonSuuruus = kotiutaVoitotPyyntoDto.getVoitonSuuruus();

        Pelaaja pelaaja = pelaajaRepository.findByTunniste(tunniste);
        Integer uusiSaldo = pelaaja.getSaldo() + voitonSuuruus;
        pelaaja.setSaldo(uusiSaldo);

        pelaajaRepository.save(pelaaja);
    }
}
