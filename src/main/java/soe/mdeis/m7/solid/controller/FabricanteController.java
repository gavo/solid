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

   @Autowired
   FabricanteService service;

   @GetMapping("/fabricante")
   public ResponseEntity<ApiResponse<List<Fabricante>>> getFabricantes() {
      var fabricantes = service.getAll();
      return ResponseEntity.ok().body(ApiResponse.of(fabricantes, String.format("%d Fabricantes", fabricantes.size())));
   }

   @PostMapping("/fabricante")
   public ResponseEntity<ApiResponse<Fabricante>> registerFabricante(@RequestBody Fabricante fabricante) {
      if (StringUtils.isBlank(fabricante.getNombre())) {
         return ResponseEntity.badRequest().body(
               ApiResponse.of(fabricante, "Falta el campo [nombre]"));
      }
      var newFabricante = service.save(fabricante);
      return ResponseEntity.created(URI.create("/fabricante/" + newFabricante.getId()))
            .body(ApiResponse.of(newFabricante, "Fabricante Registrado"));
   }

   @PutMapping("/fabricante/{id}")
   public ResponseEntity<ApiResponse<Fabricante>> updateFabricante(@PathVariable int id,
         @RequestBody Fabricante fabricante) {
      if (StringUtils.isBlank(fabricante.getNombre())) {
         return ResponseEntity.badRequest().body(
               ApiResponse.of(fabricante, "Falta el campo [nombre]"));
      }
      var fabricanteUpdated = service.update(id, fabricante);
      return ResponseEntity.ok().body(
            ApiResponse.of(fabricanteUpdated, "Fabricante Actualizado"));
   }

}
