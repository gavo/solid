package soe.mdeis.m7.solid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import soe.mdeis.m7.solid.model.Almacen;
import soe.mdeis.m7.solid.service.AlmacenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("api")
public class AlmacenController {

   @Autowired
   AlmacenService almacenService;

   @PostMapping("/almacen")
   public ResponseEntity<Almacen> postMethodName(@RequestBody Almacen almacen) {
      return new ResponseEntity<Almacen>(almacenService.save(almacen), HttpStatus.CREATED);
   }

   @GetMapping("/almacen")
   public ResponseEntity<List<Almacen>> getMethodName() {
      return new ResponseEntity<List<Almacen>>(almacenService.getAll(), HttpStatus.OK);
   }

}
