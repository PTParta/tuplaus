package tuplaus.Pelitapahtuma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuplaus.Dtos.PelitapahtumaDto;

@Service
public class PelitapahtumaService {
    
    @Autowired
    PelitapahtumaRepository pelitapahtumaRepository;

    public void luoPelitapahtuma(PelitapahtumaDto pelitapahtumaDto){

        
    }
}
