package tuplaus.Pelaaja;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PelaajaController {

    @Autowired
    PelaajaService pelaajaService;
    
    @PostMapping("pelaaja")
    public void luoPelaaja(@RequestBody PelaajaDto pelaajaDto) {

        pelaajaService.luoPelaaja(pelaajaDto);
    }

    @GetMapping("pelaaja/{tunniste}")
    public String haePelaaja(@PathVariable String tunniste) {

        return "root";
    }

    @GetMapping("pelaajat")
    public List<Pelaaja> haePelaajat() {

       return pelaajaService.haePelaajat();
    }

}
