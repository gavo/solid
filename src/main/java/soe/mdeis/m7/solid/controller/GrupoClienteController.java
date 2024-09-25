package soe.mdeis.m7.solid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import soe.mdeis.m7.solid.model.GrupoCliente;
import soe.mdeis.m7.solid.service.GrupoClienteService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("api")
public class GrupoClienteController {

   @Autowired
   GrupoClienteService grupoClienteService;

   @PostMapping("/grupo-cliente")
   public ResponseEntity<GrupoCliente> postMethodName(@RequestBody GrupoCliente grupoCliente) {
      return new ResponseEntity<GrupoCliente>(grupoClienteService.save(grupoCliente), HttpStatus.CREATED);
   }

   @GetMapping("/grupo-cliente")
   public ResponseEntity<List<GrupoCliente>> getMethodName() {
      return new ResponseEntity<List<GrupoCliente>>(grupoClienteService.getAll(), HttpStatus.OK);
   }

}
