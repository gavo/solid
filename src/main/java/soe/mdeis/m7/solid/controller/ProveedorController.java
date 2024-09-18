package soe.mdeis.m7.solid.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import soe.mdeis.m7.solid.model.Proveedor;
import soe.mdeis.m7.solid.service.ProveedorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api")
@CrossOrigin(value = "http://localhost:3000")
public class ProveedorController {

   private static final Logger logger = LoggerFactory.getLogger(ProveedorController.class);

   @Autowired
   private ProveedorService proveedorService;

   @GetMapping("/proveedor")
   public List<Proveedor> getAll() {
      var proveedores = proveedorService.getAll();
      proveedores.forEach(proveedor -> logger.info(proveedor.getNombre()));
      return proveedores;
   }

   @PostMapping("/proveedor")
   public Proveedor saveProveedor(@RequestBody Proveedor proveedor) {
      logger.info("Proveedor a agregar: " + proveedor.getNombre());
      return proveedorService.save(proveedor);
   }

}
