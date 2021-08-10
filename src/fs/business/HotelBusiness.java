package fs.business;

import java.util.List;

import fs.dao.DB;
import fs.dao.HotelDao;
import fs.entities.Hotel;
import fs.exceptions.HotelException;

public class HotelBusiness {

	
	HotelDao dao = new HotelDao(DB.getConnection());
	
	public List<Hotel> findAll() throws HotelException{
		return dao.findAll();
	}
	
	public Hotel findById(Integer id) throws HotelException{
		return dao.findById(id);
	}
	
	public Hotel insert(Hotel hotel) throws HotelException {
		return dao.insert(hotel);
	}
	
	public Hotel update(Hotel hotel) throws HotelException {
		return dao.update(hotel);
	}
	
	public void delete(Integer id) throws HotelException {
		dao.delete(id);
	}
}
