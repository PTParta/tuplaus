package tuplaus.Dtos.Responses;

public class PelitapahtumaVastausDto {
    
    private Integer arvottuKortti;

    private Boolean voitto;

    private Integer mahdollisenVoitonSuuruus;

    private Integer pelitilinSaldo;

    public void setArvottuKortti(Integer arvottuKortti){
        this.arvottuKortti = arvottuKortti;
    }

    public void setVoitto(Boolean voitto){
        this.voitto = voitto;
    }

    public void setMahdollisenVoitonSuuruus(Integer mahdollisenVoitonSuuruus){
        this.mahdollisenVoitonSuuruus = mahdollisenVoitonSuuruus;
    }

    public void setPelitilinSaldo(Integer pelitilinSaldo){
        this.pelitilinSaldo = pelitilinSaldo;
    }

    public Integer getArvottuKortti(){
        return this.arvottuKortti;
    }

    public Boolean getVoitto(){
        return this.voitto;
    }

    public Integer getMahdollisenVoitonSuuruus(){
        return this.mahdollisenVoitonSuuruus;
    }

    public Integer getPelitilinSaldo(){
        return this.pelitilinSaldo;
    }
}
