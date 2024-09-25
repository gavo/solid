package soe.mdeis.m7.solid.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(value = "http://localhost:3000")
public class ProveedorController {

   private static final Logger logger = LoggerFactory.getLogger(ProveedorController.class);

   @Autowired
   private ProveedorService proveedorService;

   @GetMapping("/proveedor")
   public ResponseEntity<?> getAll() {
      var proveedores = proveedorService.getAll();
      proveedores.forEach(proveedor -> logger.info(proveedor.getNombre()));
      return ResponseEntity.ok().body(proveedores);
   }

   @PostMapping("/proveedor")
   public ResponseEntity<?> saveProveedor(@RequestBody Proveedor proveedor) {
      if (StringUtils.isBlank(proveedor.getNombre())) {
         return ResponseEntity.badRequest().body("Debe especificar el nombre del proveedor");
      }
      logger.info("Proveedor a agregar: " + proveedor.getNombre());
      var newProveedor = proveedorService.save(proveedor);
      return ResponseEntity.ok().body(newProveedor);
   }

   @PutMapping("/proveedor/{id}")
   public ResponseEntity<?> putMethodName(@PathVariable int id, @RequestBody Proveedor proveedor) {
      if (StringUtils.isBlank(proveedor.getNombre())) {
         return ResponseEntity.badRequest().body("null");
      }
      var proveedorUpdated = proveedorService.update(id, proveedor);
      logger.info(String.format("Proveedor [%d][%s] Actualizado", id, proveedorUpdated.getNombre()));
      return ResponseEntity.ok().body(proveedorUpdated);
   }

}
