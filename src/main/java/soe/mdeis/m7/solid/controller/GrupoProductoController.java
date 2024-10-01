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
import soe.mdeis.m7.solid.model.GrupoProducto;
import soe.mdeis.m7.solid.service.GrupoProductoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api")
@CrossOrigin(value = "http://localhost:3000")
public class GrupoProductoController {

   @Autowired
   private GrupoProductoService service;

   @GetMapping("/grupo-producto")
   public ResponseEntity<ApiResponse<List<GrupoProducto>>> getGruposProductos() {
      var list = service.getAll();
      return ResponseEntity.ok()
            .body(ApiResponse.of(list, String.format("[%d] GrupoProductos", list.size())));
   }

   @PostMapping("/grupo-producto")
   public ResponseEntity<ApiResponse<GrupoProducto>> registerGrupoProducto(@RequestBody GrupoProducto grupoProducto) {
      if (StringUtils.isBlank(grupoProducto.getNombre())) {
         return ResponseEntity.badRequest()
               .body(ApiResponse.of(grupoProducto, "Falta el campo [nombre]"));
      }
      var newGrupo = service.save(grupoProducto);
      return ResponseEntity.created(URI.create("/grupo-producto/" + newGrupo.getId()))
            .body(ApiResponse.of(newGrupo, "GrupoCliente Registrado"));
   }

   @PutMapping("/grupo-producto/{id}")
   public ResponseEntity<ApiResponse<GrupoProducto>> updateGrupoProducto(@PathVariable int id,
         @RequestBody GrupoProducto grupoProducto) {
      if (StringUtils.isBlank(grupoProducto.getNombre())) {
         return ResponseEntity.badRequest()
               .body(ApiResponse.of(grupoProducto, "Falta el campo [nombre]"));
      }
      var grupoUpdated = service.update(id, grupoProducto);
      return ResponseEntity.ok().body(ApiResponse.of(grupoUpdated, "GrupoCliente Actualizado"));
   }
}
