package soe.mdeis.m7.solid.controller;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.common.util.StringUtils;
import soe.mdeis.m7.solid.dto.ApiResponse;
import soe.mdeis.m7.solid.model.Proveedor;
import soe.mdeis.m7.solid.service.ProveedorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api")
@CrossOrigin(value = "http://localhost:3000")
public class ProveedorController {

   @Autowired
   private ProveedorService service;

   @GetMapping("/proveedor")
   public ResponseEntity<ApiResponse<List<Proveedor>>> getProveedores() {
      var proveedores = service.getAll();
      return ResponseEntity.ok()
            .body(ApiResponse.of(proveedores, String.format("[%d] Proveedores", proveedores.size())));
   }

   @PostMapping("/proveedor")
   public ResponseEntity<ApiResponse<Proveedor>> registerProveedor(@RequestBody Proveedor proveedor) {
      if (StringUtils.isBlank(proveedor.getNombre())) {
         return ResponseEntity.badRequest().body(ApiResponse.of(proveedor, "Falta el campo [nombre]"));
      }
      var newProveedor = service.save(proveedor);
      return ResponseEntity.created(URI.create("/proveedor/" + newProveedor.getId()))
            .body(ApiResponse.of(newProveedor, "Proveedor registrado"));
   }

   @PutMapping("/proveedor/{id}")
   public ResponseEntity<ApiResponse<Proveedor>> updateProveedor(@PathVariable int id,
         @RequestBody Proveedor proveedor) {
      if (StringUtils.isBlank(proveedor.getNombre())) {
         return ResponseEntity.badRequest().body(ApiResponse.of(proveedor, "Falta el campo [nombre]"));
      }
      var proveedorUpdated = service.update(id, proveedor);
      return ResponseEntity.ok().body(ApiResponse.of(proveedorUpdated, "Proveedor Actualizado"));
   }

}
