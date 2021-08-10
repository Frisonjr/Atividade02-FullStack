package fs.dao;

import fs.entities.Hospedagem;
import fs.entities.Hospede;
import fs.entities.Quarto;
import fs.exceptions.HospedagemException;
import fs.exceptions.HospedeException;
import fs.exceptions.QuartoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HospedagemDao {
    Connection conn;
    HospedeDao hospedeDao;
    QuartoDao quartoDao;
    public HospedagemDao(Connection conn, QuartoDao quartoDao, HospedeDao hospedeDao ){
        this.conn = conn;
        this.quartoDao = quartoDao;
        this.hospedeDao = hospedeDao;
    }

    public List<Hospedagem> findAll() throws HospedagemException {
        List<Hospedagem> retorno = new ArrayList<Hospedagem>();
        ResultSet rs = null;
        PreparedStatement st = null;
        try{

            String sql = " SELECT id_hospedagem, id_quarto, id_hospede, dt_checkin, dt_checkout FROM tb_hospedagem; ";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            Quarto quarto = null;
            Hospede hospede = null;
            while(rs.next()){
                try{
                    quarto = this.quartoDao.findById(rs.getInt("id_quarto"));
                    hospede = this.hospedeDao.findById(rs.getInt("id_hospede"));
                }
                catch (QuartoException | HospedeException e){
                    throw new HospedagemException("Falha entidate relacionada: "+ e.getMessage() );
                }
                Hospedagem hospedagem = new Hospedagem(
                        rs.getInt("id_hospedagem"),
                        rs.getDate("dt_checkin"),
                        rs.getDate("dt_checkout"),
                        quarto,
                        hospede
                );
                retorno.add(hospedagem);
            }
        }catch (SQLException e) {
            throw new HospedagemException("Erro no banco de dados: "+e.getMessage());
        }finally {
            DB.closeStatment(st);
            DB.closeResultSet(rs);
        }
        return retorno;
    }

    public Hospedagem findById(Integer id) throws HospedagemException{
        Hospedagem retorno = new Hospedagem();
        ResultSet rs = null;
        PreparedStatement st = null;

        try{
            String sql = "SELECT id_hospedagem, id_quarto, id_hospede, dt_checkin, dt_checkout FROM tb_hospedagem where id_hospedagem ="+ id;
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            Quarto quarto = null;
            Hospede hospede = null;
            if (rs.next()){
                try{
                    quarto = this.quartoDao.findById(rs.getInt("id_quarto"));
                    hospede = this.hospedeDao.findById(rs.getInt("id_hospede"));
                }
                catch (QuartoException | HospedeException e){
                    throw new HospedagemException("Falha entidate relacionada: "+ e.getMessage() );
                }
                return new Hospedagem(
                        rs.getInt("id_hospedagem"),
                        rs.getDate("dt_checkin"),
                        rs.getDate("dt_checkout"),
                        quarto,
                        hospede
                );
            }
        }catch (SQLException e){
            throw new HospedagemException("Erro no banco de dados: "+ e.getMessage());
        }finally {
            DB.closeStatment(st);
            DB.closeResultSet(rs);
        }
        return retorno;
    }

    public Hospedagem insert (Hospedagem hospedagem) throws HospedagemException{
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(""
                    + "INSERT INTO tb_hospedagem "
                    + "(id_hospedagem, id_quarto, id_hospede, dt_checkin, dt_checkout)"
                    + "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, hospedagem.getIdHospedagem());
            st.setInt(2, hospedagem.getQuarto().getIdQuarto());
            st.setInt(3, hospedagem.getHospede().getIdHospede());
            st.setDate(4, hospedagem.getCheckIn());
            st.setDate(5, hospedagem.getCheckOut());
            int rowsAffected = st.executeUpdate();
            if (rowsAffected>0){
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()){
                    hospedagem.setIdHospedagem(rs.getInt(1));
                }
            } else {
                throw new HospedagemException("Erro: Nenhuma linha foi inserida");
            }
        } catch (SQLException e) {
            throw new HospedagemException("Erro no banco de dados: "+ e.getMessage());
        }finally {
            DB.closeStatment(st);
        }
        return hospedagem;
    }

    public Hospedagem update(Hospedagem hospedagem) throws HospedagemException{
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(""
                    + "UPDATE tb_hospedagem "
                    + "SET id_quarto = ?, id_hospede = ?, dt_checkin = ?, dt_checkout = ?"
                    + "WHERE id_hospedagem = ? ", Statement.RETURN_GENERATED_KEYS);
            st.setInt(5, hospedagem.getIdHospedagem());
            st.setInt(1, hospedagem.getQuarto().getIdQuarto());
            st.setInt(2, hospedagem.getHospede().getIdHospede());
            st.setDate(3, hospedagem.getCheckIn());
            st.setDate(4, hospedagem.getCheckOut());
            st.executeUpdate();
        }
        catch (SQLException e){
            throw new HospedagemException("Erro no banco: "+e.getMessage());
        }finally {
            DB.closeStatment(st);
        }
        return hospedagem;
    }

    public void delete(Integer id) throws HospedagemException{
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "DELETE FROM tb_hospedagem WHERE id_hospedagem = ?");
            st.setInt(1, id);
            st.executeUpdate();
        }
        catch (SQLException e) {
            throw new HospedagemException("Erro no banco de dados: "+e.getMessage());
        }
        finally {
            DB.closeStatment(st);
        }
    }
}
