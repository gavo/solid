package soe.mdeis.m7.solid.controller;

import org.springframework.web.bind.annotation.*;

import io.micrometer.common.util.StringUtils;
import soe.mdeis.m7.solid.model.Cliente;
import soe.mdeis.m7.solid.service.ClienteService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("api")
public class ClienteController {

   @Autowired
   ClienteService service;

   @PostMapping("/cliente")
   public ResponseEntity<Cliente> registerCliente(@RequestBody Cliente cliente) {
      var response = verifyFields(cliente);
      if (response != null)
         return response;
      Cliente newCliente = service.save(cliente);
      return ResponseEntity.created(URI.create("/cliente/" + newCliente.getId())).body(newCliente);
   }

   private ResponseEntity<Cliente> verifyFields(Cliente cliente) {
      if (StringUtils.isBlank(cliente.getNombre())) {
         return ResponseEntity.badRequest().body(cliente);
      }
      if (StringUtils.isBlank(cliente.getDocumento())) {
         return ResponseEntity.badRequest().body(cliente);
      }
      if (cliente.getTipoDocumento() == null) {
         return ResponseEntity.badRequest().body(cliente);
      }
      if (StringUtils.isBlank(cliente.getEmail())) {
         return ResponseEntity.badRequest().body(cliente);
      }
      if (StringUtils.isBlank(cliente.getCode())) {
         return ResponseEntity.badRequest().body(cliente);
      }
      return null;
   }

   @PutMapping("/cliente/{id}")
   public ResponseEntity<Cliente> updateCliente(@PathVariable int id, @RequestBody Cliente cliente) {
      var response = verifyFields(cliente);
      if (response != null)
         return response;
      return ResponseEntity.ok().body(service.updateCliente(id, cliente));
   }

   @GetMapping("/cliente")
   public ResponseEntity<List<Cliente>> getClientes() {
      return ResponseEntity.ok().body(service.getAll());
   }

   @GetMapping("/cliente/{id}")
   public ResponseEntity<Cliente> getClienteById(@PathVariable int id) {
      Optional<Cliente> cliente = service.get(id);
      if (cliente.isPresent()) {
         return ResponseEntity.ok().body(cliente.get());
      }
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
   }

}
