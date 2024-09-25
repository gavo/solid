package soe.mdeis.m7.solid.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import soe.mdeis.m7.solid.model.Cliente;
import soe.mdeis.m7.solid.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api")
public class ClienteController {

   @Autowired
   ClienteService clienteService;

   @PostMapping("/clientes")
   public ResponseEntity<?> postMethodName(@RequestBody Cliente cliente) {
      return new ResponseEntity<Cliente>(clienteService.save(cliente), HttpStatus.CREATED);
   }

}
