package soe.mdeis.m7.solid.controller;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.common.util.StringUtils;
import soe.mdeis.m7.solid.model.GrupoProducto;
import soe.mdeis.m7.solid.service.GrupoProductoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api")
public class GrupoProductoController {

   @Autowired
   private GrupoProductoService service;

   @GetMapping("/grupo-producto")
   public ResponseEntity<List<GrupoProducto>> getGruposProductos() {
      return ResponseEntity.ok().body(service.getAll());
   }

   @PostMapping("/grupo-producto")
   public ResponseEntity<GrupoProducto> registerGrupoProducto(@RequestBody GrupoProducto grupoProducto) {
      if (StringUtils.isBlank(grupoProducto.getNombre())) {
         return ResponseEntity.badRequest().body(grupoProducto);
      }
      var newGrupo = service.save(grupoProducto);
      return ResponseEntity.created(URI.create("/grupo-producto/" + newGrupo.getId())).body(newGrupo);
   }

   @PutMapping("/grupo-producto/{id}")
   public ResponseEntity<GrupoProducto> updateGrupoProducto(@PathVariable int id,
         @RequestBody GrupoProducto grupoProducto) {
      if (StringUtils.isBlank(grupoProducto.getNombre())) {
         return ResponseEntity.badRequest().body(grupoProducto);
      }
      var grupoUpdated = service.update(id, grupoProducto);
      return ResponseEntity.ok().body(grupoUpdated);
   }
}
