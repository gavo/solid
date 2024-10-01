package soe.mdeis.m7.solid.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.common.util.StringUtils;
import soe.mdeis.m7.solid.dto.ApiResponse;
import soe.mdeis.m7.solid.model.Servicio;
import soe.mdeis.m7.solid.service.ServicioService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("api")
@CrossOrigin(value = "http://localhost:3000")
public class ServicioController {

   @Autowired
   ServicioService service;

   @PostMapping("/servicio")
   public ResponseEntity<ApiResponse<Servicio>> registerServicio(@RequestBody Servicio servicio) {
      if (StringUtils.isBlank(servicio.getNombre())) {
         return ResponseEntity.badRequest().body(ApiResponse.of(servicio, "Falta el campo [nombre]"));
      }
      if (StringUtils.isBlank(servicio.getCodigo())) {
         return ResponseEntity.badRequest().body(ApiResponse.of(servicio, "Falta el campo [codigo]"));
      }
      Servicio newServicio = service.save(servicio);
      return ResponseEntity.created(URI.create("/servicio/" + newServicio.getId()))
            .body(ApiResponse.of(newServicio, "Servicio Registrado"));
   }

   @GetMapping("/servicio")
   public ResponseEntity<ApiResponse<List<Servicio>>> getServicios() {
      var list = service.getAll();
      return ResponseEntity.ok()
            .body(ApiResponse.of(list, String.format("[%d] Servicios", list.size())));
   }

}
