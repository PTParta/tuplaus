package tuplaus.Dtos.Requests;

public class PelitapahtumaPyyntoDto {
    
    private String tunniste;

    private Integer panos;

    private String valinta;

    public void setTunniste(String tunniste){
        this.tunniste = tunniste;
    }

    public void setPanos(Integer panos){
        this.panos = panos;
    }

    public void setValinta(String valinta){
        this.valinta = valinta;
    }

    public String getTunniste(){
        return this.tunniste;
    }

    public Integer getPanos(){
        return this.panos;
    }

    public String getValinta(){
        return this.valinta;
    }
}
