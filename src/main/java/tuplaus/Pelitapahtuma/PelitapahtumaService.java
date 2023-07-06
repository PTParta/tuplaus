package tuplaus.Pelitapahtuma;

import java.util.Date;
import java.util.Objects;

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

    private static Integer voittokerroin = 2;

    @Autowired
    PelitapahtumaRepository pelitapahtumaRepository;

    @Autowired
    PelaajaRepository pelaajaRepository;

    public PelitapahtumaVastausDto luoPelitapahtuma(PelitapahtumaPyyntoDto pelitapahtumaDto)
            throws SaldoEiRiitaException, PelaajaaEiLoydyException {

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

        PelitapahtumaVastausDto pelitapahtumaVastausDto = new PelitapahtumaVastausDto();
        pelitapahtumaVastausDto.setArvottuKortti(arvotunKortinSuuruus);
        pelitapahtumaVastausDto.setVoitto(voitto);
        pelitapahtumaVastausDto.setMahdollisenVoitonSuuruus(mahdollisenVoitonSuuruus);
        Integer saldo = handleSaldo(pelitapahtumaDto);
        pelitapahtumaVastausDto.setPelitilinSaldo(saldo);

        /* if (pelitapahtumaDto.getOnEnsimmainenKierros()) {
            Integer uusiSaldo = vahennaPelaajanSaldo(pelitapahtumaDto.getTunniste(), pelitapahtumaDto.getPanos());
            pelitapahtumaVastausDto.setPelitilinSaldo(uusiSaldo);
        } else {
            Integer saldo = pelaajaRepository.findByTunniste(pelitapahtuma.getTunniste()).getSaldo();
            pelitapahtumaVastausDto.setPelitilinSaldo(saldo);
        } */

        pelitapahtumaRepository.save(pelitapahtuma);

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

    private Integer handleSaldo(PelitapahtumaPyyntoDto pelitapahtumaDto)
            throws SaldoEiRiitaException, PelaajaaEiLoydyException {

        if (pelitapahtumaDto.getOnEnsimmainenKierros()) {
            Integer uusiSaldo = vahennaPelaajanSaldo(pelitapahtumaDto.getTunniste(), pelitapahtumaDto.getPanos());
            return uusiSaldo;
        } else {
            Integer saldo = pelaajaRepository.findByTunniste(pelitapahtumaDto.getTunniste()).getSaldo();
            return saldo;
        }
    }

    private Integer vahennaPelaajanSaldo(String tunniste, Integer panos)
            throws SaldoEiRiitaException, PelaajaaEiLoydyException {

        Pelaaja pelaaja = pelaajaRepository.findByTunniste(tunniste);

        if (Objects.isNull(pelaaja)) {
            throw new PelaajaaEiLoydyException("Pelaajaa ei löydy tunnisteella " + tunniste);
        }
        
        Integer uusiSaldo = pelaaja.getSaldo() - panos;

        if (uusiSaldo < 0) {
            throw new SaldoEiRiitaException("Saldo ei riitä panoksella " + panos.toString());
        }

        pelaaja.setSaldo(uusiSaldo);
        return pelaajaRepository.save(pelaaja).getSaldo();
    }

    public class SaldoEiRiitaException extends Exception {
        public SaldoEiRiitaException(String virheviesti) {
            super(virheviesti);
        }
    }

    public class PelaajaaEiLoydyException extends Exception {
        public PelaajaaEiLoydyException(String virheviesti) {
            super(virheviesti);
        }
    }
}
