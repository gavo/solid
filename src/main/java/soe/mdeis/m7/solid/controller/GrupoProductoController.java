package soe.mdeis.m7.solid.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import soe.mdeis.m7.solid.model.GrupoProducto;
import soe.mdeis.m7.solid.service.GrupoProductoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api")
@CrossOrigin(value = "http://localhost:3000")
public class GrupoProductoController {

   private static final Logger logger = LoggerFactory.getLogger(GrupoProductoController.class);

   @Autowired
   private GrupoProductoService grupoProductoService;

   @GetMapping("/grupo-productos")
   public List<GrupoProducto> getGrupos() {
      var grupoProductos = grupoProductoService.getAll();
      grupoProductos.forEach((grupo) -> logger.info(grupo.getNombre()));
      return grupoProductos;
   }

   @PostMapping("/grupo-productos")
   public GrupoProducto saveGrupoProductos(@RequestBody GrupoProducto grupoProducto) {
      logger.info("Grupo Productos a agregar: " + grupoProducto.getNombre());
      return grupoProductoService.save(grupoProducto);
   }

}
