package soe.mdeis.m7.solid.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import soe.mdeis.m7.solid.model.Fabricante;
import soe.mdeis.m7.solid.service.FabricanteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api")
@CrossOrigin(value = "http://localhost:3000")
public class FabricanteController {
   private static final Logger logger = LoggerFactory.getLogger(FabricanteController.class);

   @Autowired
   private FabricanteService fabricanteService;

   @GetMapping("/fabricante")
   public List<Fabricante> getFabricante() {
      var fabricantes = fabricanteService.getAll();
      fabricantes.forEach((fabricante) -> logger.info(fabricante.getNombre()));
      return fabricantes;
   }

   @PostMapping("/fabricante")
   public Fabricante saveFabricante(@RequestBody Fabricante fabricante) {
      logger.info("Fabricante a agregar: " + fabricante);
      return fabricanteService.save(fabricante);
   }

}
