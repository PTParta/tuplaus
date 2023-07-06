package tuplaus.Pelaaja;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuplaus.Dtos.PelaajaDto;

// TODO: PelaajaServiceInterface ja sille PelaajaServiceImpl, katso alla public interface StudentService
//https://medium.com/@wahyudi.hh/h2-database-as-embedded-postgres-for-spring-boot-integration-test-295683c7b974
@Service
public class PelaajaService {
    
@Autowired
PelaajaRepository pelaajaRepository;

    //TODO: mapper
    public void luoPelaaja(PelaajaDto pelaajaDto){

        Pelaaja pelaaja = new Pelaaja();
        pelaaja.setTunniste(pelaajaDto.getTunniste());
        pelaaja.setNimi(pelaajaDto.getnimi());
        pelaaja.setSaldo(pelaajaDto.getSaldo());

        pelaajaRepository.save(pelaaja);
        System.out.print("Tallennetu pelaaja: " + pelaaja.getNimi());
    }

    public List<Pelaaja> haePelaajat(){
        return pelaajaRepository.findAll();
    }

}
