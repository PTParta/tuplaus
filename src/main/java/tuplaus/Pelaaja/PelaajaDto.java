package tuplaus.Pelaaja;

public class PelaajaDto{
    
    private String tunniste;

    private String nimi;

    private Double saldo;

    public void setTunniste(String tunniste){
        this.tunniste = tunniste;
    }

    public void setNimi(String nimi){
        this.nimi = nimi;
    }

    public void setSaldo(Double saldo){
        this.saldo = saldo;
    }

    public String getTunniste(){
        return this.tunniste;
    }

    public String getnimi(){
        return this.nimi;
    }

    public Double getSaldo(){
        return this.saldo;
    }
}
