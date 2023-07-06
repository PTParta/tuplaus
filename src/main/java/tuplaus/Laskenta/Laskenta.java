package tuplaus.Laskenta;

import java.util.Random;

public class Laskenta {
    
    //TODO: Yksikkötesti
    public Integer arvoKortinSuuruus(){
        Integer pieninKortti = 1;
        Integer suurinKortti = 13;
        return new Random().nextInt(suurinKortti - pieninKortti + 1) + pieninKortti;
    }

    //TODO: Yksikkötesti
    public Boolean isVoitto(String valinta, Integer arvotunKortinSuuruus){

        if(arvotunKortinSuuruus == 7){
            return false;
        }
        if(valinta.equals("pieni") && arvotunKortinSuuruus < 7) {
            return true;
        }
        if(valinta.equals("suuri") && arvotunKortinSuuruus > 7){
            return true;
        }
        return false;
    }
}
