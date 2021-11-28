package com.misiontic2022.grupo51.tiendagenerica.tiendagenerica51.reposity;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.misiontic2022.grupo51.tiendagenerica.tiendagenerica51.model.Producto;


public interface ProductoRepository extends MongoRepository<Producto, String>{
	
	List<Producto> findByCodigoproducto(Long codigoproducto);
	
	List<Producto> findByNombreproducto(String nombreproducto);

}
