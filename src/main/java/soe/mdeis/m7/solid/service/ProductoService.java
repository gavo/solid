package soe.mdeis.m7.solid.service;

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

   public Producto savePrice(Producto producto, int price) {
      producto.setPrecioLista(price);
      return productoRepository.save(producto);
   }
}
