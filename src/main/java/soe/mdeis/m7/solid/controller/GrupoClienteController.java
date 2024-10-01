package soe.mdeis.m7.solid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.common.util.StringUtils;

import java.net.URI;
import java.util.List;

import soe.mdeis.m7.solid.dto.ApiResponse;
import soe.mdeis.m7.solid.model.GrupoCliente;
import soe.mdeis.m7.solid.service.GrupoClienteService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api")
@CrossOrigin(value = "http://localhost:3000")
public class GrupoClienteController {

   @Autowired
   GrupoClienteService service;

   @PostMapping("/grupo-cliente")
   public ResponseEntity<ApiResponse<GrupoCliente>> registerGrupoCliente(@RequestBody GrupoCliente grupo) {
      if (StringUtils.isBlank(grupo.getNombre())) {
         return ResponseEntity.badRequest().body(
               ApiResponse.of(grupo, "Falta el campo [nombre]"));
      }
      GrupoCliente newGrupo = service.save(grupo);
      return ResponseEntity.created(URI.create("/grupo-cliente/" + grupo.getId())).body(
            ApiResponse.of(newGrupo, "GrupoCliente Registrado"));
   }

   @PutMapping("/grupo-cliente/{id}")
   public ResponseEntity<ApiResponse<GrupoCliente>> updateGrupoCliente(@PathVariable int id,
         @RequestBody GrupoCliente grupo) {
      if (StringUtils.isBlank(grupo.getNombre())) {
         return ResponseEntity.badRequest().body(ApiResponse.of(grupo, "Falta el campo [nombre]"));
      }
      GrupoCliente newGrupo = service.update(id, grupo);
      return ResponseEntity.ok().body(
            ApiResponse.of(newGrupo, "GrupoCliente Actualizado"));
   }

   @GetMapping("/grupo-cliente")
   public ResponseEntity<ApiResponse<List<GrupoCliente>>> getGruposClientes() {
      var list = service.getAll();
      return ResponseEntity.ok().body(ApiResponse.of(list, String.format("%d GrupoClientes", list.size())));
   }

}
