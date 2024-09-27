package soe.mdeis.m7.solid.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import soe.mdeis.m7.solid.model.Producto;
import soe.mdeis.m7.solid.repository.ProductoRepository;

@Service
public class ProductoService {

   private final ProductoRepository productoRepository;

   public ProductoService(ProductoRepository productoRepository) {
      this.productoRepository = productoRepository;
   }

   public List<Producto> getAll() {
      return productoRepository.findAll();
   }

   public Producto save(Producto producto) {
      return productoRepository.save(producto);
   }

   public Producto savePrice(Producto producto, BigDecimal price) {
      producto.setPrecio(price);
      return productoRepository.save(producto);
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
      return productoRepository.save(newProducto);
   }
}
