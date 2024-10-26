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

import soe.mdeis.m7.solid.model.Almacen;
import soe.mdeis.m7.solid.service.AlmacenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api")
public class AlmacenController {

   @Autowired
   AlmacenService service;

   @PostMapping("/almacen")
   public ResponseEntity<Almacen> registerAlmacen(@RequestBody Almacen almacen) {
      if (StringUtils.isBlank(almacen.getNombre())) {
         return ResponseEntity.badRequest()
               .body(almacen);
      }
      Almacen newAlmacen = service.save(almacen);
      return ResponseEntity.created(URI.create("/almacen/" + newAlmacen.getId())).body(newAlmacen);
   }

   @PutMapping("/almacen/{id}")
   public ResponseEntity<Almacen> updateAlmacen(@PathVariable int id, @RequestBody Almacen almacen) {
      if (StringUtils.isBlank(almacen.getNombre())) {
         return ResponseEntity.badRequest().body(almacen);
      }

      Almacen newAlmacen = service.update(id, almacen);
      return ResponseEntity.ok().body(newAlmacen);
   }

   @GetMapping("/almacen")
   public ResponseEntity<List<Almacen>> getAllAlmacenes() {
      return ResponseEntity.ok().body(service.getAll());
   }

   @GetMapping("/almacen/{id}")
   public ResponseEntity<Almacen> getAlmacenById(@PathVariable int id) {
      Optional<Almacen> almacen = service.get(id);
      if (almacen.isEmpty()) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
      }
      return ResponseEntity.ok().body(almacen.get());
   }

   @DeleteMapping("/almacen/{id}")
   public ResponseEntity<Void> deleteAlmacen(@PathVariable int id) {
      service.delete(id);
      return ResponseEntity.noContent().build();
   }

}
