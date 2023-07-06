package tuplaus.Pelitapahtuma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tuplaus.Dtos.PelitapahtumaPyyntoDto;
import tuplaus.Dtos.PelitapahtumaVastausDto;

@RestController
public class PelitapahtumaController {
    
    @Autowired
    PelitapahtumaService pelitapahtumaService;

    @PostMapping("tuplaus")
    public PelitapahtumaVastausDto tuplaus(@RequestBody PelitapahtumaPyyntoDto pelitapahtumaDto) {

       return pelitapahtumaService.luoPelitapahtuma(pelitapahtumaDto);
    }
}
