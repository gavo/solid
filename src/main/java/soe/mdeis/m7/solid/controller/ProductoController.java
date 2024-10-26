package soe.mdeis.m7.solid.controller;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.common.util.StringUtils;
import soe.mdeis.m7.solid.model.Producto;
import soe.mdeis.m7.solid.service.ProductoService;

import java.math.BigDecimal;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api")
public class ProductoController {

   @Autowired
   private ProductoService service;

   @GetMapping("/producto")
   public ResponseEntity<List<Producto>> getProductos() {
      return ResponseEntity.ok().body(service.getAll());
   }

   @PostMapping("/producto")
   public ResponseEntity<Producto> registerProducto(@RequestBody Producto producto) {
      ResponseEntity<Producto> verify = verifyProduct(producto);
      if (verify != null)
         return verify;
      Producto newProducto = service.save(producto);
      return ResponseEntity.created(URI.create("/producto/" + newProducto.getId())).body(newProducto);
   }

   @PutMapping("/producto/{id}")
   public ResponseEntity<Producto> updateProducto(@PathVariable int id, @RequestBody Producto producto) {
      ResponseEntity<Producto> verify = verifyProduct(producto);
      if (verify != null)
         return verify;
      var productoUpdated = service.update(id, producto);
      return ResponseEntity.ok().body(productoUpdated);
   }

   private ResponseEntity<Producto> verifyProduct(Producto producto) {
      if (StringUtils.isBlank(producto.getNombre())
            || StringUtils.isBlank(producto.getNombreExtranjero())
            || StringUtils.isBlank(producto.getCodBarra())
            || producto.getPrecio() == null
            || producto.getPrecio().compareTo(BigDecimal.ZERO) <= 0
            || producto.getPeso() <= 0 ||
            StringUtils.isBlank(producto.getUm())) {
         return ResponseEntity.badRequest().body(producto);
      }
      return null;
   }
}
