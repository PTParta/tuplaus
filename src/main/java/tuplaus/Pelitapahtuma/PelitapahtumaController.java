package tuplaus.Pelitapahtuma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tuplaus.Dtos.Requests.KotiutaVoitotPyyntoDto;
import tuplaus.Dtos.Requests.PelitapahtumaPyyntoDto;
import tuplaus.Dtos.Responses.PelitapahtumaVastausDto;

@RestController
public class PelitapahtumaController {
    
    @Autowired
    PelitapahtumaService pelitapahtumaService;

    @PostMapping("tuplaus")
    public PelitapahtumaVastausDto tuplaus(@RequestBody PelitapahtumaPyyntoDto pelitapahtumaDto) {

       return pelitapahtumaService.luoPelitapahtuma(pelitapahtumaDto);
    }

    @PostMapping("kotiutavoitot")
    public String kotioutaVoitot(@RequestBody KotiutaVoitotPyyntoDto kotiutaVoitotPyyntoDto){

        pelitapahtumaService.kotiutaVoitot(kotiutaVoitotPyyntoDto);
        
        return "Voitot kotiutettu";
    }
}
