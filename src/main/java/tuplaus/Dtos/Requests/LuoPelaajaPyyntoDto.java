package tuplaus.Dtos.Requests;

public class LuoPelaajaPyyntoDto{
    
    private String tunniste;

    private String nimi;

    private Integer saldo;

    public void setTunniste(String tunniste){
        this.tunniste = tunniste;
    }

    public void setNimi(String nimi){
        this.nimi = nimi;
    }

    public void setSaldo(Integer saldo){
        this.saldo = saldo;
    }

    public String getTunniste(){
        return this.tunniste;
    }

    public String getnimi(){
        return this.nimi;
    }

    public Integer getSaldo(){
        return this.saldo;
    }
}
