package soe.mdeis.m7.solid.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.common.util.StringUtils;
import soe.mdeis.m7.solid.dto.ApiResponse;
import soe.mdeis.m7.solid.model.Cliente;
import soe.mdeis.m7.solid.service.ClienteService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api")
@CrossOrigin(value = "http://localhost:3000")
public class ClienteController {

   @Autowired
   ClienteService service;

   @PostMapping("/cliente")
   public ResponseEntity<ApiResponse<Cliente>> registerCliente(@RequestBody Cliente cliente) {
      if (StringUtils.isBlank(cliente.getNombre())) {
         return ResponseEntity.badRequest().body(
               ApiResponse.of(cliente, "Falta el campo [nombre]"));
      }
      if (StringUtils.isBlank(cliente.getDocumento())) {
         return ResponseEntity.badRequest().body(
               ApiResponse.of(cliente, "Falta el campo [documento]"));
      }
      if (cliente.getTipoDocumento() == null) {
         return ResponseEntity.badRequest().body(ApiResponse.of(cliente, "Falta el campo [tipoDocumento(NIT|CI)]"));
      }
      if (StringUtils.isBlank(cliente.getEmail())) {
         return ResponseEntity.badRequest().body(
               ApiResponse.of(cliente, "Falta el campo [email]"));
      }
      if (StringUtils.isBlank(cliente.getCode())) {
         return ResponseEntity.badRequest().body(
               ApiResponse.of(cliente, "Falta el campo [code]"));
      }
      Cliente newCliente = service.save(cliente);
      return ResponseEntity.created(URI.create("/cliente/" + newCliente.getId())).body(
            ApiResponse.of(newCliente, "Cliente Registrado"));
   }

   @GetMapping("/cliente")
   public ResponseEntity<ApiResponse<List<Cliente>>> getClientes() {
      var list = service.getAll();
      return ResponseEntity.ok()
            .body(ApiResponse.of(list, String.format("[%d] Clientes", list.size())));
   }

   @GetMapping("/cliente/{id}")
   public ResponseEntity<ApiResponse<Cliente>> getClienteById(@PathVariable int id) {
      Optional<Cliente> cliente = service.get(id);
      if (cliente.isPresent()) {
         return ResponseEntity.ok().body(ApiResponse.of(cliente.get(), "Cliente Encontrado"));
      }
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.of(null, "Cliente no encontrado"));
   }

}
