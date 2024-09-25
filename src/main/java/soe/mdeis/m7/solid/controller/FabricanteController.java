package soe.mdeis.m7.solid.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.common.util.StringUtils;
import soe.mdeis.m7.solid.model.Fabricante;
import soe.mdeis.m7.solid.service.FabricanteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api")
@CrossOrigin(value = "http://localhost:3000")
public class FabricanteController {
   private static final Logger logger = LoggerFactory.getLogger(FabricanteController.class);

   @Autowired
   private FabricanteService fabricanteService;

   @GetMapping("/fabricante")
   public ResponseEntity<?> getFabricante() {
      var fabricantes = fabricanteService.getAll();
      fabricantes.forEach((fabricante) -> logger.info(fabricante.getNombre()));
      return ResponseEntity.ok().body(fabricantes);
   }

   @PostMapping("/fabricante")
   public ResponseEntity<?> saveFabricante(@RequestBody Fabricante fabricante) {
      if (StringUtils.isBlank(fabricante.getNombre())) {
         return ResponseEntity.badRequest().body("Debe especificar un nombre para el Fabricante");
      }
      logger.info("Fabricante a agregar: " + fabricante);
      var newFabricante = fabricanteService.save(fabricante);
      return ResponseEntity.ok().body(newFabricante);
   }

   @PutMapping("/fabricante/{id}")
   public ResponseEntity<?> putFabricante(@PathVariable int id, @RequestBody Fabricante fabricante) {
      if (StringUtils.isBlank(fabricante.getNombre())) {
         return ResponseEntity.badRequest().body("Debe especificar un nombre para el Fabricante");
      }
      var fabricanteSaved = fabricanteService.update(id, fabricante);
      logger.info(String.format("Fabricante [%d][%s] actualizado", id, fabricante.getNombre()));
      return ResponseEntity.ok().body(fabricanteSaved);
   }

}
