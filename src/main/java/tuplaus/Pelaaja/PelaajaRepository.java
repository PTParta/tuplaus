package tuplaus.Pelaaja;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PelaajaRepository extends JpaRepository<Pelaaja, Long>{
    Pelaaja findByTunniste(String tunniste);
    
}
