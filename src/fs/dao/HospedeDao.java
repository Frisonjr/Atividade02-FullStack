package fs.dao;

import fs.entities.Hospede;
import fs.exceptions.HospedeException;
import fs.exceptions.QuartoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HospedeDao {
    Connection conn;

    public HospedeDao(Connection conn){
        this.conn = conn;
    }

    public List<Hospede> findAll() throws HospedeException {
        List<Hospede> retorno = new ArrayList<Hospede>();
        ResultSet rs = null;
        PreparedStatement st = null;
        try{
            String sql = " SELECT id_hospede, nm_hospede, dt_nascimento, cpf FROM tb_hospede; ";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Hospede hospede = new Hospede(
                        rs.getInt("id_hospede"),
                        rs.getString("nm_hospede"),
                        rs.getDate("dt_nascimento"),
                        rs.getInt("cpf")
                );
                retorno.add(hospede);
            }
        }catch (SQLException e) {
            throw new HospedeException("Erro no banco de dados: "+e.getMessage());
        }finally {
            DB.closeStatment(st);
            DB.closeResultSet(rs);
        }
        return retorno;
    }

    public Hospede findById(Integer id) throws HospedeException{
        Hospede retorno = new Hospede();
        ResultSet rs = null;
        PreparedStatement st = null;

        try{
            String sql = "SELECT id_hospede, nm_hospede, dt_nascimento, cpf FROM tb_hospede where id_hospede ="+ id;
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            if(rs.next()){
                retorno = new Hospede(
                        rs.getInt("id_hospede"),
                        rs.getString("nm_hospede"),
                        rs.getDate("dt_nascimento"),
                        rs.getInt("cpf")
                );
            }
        }catch (SQLException e){
            throw new HospedeException("Erro no banco de dados: "+ e.getMessage());
        }finally {
            DB.closeStatment(st);
            DB.closeResultSet(rs);
        }
        return retorno;
    }

    public Hospede insert (Hospede hospede) throws HospedeException{
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(""
                + "INSERT INTO tb_hospede "
                + "(id_hospede, nm_hospede, dt_nascimento, cpf)"
                + "VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, hospede.getIdHospede());
            st.setString(2, hospede.getNomeHospede());
            st.setDate(3, hospede.getNascimentoHospede());
            st.setInt(4, hospede.getCpfHospede());
            int rowsAffected = st.executeUpdate();
            if (rowsAffected>0){
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()){
                    hospede.setIdHospede(rs.getInt(1));
                }
            }
            else {
                throw new HospedeException("Erro: Nenhuma linha foi inserida");
            }
        } catch (SQLException e) {
            throw new HospedeException("Erro no banco de dados: "+ e.getMessage());
        }finally {
            DB.closeStatment(st);
        }
        return hospede;
    }

    public Hospede update(Hospede hospede) throws HospedeException{
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(""
                    +"UPDATE tb_hospede "
                    +"SET nm_hospede = ?, dt_nascimento = ?, cpf = ? "
                    +"WHERE id_hospede = ?");
            st.setInt(4, hospede.getIdHospede());
            st.setString(1, hospede.getNomeHospede());
            st.setDate(2, hospede.getNascimentoHospede());
            st.setInt(3, hospede.getCpfHospede());
            st.executeUpdate();
        }
        catch (SQLException e){
            throw new HospedeException("Erro no banco: "+e.getMessage());
        }finally {
            DB.closeStatment(st);
        }
        return hospede;
    }

    public void delete(Integer id) throws HospedeException{
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "DELETE FROM tb_hospede WHERE id_hospede = ?");
            st.setInt(1, id);
            st.executeUpdate();
        }
        catch (SQLException e) {
            throw new HospedeException("Erro no banco de dados: "+e.getMessage());
        }
        finally {
            DB.closeStatment(st);
        }
    }

}
