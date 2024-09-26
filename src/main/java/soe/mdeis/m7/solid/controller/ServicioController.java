package soe.mdeis.m7.solid.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import soe.mdeis.m7.solid.model.Servicio;
import soe.mdeis.m7.solid.service.ServicioService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("api")
public class ServicioController {

   @Autowired
   ServicioService service;

   @PostMapping("/servicio")
   public ResponseEntity<Servicio> postMethodName(@RequestBody Servicio servicio) {
      return new ResponseEntity<>(service.save(servicio), HttpStatus.CREATED);
   }

   @GetMapping("/servicio")
   public ResponseEntity<List<Servicio>> getMethodName() {
      return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
   }

}
