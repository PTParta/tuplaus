package tuplaus.Pelaaja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PelaajaRepository extends JpaRepository<Pelaaja, Long>{
    Pelaaja findByTunniste(String tunniste);
    
}
