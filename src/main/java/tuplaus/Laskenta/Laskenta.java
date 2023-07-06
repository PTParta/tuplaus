package tuplaus.Laskenta;

import java.util.Random;

public class Laskenta {
    
    //TODO: Yksikkötesti
    public Integer arvoKortinSuuruus(){
        Integer pieninKortti = 1;
        Integer suurinKortti = 13;
        return new Random().nextInt(suurinKortti - pieninKortti + 1) + pieninKortti;
    }

}
