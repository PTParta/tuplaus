package tuplaus.Pelaaja;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tuplaus.Dtos.Requests.LuoPelaajaPyyntoDto;

@RestController
public class PelaajaController {

    @Autowired
    PelaajaService pelaajaService;
    
    @PostMapping("pelaaja")
    public String luoPelaaja(@RequestBody LuoPelaajaPyyntoDto pelaajaDto) {

        return pelaajaService.luoPelaaja(pelaajaDto);
    }

    @GetMapping("pelaajat")
    public List<Pelaaja> haePelaajat() {

       return pelaajaService.haePelaajat();
    }

}
