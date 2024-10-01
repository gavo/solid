package soe.mdeis.m7.solid.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import soe.mdeis.m7.solid.model.Venta;
import soe.mdeis.m7.solid.service.VentaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("api")
@CrossOrigin(value = "http://localhost:300")
public class VentaController {

   @Autowired
   VentaService service;

   @PostMapping("/venta")
   public ResponseEntity<Venta> registerSale(@RequestBody Venta venta) {
      var sale = service.save(venta);
      return ResponseEntity.created(URI.create("/venta/" + sale.getId())).body(sale);
   }

   @GetMapping("/venta")
   public ResponseEntity<List<Venta>> getAllVentas() {
      return ResponseEntity.ok().body(service.getAllVentas());
   }

}
