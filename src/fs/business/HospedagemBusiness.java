package fs.business;

import fs.dao.DB;
import fs.dao.HospedagemDao;
import fs.dao.HospedeDao;
import fs.dao.QuartoDao;
import fs.entities.Hospedagem;
import fs.exceptions.HospedagemException;

import java.sql.Connection;
import java.util.List;

public class HospedagemBusiness {
    Connection conn = DB.getConnection();
    HospedagemDao dao = new HospedagemDao(conn, new QuartoDao(conn), new HospedeDao(conn));

    public List<Hospedagem> findAll() throws HospedagemException {
        return dao.findAll();
    }

    public Hospedagem findById(Integer id) throws HospedagemException{
        return dao.findById(id);
    }
    public Hospedagem insert(Hospedagem hospedagem) throws HospedagemException{
        return dao.insert(hospedagem);
    }
    public Hospedagem update(Hospedagem hospedagem)throws HospedagemException{
        return dao.update(hospedagem);
    }
    public void delete(Integer id) throws HospedagemException {
        dao.delete(id);
    }
    
}
