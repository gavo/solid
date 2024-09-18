package soe.mdeis.m7.solid.controller;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;

import soe.mdeis.m7.solid.model.Producto;
import soe.mdeis.m7.solid.service.ProductoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api")
@CrossOrigin(value = "http://localhost:3000")
public class ProductoController {
   private static final Logger logger = LoggerFactory.getLogger(ProductoController.class);

   @Autowired
   private ProductoService productoService;

   @GetMapping("/producto")
   public List<Producto> getMethodName() {
      var productos = productoService.getAll();
      productos.forEach(producto -> logger.info(producto.getNombre()));
      return productos;
   }

   @PostMapping("/producto")
   public Producto postMethodName(@RequestBody Producto newProducto) {
      logger.info("Producto a Guardar: " + newProducto.getNombre());
      return productoService.save(newProducto);
   }

}
