package soe.mdeis.m7.solid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.common.util.StringUtils;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import soe.mdeis.m7.solid.dto.ApiResponse;
import soe.mdeis.m7.solid.model.Almacen;
import soe.mdeis.m7.solid.service.AlmacenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api")
@CrossOrigin(value = "http://localhost:3000")
public class AlmacenController {

   @Autowired
   AlmacenService service;

   @PostMapping("/almacen")
   public ResponseEntity<ApiResponse<Almacen>> registerAlmacen(@RequestBody Almacen almacen) {
      if (StringUtils.isBlank(almacen.getNombre())) {
         return ResponseEntity.badRequest()
               .body(ApiResponse.of(almacen, "falta el campo [nombre]"));
      }
      Almacen newAlmacen = service.save(almacen);
      return ResponseEntity.created(URI.create("/almacen/" + newAlmacen.getId())).body(
            ApiResponse.of(newAlmacen, "Almacen Registrado"));
   }

   @PutMapping("/almacen/{id}")
   public ResponseEntity<ApiResponse<Almacen>> updateAlmacen(@PathVariable int id, @RequestBody Almacen almacen) {
      if (StringUtils.isBlank(almacen.getNombre())) {
         return ResponseEntity.badRequest().body(ApiResponse.of(almacen, "falta el campo [nombre]"));
      }

      Almacen newAlmacen = service.update(id, almacen);
      return ResponseEntity.ok().body(
            ApiResponse.of(newAlmacen, "Almacen Actualizado"));
   }

   @GetMapping("/almacen")
   public ResponseEntity<ApiResponse<List<Almacen>>> getAllAlmacenes() {
      var list = service.getAll();
      return ResponseEntity.ok().body(ApiResponse.of(list, String.format("%d Almacenes", list.size())));
   }

   @GetMapping("/almacen/{id}")
   public ResponseEntity<ApiResponse<Almacen>> getAlmacenById(@PathVariable int id) {
      Optional<Almacen> almacen = service.get(id);
      if (almacen.isEmpty()) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.of(null, "Almacen no Encontrado"));
      }
      return ResponseEntity.ok().body(ApiResponse.of(almacen.get(), "Almacen Encontrado"));
   }

   @DeleteMapping("/almacen/{id}")
   public ResponseEntity<Void> deleteAlmacen(@PathVariable int id) {
      service.delete(id);
      return ResponseEntity.noContent().build();
   }

}
