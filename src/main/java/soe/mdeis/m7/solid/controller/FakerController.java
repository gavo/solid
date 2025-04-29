package soe.mdeis.m7.solid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soe.mdeis.m7.solid.model.*;
import soe.mdeis.m7.solid.service.FakeDataService;

import java.util.List;

@RestController
@RequestMapping("api/fake")
public class FakerController {

    @Autowired
    FakeDataService service;

    @GetMapping("/grupo-cliente")
    public ResponseEntity<List<GrupoCliente>> createFakeGrupoClientes(){
        return ResponseEntity.ok(service.newFakeGrupoClientes());
    }

    @GetMapping("/servicio/{cantidad}")
    public ResponseEntity<List<Servicio>> createFakeServicios(@PathVariable int cantidad){
        return ResponseEntity.ok(service.newFakeServicios((cantidad)));
    }

    @GetMapping("/producto/{cantidad}")
    public ResponseEntity<List<Producto>> createFakeProductos(@PathVariable int cantidad){
        return ResponseEntity.ok(service.newFakeProductos((cantidad)));
    }

    @GetMapping("/proveedor/{cantidad}")
    public ResponseEntity<List<Proveedor>> createFakeProveedores(@PathVariable int cantidad){
        return ResponseEntity.ok(service.newFakesProveedor((cantidad)));
    }

    @GetMapping("/grupo-producto/{cantidad}")
    public ResponseEntity<List<GrupoProducto>> createFakeGrupoProductos(@PathVariable int cantidad){
        return ResponseEntity.ok(service.newFakesGrupoProducto(cantidad));
    }

    @GetMapping("/fabricante/{cantidad}")
    public ResponseEntity<List<Fabricante>> createFakeFabricantes(@PathVariable int cantidad) {
        return ResponseEntity.ok(service.newFakesFabricantes(cantidad));
    }
}
