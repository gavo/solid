package soe.mdeis.m7.solid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soe.mdeis.m7.solid.model.Fabricante;
import soe.mdeis.m7.solid.service.FakeDataService;

import java.util.List;

@RestController
@RequestMapping("api/fake")
public class FakerController {

    @Autowired
    FakeDataService service;

    @PostMapping("/fabricante/{cantidad}")
    public ResponseEntity<List<Fabricante>> createFakeFabricantes(@PathVariable int cantidad){
        return ResponseEntity.ok(service.newFakesFabricantes(cantidad));
    }
}
