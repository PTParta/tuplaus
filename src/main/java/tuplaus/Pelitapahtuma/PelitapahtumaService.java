package tuplaus.Pelitapahtuma;

import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuplaus.Dtos.Requests.KotiutaVoitotPyyntoDto;
import tuplaus.Dtos.Requests.PelitapahtumaPyyntoDto;
import tuplaus.Dtos.Responses.PelitapahtumaVastausDto;
import tuplaus.Exceptions.PelaajaaEiLoydyException;
import tuplaus.Exceptions.SaldoEiRiitaException;
import tuplaus.Laskenta.Laskenta;
import tuplaus.Pelaaja.Pelaaja;
import tuplaus.Pelaaja.PelaajaRepository;

@Service
public class PelitapahtumaService {

    private static Integer voittokerroin = 2;

    @Autowired
    PelitapahtumaRepository pelitapahtumaRepository;

    @Autowired
    PelaajaRepository pelaajaRepository;

    public PelitapahtumaVastausDto luoPelitapahtuma(PelitapahtumaPyyntoDto pelitapahtumaDto)
            throws SaldoEiRiitaException, PelaajaaEiLoydyException {

        onkoPelaajaOlemassa(pelitapahtumaDto.getTunniste());
        riittaakoSaldo(pelitapahtumaDto.getTunniste(), pelitapahtumaDto.getPanos());
        
        Laskenta laskenta = new Laskenta();
        Integer arvotunKortinSuuruus = laskenta.arvoKortinSuuruus();
        Boolean voitto = laskenta.isVoitto(pelitapahtumaDto.getValinta(), arvotunKortinSuuruus);

        Integer mahdollisenVoitonSuuruus = voittokerroin * pelitapahtumaDto.getPanos();

        Pelitapahtuma pelitapahtuma = new Pelitapahtuma();
        pelitapahtuma.setAikaleima(new Date());
        pelitapahtuma.setTunniste(pelitapahtumaDto.getTunniste());
        pelitapahtuma.setPanos(pelitapahtumaDto.getPanos());
        pelitapahtuma.setValinta(pelitapahtumaDto.getValinta());
        pelitapahtuma.setArvottuKortti(arvotunKortinSuuruus);
        pelitapahtuma.setMahdollisenVoitonSuuruus(mahdollisenVoitonSuuruus);

        pelitapahtumaRepository.save(pelitapahtuma);

        PelitapahtumaVastausDto pelitapahtumaVastausDto = new PelitapahtumaVastausDto();
        pelitapahtumaVastausDto.setArvottuKortti(arvotunKortinSuuruus);
        pelitapahtumaVastausDto.setVoitto(voitto);
        pelitapahtumaVastausDto.setMahdollisenVoitonSuuruus(mahdollisenVoitonSuuruus);
        Integer saldo = handleSaldo(pelitapahtumaDto);
        pelitapahtumaVastausDto.setPelitilinSaldo(saldo);

        return pelitapahtumaVastausDto;
    }

    public String kotiutaVoitot(KotiutaVoitotPyyntoDto kotiutaVoitotPyyntoDto) {

        String tunniste = kotiutaVoitotPyyntoDto.getTunniste();
        Integer voitonSuuruus = kotiutaVoitotPyyntoDto.getVoitonSuuruus();

        Pelaaja pelaaja = pelaajaRepository.findByTunniste(tunniste);
        Integer uusiSaldo = pelaaja.getSaldo() + voitonSuuruus;
        pelaaja.setSaldo(uusiSaldo);

        pelaajaRepository.save(pelaaja);

        return "Voitot kotiutettu";
    }

    private void onkoPelaajaOlemassa(String tunniste) throws PelaajaaEiLoydyException{
        
        Pelaaja pelaaja = pelaajaRepository.findByTunniste(tunniste);
        if (Objects.isNull(pelaaja)) {
            throw new PelaajaaEiLoydyException("Pelaajaa ei löydy tunnisteella " + tunniste);
        }
    }

    private void riittaakoSaldo(String tunniste, Integer panos) throws SaldoEiRiitaException{
        
        Pelaaja pelaaja = pelaajaRepository.findByTunniste(tunniste);
        Integer uusiSaldo = pelaaja.getSaldo() - panos;
        if (uusiSaldo < 0) {
            throw new SaldoEiRiitaException("Saldo ei riitä panoksella " + panos.toString());
        }
    }

    private Integer handleSaldo(PelitapahtumaPyyntoDto pelitapahtumaDto) {

        if (pelitapahtumaDto.getOnEnsimmainenKierros()) {
            Integer uusiSaldo = vahennaPelaajanSaldo(pelitapahtumaDto.getTunniste(), pelitapahtumaDto.getPanos());
            return uusiSaldo;
        } else {
            Integer saldo = pelaajaRepository.findByTunniste(pelitapahtumaDto.getTunniste()).getSaldo();
            return saldo;
        }
    }

    private Integer vahennaPelaajanSaldo(String tunniste, Integer panos) {

        Pelaaja pelaaja = pelaajaRepository.findByTunniste(tunniste);
        Integer uusiSaldo = pelaaja.getSaldo() - panos;
        pelaaja.setSaldo(uusiSaldo);
        return pelaajaRepository.save(pelaaja).getSaldo();
    }
}
