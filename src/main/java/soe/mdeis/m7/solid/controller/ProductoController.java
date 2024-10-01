package soe.mdeis.m7.solid.controller;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.common.util.StringUtils;
import soe.mdeis.m7.solid.dto.ApiResponse;
import soe.mdeis.m7.solid.model.Producto;
import soe.mdeis.m7.solid.service.ProductoService;

import java.math.BigDecimal;
import java.net.URI;

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

   @Autowired
   private ProductoService service;

   @GetMapping("/producto")
   public ResponseEntity<ApiResponse<List<Producto>>> getProductos() {
      var productos = service.getAll();
      return ResponseEntity.ok()
            .body(ApiResponse.of(productos, String.format("[%d] Productos", productos.size())));
   }

   @PostMapping("/producto")
   public ResponseEntity<ApiResponse<Producto>> registerProducto(@RequestBody Producto producto) {
      ResponseEntity<ApiResponse<Producto>> verify = verifyProduct(producto);
      if (verify != null)
         return verify;
      Producto newProducto = service.save(producto);
      return ResponseEntity.created(URI.create("/producto/" + newProducto.getId()))
            .body(ApiResponse.of(newProducto, "Producto Registrado"));
   }

   @PutMapping("/producto/{id}")
   public ResponseEntity<ApiResponse<Producto>> updateProducto(@PathVariable int id, @RequestBody Producto producto) {
      ResponseEntity<ApiResponse<Producto>> verify = verifyProduct(producto);
      if (verify != null)
         return verify;
      var productoUpdated = service.update(id, producto);
      return ResponseEntity.ok().body(ApiResponse.of(productoUpdated, "Producto actualizado"));
   }

   private ResponseEntity<ApiResponse<Producto>> verifyProduct(Producto producto) {
      if (StringUtils.isBlank(producto.getNombre())) {
         return ResponseEntity.badRequest().body(ApiResponse.of(producto, "Falta el campo [nombre]"));
      }
      if (StringUtils.isBlank(producto.getNombreExtranjero())) {
         return ResponseEntity.badRequest().body(ApiResponse.of(producto, "Falta el campo [nombreExtranjero]"));
      }
      if (StringUtils.isBlank(producto.getCodBarra())) {
         return ResponseEntity.badRequest().body(ApiResponse.of(producto, "Falta el campo [codBarra]"));
      }
      if (producto.getPrecio() == null || producto.getPrecio().compareTo(BigDecimal.ZERO) <= 0) {
         return ResponseEntity.badRequest().body(ApiResponse.of(producto, "Falta el campo [precio]"));
      }
      if (producto.getPeso() <= 0) {
         return ResponseEntity.badRequest().body(ApiResponse.of(producto, "Falta el campo [peso]"));
      }
      if (StringUtils.isBlank(producto.getUm())) {
         return ResponseEntity.badRequest().body(
               ApiResponse.of(producto, "Falta el campo [um]"));
      }
      return null;
   }
}
