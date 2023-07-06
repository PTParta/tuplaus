package tuplaus.Dtos.Requests;

public class KotiutaVoitotPyyntoDto {
    
    private String tunniste;

    private Integer voitonSuurus;

    public void setTunniste(String tunniste){
        this.tunniste = tunniste;
    }

    public void setVoitonSuuruus(Integer voitonSuuruus){
        this.voitonSuurus = voitonSuuruus;
    }

    public String getTunniste(){
        return this.tunniste;
    }

    public Integer getVoitonSuuruus(){
        return this.voitonSuurus;
    }
}
