package util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import fs.ctrl.HospedagemCtrl;
import fs.ctrl.HospedeCtrl;
import fs.ctrl.HotelCtrl;
import fs.ctrl.QuartoCtrl;
import fs.entities.Hospedagem;
import fs.entities.Hospede;
import fs.entities.Hotel;
import fs.entities.Quarto;
import fs.enums.CategoriaQuarto;

public class App {
	
	public static void testeSystem() {
		
		System.out.println("Hello World");
		System.err.println("Hello World com erro");

	}
	
	public static void main(String[] args) {
//		testeConexcao();
//		testeCrudQuarto();
//		testeCrudHotel();
		testeCrudHospede();
		testeCrudHospedagem();
	}
	
	public static void testeConexcao() {
		try{
			Connection con = (Connection) DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/db_hotel", "root","root");
			System.out.println("Conexão funcionou");
			}catch(SQLException e){
				System.err.println("Conexão não funcionou");
				System.out.println(e.getMessage());
			}	
	}

	public static void testeCrudHotel() {
		
		HotelCtrl ctrl = new HotelCtrl();
		
		System.out.println("Lista de Hoteis Cadastrados");
		for(Hotel h : ctrl.findAll()) {
			System.out.println(h);
		}
		
		System.out.println("Buscar pelo #ID 1");
		Hotel hotel = ctrl.findById(1);
		System.out.println(hotel);
		
		System.out.println("Cadastrar novo Hotel");
		
		Hotel h1 = new Hotel(5, "Hotel Goiânia", "Goiânia", 3);
		//h1 = ctrl.insert(h1);
		System.out.println(h1);
		
		Hotel h2 = ctrl.findById(3);
		System.out.println("UPDATE");
		System.out.println("#ID original: "+h2);
		h2.setNmHotel(h2.getNmHotel()+" ALTERADO");
		h2 = ctrl.update(h2);
		System.out.println("#ID alterado: "+h2);
		
		
		System.out.println("Lista de Hoteis Cadastrados");
		for(Hotel h : ctrl.findAll()) {
			System.out.println(h);
		}
		System.out.println("Deletar #ID 4");
		ctrl.delete(4);
		
		System.out.println("Lista de Hoteis Atualizado");
		for(Hotel h : ctrl.findAll()) {
			System.out.println(h);
		}
		
		
	}
	
	public static void testeCrudQuarto() {
		
		QuartoCtrl ctrl = new QuartoCtrl();
		
		HotelCtrl hotelCtrl = new HotelCtrl();
		
		System.out.println("Lista de Quartos Cadastrados");
		for(Quarto q : ctrl.findAll()) {
			System.out.println(q);
		}
		
		System.out.println("Buscar pelo #ID 1");
		Quarto quarto = ctrl.findById(1);
		System.out.println(quarto);
		
		System.out.println("Cadastrar novo Hotel");
		
		Quarto q1 = new Quarto(null,
					hotelCtrl.findById(1), 
					CategoriaQuarto.APARTAMENTO, 
					3,
					120,
					240.0);
		//q1 = ctrl.insert(q1);
		System.out.println(q1);
		
		Quarto q2 = ctrl.findById(3);
		System.out.println("UPDATE");
		System.out.println("#ID original: "+q2);
		q2.setPrDiaria(q2.getPrDiaria()+20);
		q2 = ctrl.update(q2);
		System.out.println("#ID alterado: "+q2);
		
		
		System.out.println("Lista de Quartos Cadastrados");
		for(Quarto h : ctrl.findAll()) {
			System.out.println(h);
		}
		System.out.println("Deletar #ID 3");
		ctrl.delete(3);
		
		System.out.println("Lista de Quartos Atualizado");
		for(Quarto q : ctrl.findAll()) {
			System.out.println(q);
		}
		
		
	}

	public static void testeCrudHospede(){
		HospedeCtrl ctrl = new HospedeCtrl();
		Hospede hospede = new Hospede(
				1,
				"FRISON",
				new Date(1996,12,26),
				1123
		);
		Hospede hospede1 = new Hospede(
				2,
				"Junior",
				new Date(1995,8,15),
				1124
		);
		Hospede user = ctrl.insert(hospede);
		Hospede user1 = ctrl.insert(hospede1);
		System.out.println("Hospede inserted: "+user);
		List<Hospede> todos = ctrl.findAll();
		System.out.println("Todos os hospedes: "+todos);
		Hospede dois = ctrl.findById(2);
		System.out.println("#ID 2: "+dois);
		dois.setNomeHospede("Yhkarus");
		Hospede doisUp = ctrl.update(dois);
		System.out.println("Update 2: "+doisUp);
		ctrl.delete(1);
		System.out.println("Delete #1: "+ ctrl.findById(1));
		ctrl.delete(2);
		System.out.println("Fim Hospede");
	}

	public static void testeCrudHospedagem(){
		HospedagemCtrl ctrl = new HospedagemCtrl();
		QuartoCtrl quartoCtrl = new QuartoCtrl();
		Quarto quarto = quartoCtrl.findById(1);
		Hospede hospede = new Hospede(1,"Fernando",new Date(1996,12,26),1234);
		HospedeCtrl hospedeCtrl= new HospedeCtrl();
		hospedeCtrl.insert(hospede);
		Hospedagem hospedagem = new Hospedagem(1,
				new Date(2021,8,15),
				new Date(2021,8,20),
				quarto,
				hospede);
		System.out.println("Insert");
		ctrl.insert(hospedagem);

		List<Hospedagem> allhospedagems = ctrl.findAll();
		System.out.println("Todas as hospedagens: "+allhospedagems);

		Hospedagem byid = ctrl.findById(1);
		System.out.println("Hospedagem por ID "+byid);
		quarto.setIdQuarto(2);
		hospedagem.setQuarto(quarto);
		Hospedagem update = ctrl.update(hospedagem);
		System.out.println("Update hospedagem: "+update);
		ctrl.delete(1);
		System.out.println("Delete #ID 1: "+ctrl.findById(1));
		hospedeCtrl.delete(1);
	}

}
