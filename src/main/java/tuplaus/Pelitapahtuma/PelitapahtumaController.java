package tuplaus.Pelitapahtuma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import tuplaus.Dtos.Requests.KotiutaVoitotPyyntoDto;
import tuplaus.Dtos.Requests.PelitapahtumaPyyntoDto;
import tuplaus.Dtos.Responses.PelitapahtumaVastausDto;
import tuplaus.Exceptions.PelaajaaEiLoydyException;
import tuplaus.Exceptions.SaldoEiRiitaException;

@RestController
public class PelitapahtumaController {

    @Autowired
    PelitapahtumaService pelitapahtumaService;

    @PostMapping("tuplaus")
    public PelitapahtumaVastausDto tuplaus(@RequestBody PelitapahtumaPyyntoDto pelitapahtumaDto) {

        try {
            return pelitapahtumaService.luoPelitapahtuma(pelitapahtumaDto);
        } catch (SaldoEiRiitaException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Saldo ei riitä");
        } catch (PelaajaaEiLoydyException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Tunnisteella ei löydy pelaajaa");
        }
    }

    @PostMapping("kotiutavoitot")
    public String kotioutaVoitot(@RequestBody KotiutaVoitotPyyntoDto kotiutaVoitotPyyntoDto) {

        return pelitapahtumaService.kotiutaVoitot(kotiutaVoitotPyyntoDto);
    }
}
