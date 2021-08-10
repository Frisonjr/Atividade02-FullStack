package fs.entities;

import java.sql.Date;

public class Hospedagem {
    private Integer idHospedagem;
    private Date checkIn;
    private Date checkOut;
    private Quarto quarto;
    private Hospede hospede;

    public Hospedagem() {
        super();
    }

    public Hospedagem(Integer idHospedagem, Date checkIn, Date checkOut, Quarto quarto, Hospede hospede) {
        this.idHospedagem = idHospedagem;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.quarto = quarto;
        this.hospede = hospede;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public Hospede getHospede() {
        return hospede;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    public Integer getIdHospedagem() {
        return idHospedagem;
    }

    public void setIdHospedagem(Integer idHospedagem) {
        this.idHospedagem = idHospedagem;
    }


    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    @Override
    public String toString() {
        return "Hospedagem{" +
                "idHospedagem=" + idHospedagem +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", quarto=" + quarto +
                ", hospede=" + hospede +
                '}';
    }
}
