package tuplaus.Pelitapahtuma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tuplaus.Dtos.PelitapahtumaPyyntoDto;

@RestController
public class PelitapahtumaController {
    
    @Autowired
    PelitapahtumaService pelitapahtumaService;

    @PostMapping("tuplaus")
    public Integer tuplaus(@RequestBody PelitapahtumaPyyntoDto pelitapahtumaDto) {

        pelitapahtumaService.luoPelitapahtuma(pelitapahtumaDto);
        return pelitapahtumaDto.getPanos() + 1;
    }
}
