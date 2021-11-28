package com.misiontic2022.grupo51.tiendagenerica.tiendagenerica51.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.misiontic2022.grupo51.tiendagenerica.tiendagenerica51.model.Cliente;
import com.misiontic2022.grupo51.tiendagenerica.tiendagenerica51.reposity.ClienteRepository;
import com.misiontic2022.grupo51.tiendagenerica.tiendagenerica51.reposity.ProductoRepository;

@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ClienteController {
	
	@Autowired
	ClienteRepository clienteRepository;

	@GetMapping("/clientes")
	public ResponseEntity<List<Cliente>> getAllClientes(@RequestParam(required = false) String nombrecliente) {
		try {
			List<Cliente> clientes = new ArrayList<Cliente>();

			if (nombrecliente == null) {
				clienteRepository.findAll().forEach(clientes::add);
			} else {
				clienteRepository.findByNombrecliente(nombrecliente).forEach(clientes::add);
			}

			if (clientes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(clientes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping("/clientes/email/{email}")
	public ResponseEntity<Cliente> getClienteByEmailcliente(@PathVariable("email") String email) {
		Cliente aux=clienteRepository.findByEmailcliente(email).get(0);
		Optional<Cliente> clienteData =  Optional.of(aux);

		if (clienteData.isPresent()) {
			return new ResponseEntity<>(clienteData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/clientes/cedula/{cedula}")
	public ResponseEntity<Cliente> getClienteByCedula(@PathVariable("cedula") Long cedula) {
		Cliente aux=clienteRepository.findByCedulacliente(cedula).get(0);
		Optional<Cliente> clienteData =  Optional.of(aux);

		if (clienteData.isPresent()) {
			return new ResponseEntity<>(clienteData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/clientes")
	public ResponseEntity<Cliente> createCliente(@RequestBody Cliente client) {
		try {
			Cliente _cliente = clienteRepository.save(
					new Cliente(
							client.getCedulacliente(),
							client.getDireccioncliente(),
							client.getEmailcliente(),
							client.getNombrecliente(),
							client.getTelefonocliente()));
			return new ResponseEntity<>(_cliente, HttpStatus.CREATED);
		} catch (DuplicateKeyException e) {
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/clientes/cedula/{cedula}")
	public ResponseEntity<Cliente> updateClienteByCedula(@PathVariable("cedula") long cedula, @RequestBody Cliente client) {
		Cliente aux=clienteRepository.findByCedulacliente(cedula).get(0);
		Optional<Cliente> clienteData =  Optional.of(aux);

		if (clienteData.isPresent()) {
			Cliente _cliente = clienteData.get();
			_cliente.setCedulacliente(client.getCedulacliente());
			_cliente.setDireccioncliente(client.getDireccioncliente());
			_cliente.setEmailcliente(client.getEmailcliente());
			_cliente.setNombrecliente(client.getNombrecliente());
			_cliente.setTelefonocliente(client.getTelefonocliente());

			
			return new ResponseEntity<>(clienteRepository.save(_cliente), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/clientes/cedula/{cedula}")
	public ResponseEntity<HttpStatus> deleteClientesByCedula(@PathVariable("cedula") long cedula) {
		try {
			clienteRepository.deleteByCedulacliente(cedula);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
