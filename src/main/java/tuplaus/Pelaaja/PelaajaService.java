package tuplaus.Pelaaja;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuplaus.Dtos.Requests.LuoPelaajaPyyntoDto;

/**
 * TODO: PelaajaServiceInterface ja sille PelaajaServiceImpl,
 * katso alla public interface StudentService
 * https://medium.com/@wahyudi.hh/h2-database-as-embedded-postgres-for-spring-boot-integration-test-295683c7b974
 */
@Service
public class PelaajaService {

    @Autowired
    PelaajaRepository pelaajaRepository;

    public String luoPelaaja(LuoPelaajaPyyntoDto pelaajaDto) {

        Pelaaja pelaaja = new Pelaaja();
        pelaaja.setTunniste(pelaajaDto.getTunniste());
        pelaaja.setNimi(pelaajaDto.getnimi());
        pelaaja.setSaldo(pelaajaDto.getSaldo());

        pelaajaRepository.save(pelaaja);
        return "Pelaaja luotu";
    }

    public List<Pelaaja> haePelaajat() {
        return pelaajaRepository.findAll();
    }
}
