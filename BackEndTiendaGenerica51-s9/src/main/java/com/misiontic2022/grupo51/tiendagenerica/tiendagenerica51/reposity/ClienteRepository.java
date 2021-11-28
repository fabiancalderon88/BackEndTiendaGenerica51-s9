package com.misiontic2022.grupo51.tiendagenerica.tiendagenerica51.reposity;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.misiontic2022.grupo51.tiendagenerica.tiendagenerica51.model.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, String>{
	
	List<Cliente> findByCedulacliente(Long cedulacliente);
	List<Cliente> findByNombrecliente(String nombrecliente);
	List<Cliente> findByEmailcliente(String emailcliente);
	
	
	void deleteByCedulacliente(Long cedulacliente);
	void deleteByNombrecliente(String nombrecliente);
	void deleteByEmailcliente(String emailcliente);

}
