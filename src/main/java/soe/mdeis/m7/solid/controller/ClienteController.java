package soe.mdeis.m7.solid.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import soe.mdeis.m7.solid.model.Cliente;
import soe.mdeis.m7.solid.service.ClienteService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("api")
public class ClienteController {

   @Autowired
   ClienteService clienteService;

   @PostMapping("/clientes")
   public ResponseEntity<?> postMethodName(@RequestBody Cliente cliente) {
      return new ResponseEntity<Cliente>(clienteService.save(cliente), HttpStatus.CREATED);
   }

   @GetMapping("/clientes")
   public ResponseEntity<List<Cliente>> getMethodName() {
      return new ResponseEntity<List<Cliente>>(clienteService.getAll(), HttpStatus.OK);
   }

}
