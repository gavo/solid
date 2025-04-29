package soe.mdeis.m7.solid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soe.mdeis.m7.solid.model.Fabricante;
import soe.mdeis.m7.solid.model.GrupoProducto;
import soe.mdeis.m7.solid.service.FakeDataService;

import java.util.List;

@RestController
@RequestMapping("api/fake")
public class FakerController {

    @Autowired
    FakeDataService service;

    @GetMapping("/grupo-producto/{cantidad}")
    public ResponseEntity<List<GrupoProducto>> createFakeGrupoProductos(@PathVariable int cantidad){
        return ResponseEntity.ok(service.newFakesGrupoProducto(cantidad));
    }

    @GetMapping("/fabricante/{cantidad}")
    public ResponseEntity<List<Fabricante>> createFakeFabricantes(@PathVariable int cantidad) {
        return ResponseEntity.ok(service.newFakesFabricantes(cantidad));
    }
}
