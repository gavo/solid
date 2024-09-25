package soe.mdeis.m7.solid.controller;

import org.springframework.web.bind.annotation.RestController;

import io.micrometer.common.util.StringUtils;
import soe.mdeis.m7.solid.model.Producto;
import soe.mdeis.m7.solid.service.ProductoService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api")
@CrossOrigin(value = "http://localhost:3000")
public class ProductoController {
   private static final Logger logger = LoggerFactory.getLogger(ProductoController.class);

   @Autowired
   private ProductoService productoService;

   @GetMapping("/producto")
   public ResponseEntity<?> getMethodName() {
      var productos = productoService.getAll();
      productos.forEach(producto -> logger.info(producto.getNombre()));
      return ResponseEntity.ok().body(productos);
   }

   @PostMapping("/producto")
   public ResponseEntity<?> postMethodName(@RequestBody Producto newProducto) {
      if (StringUtils.isBlank(newProducto.getNombre())) {
         return ResponseEntity.badRequest().body("Debe asignar un Nombre");
      }
      if (StringUtils.isBlank(newProducto.getNombreExtranjero())) {
         return ResponseEntity.badRequest().body("Debe asignar un Nombre extranjero");
      }
      if (StringUtils.isBlank(newProducto.getCodBarra())) {
         return ResponseEntity.badRequest().body("Debe asignar un Codigo de barra");
      }
      if (newProducto.getPrecioLista() <= 0) {
         return ResponseEntity.badRequest().body("Debe asignar precio");
      }
      if (newProducto.getPeso() <= 0) {
         return ResponseEntity.badRequest().body("Debe asignar Peso");
      }
      if (StringUtils.isBlank(newProducto.getUm())) {
         return ResponseEntity.badRequest().body("Debe asignar Unidad de Medida");
      }
      logger.info("Producto a Guardar: " + newProducto.getNombre());
      return ResponseEntity.ok().body(productoService.save(newProducto));
   }

   @PutMapping("/producto/{id}")
   public ResponseEntity<?> putMethodName(@PathVariable int id, @RequestBody Producto producto) {
      if (StringUtils.isBlank(producto.getNombre())) {
         return ResponseEntity.badRequest().body("Debe asignar un Nombre");
      }
      if (StringUtils.isBlank(producto.getNombreExtranjero())) {
         return ResponseEntity.badRequest().body("Debe asignar un Nombre extranjero");
      }
      if (StringUtils.isBlank(producto.getCodBarra())) {
         return ResponseEntity.badRequest().body("Debe asignar un Codigo de barra");
      }
      if (producto.getPrecioLista() <= 0) {
         return ResponseEntity.badRequest().body("Debe asignar precio");
      }
      if (producto.getPeso() <= 0) {
         return ResponseEntity.badRequest().body("Debe asignar Peso");
      }
      if (StringUtils.isBlank(producto.getUm())) {
         return ResponseEntity.badRequest().body("Debe asignar Unidad de Medida");
      }
      return ResponseEntity.ok().body(productoService.update(id, producto));
   }
}
