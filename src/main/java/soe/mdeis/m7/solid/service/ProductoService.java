package soe.mdeis.m7.solid.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soe.mdeis.m7.solid.model.Producto;
import soe.mdeis.m7.solid.repository.ProductoRepository;

@Service
public class ProductoService {

   @Autowired
   ProductoRepository repository;

   public List<Producto> getAll() {
      return repository.findAll();
   }

   public Producto save(Producto producto) {
      return repository.save(producto);
   }

   public Producto update(int id, Producto producto) {
      Producto newProducto = new Producto();
      newProducto.setId(id);
      newProducto.setNombre(producto.getNombre());
      newProducto.setNombreExtranjero(producto.getNombreExtranjero());
      newProducto.setCodBarra(producto.getCodBarra());
      newProducto.setPrecio(producto.getPrecio());
      newProducto.setPeso(producto.getPeso());
      newProducto.setUm(producto.getUm());
      newProducto.setAlternante(producto.getAlternante());
      newProducto.setFabricante(producto.getFabricante());
      newProducto.setProveedor(producto.getProveedor());
      newProducto.setGrupoProducto(producto.getGrupoProducto());
      return repository.save(newProducto);
   }
}
