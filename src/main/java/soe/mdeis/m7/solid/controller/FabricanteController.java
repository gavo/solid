package soe.mdeis.m7.solid.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
public class FabricanteController {

   @Autowired
   FabricanteService service;

   @GetMapping("/fabricante")
   public ResponseEntity<List<Fabricante>> getFabricantes() {
      var fabricantes = service.getAll();
      return ResponseEntity.ok().body(fabricantes);
   }

   @PostMapping("/fabricante")
   public ResponseEntity<Fabricante> registerFabricante(@RequestBody Fabricante fabricante) {
      if (StringUtils.isBlank(fabricante.getNombre())) {
         return ResponseEntity.badRequest().body(fabricante);
      }
      var newFabricante = service.save(fabricante);
      return ResponseEntity.created(URI.create("/fabricante/" + newFabricante.getId()))
            .body(newFabricante);
   }

   @PutMapping("/fabricante/{id}")
   public ResponseEntity<Fabricante> updateFabricante(@PathVariable int id,
         @RequestBody Fabricante fabricante) {
      if (StringUtils.isBlank(fabricante.getNombre())) {
         return ResponseEntity.badRequest().body(fabricante);
      }
      var fabricanteUpdated = service.update(id, fabricante);
      return ResponseEntity.ok().body(fabricanteUpdated);
   }

}
