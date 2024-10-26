package soe.mdeis.m7.solid.controller;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.common.util.StringUtils;
import soe.mdeis.m7.solid.model.Proveedor;
import soe.mdeis.m7.solid.service.ProveedorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api")
public class ProveedorController {

   @Autowired
   private ProveedorService service;

   @GetMapping("/proveedor")
   public ResponseEntity<List<Proveedor>> getProveedores() {
      var proveedores = service.getAll();
      return ResponseEntity.ok()
            .body(proveedores);
   }

   @PostMapping("/proveedor")
   public ResponseEntity<Proveedor> registerProveedor(@RequestBody Proveedor proveedor) {
      if (StringUtils.isBlank(proveedor.getNombre())) {
         return ResponseEntity.badRequest().body(proveedor);
      }
      var newProveedor = service.save(proveedor);
      return ResponseEntity.created(URI.create("/proveedor/" + newProveedor.getId())).body(newProveedor);
   }

   @PutMapping("/proveedor/{id}")
   public ResponseEntity<Proveedor> updateProveedor(@PathVariable int id,
         @RequestBody Proveedor proveedor) {
      if (StringUtils.isBlank(proveedor.getNombre())) {
         return ResponseEntity.badRequest().body(proveedor);
      }
      var proveedorUpdated = service.update(id, proveedor);
      return ResponseEntity.ok().body(proveedorUpdated);
   }

}
