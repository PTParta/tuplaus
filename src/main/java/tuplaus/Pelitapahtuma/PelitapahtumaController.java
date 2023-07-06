package tuplaus.Pelitapahtuma;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tuplaus.Dtos.PelitapahtumaDto;

@RestController
public class PelitapahtumaController {
    
    @PostMapping("tuplaus")
    public Integer tuplaus(@RequestBody PelitapahtumaDto pelitapahtumaDto) {

        return pelitapahtumaDto.getPanos() + 1;
    }
}
