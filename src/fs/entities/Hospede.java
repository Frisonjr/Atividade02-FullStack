package fs.entities;

import java.sql.Date;

public class Hospede {

    private Integer idHospede;
    private String nomeHospede;
    private Date nascimentoHospede;
    private Integer cpfHospede;

    public Hospede() {
        super();
    }

    public Hospede(Integer idHospede, String nomeHospede, Date nascimentoHospede, Integer cpfHospede) {
        this.idHospede = idHospede;
        this.nomeHospede = nomeHospede;
        this.nascimentoHospede = nascimentoHospede;
        this.cpfHospede = cpfHospede;
    }

    public Integer getIdHospede() {
        return idHospede;
    }

    public void setIdHospede(Integer idHospede) {
        this.idHospede = idHospede;
    }

    public String getNomeHospede() {
        return nomeHospede;
    }

    public void setNomeHospede(String nomeHospede) {
        this.nomeHospede = nomeHospede;
    }

    public Date getNascimentoHospede() {
        return nascimentoHospede;
    }

    public void setNascimentoHospede(Date nascimentoHospede) {
        this.nascimentoHospede = nascimentoHospede;
    }

    public Integer getCpfHospede() {
        return cpfHospede;
    }

    public void setCpfHospede(Integer cpfHospede) {
        this.cpfHospede = cpfHospede;
    }

    @Override
    public String toString() {
        return "Hospede{" +
                "idHospede=" + idHospede +
                ", nomeHospede='" + nomeHospede + '\'' +
                ", nascimentoHospede=" + nascimentoHospede +
                ", cpfHospede=" + cpfHospede +
                '}';
    }
}
