package soe.mdeis.m7.solid.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.common.util.StringUtils;
import soe.mdeis.m7.solid.model.GrupoProducto;
import soe.mdeis.m7.solid.service.GrupoProductoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api")
@CrossOrigin(value = "http://localhost:3000")
public class GrupoProductoController {

   private static final Logger logger = LoggerFactory.getLogger(GrupoProductoController.class);

   @Autowired
   private GrupoProductoService grupoProductoService;

   @GetMapping("/grupo-productos")
   public ResponseEntity<?> getGrupos() {
      var grupoProductos = grupoProductoService.getAll();
      grupoProductos.forEach((grupo) -> logger.info(grupo.getNombre()));
      return ResponseEntity.ok().body(grupoProductos);
   }

   @PostMapping("/grupo-productos")
   public ResponseEntity<?> saveGrupoProductos(@RequestBody GrupoProducto grupoProducto) {
      if (StringUtils.isBlank(grupoProducto.getNombre())) {
         return ResponseEntity.badRequest().body("Debe asignar un nombre para el grupo de productos");
      }
      logger.info("Grupo Productos a agregar: " + grupoProducto.getNombre());
      var newGrupo = grupoProductoService.save(grupoProducto);
      return ResponseEntity.ok().body(newGrupo);
   }

   @PutMapping("/grupo-productos/{id}")
   public ResponseEntity<?> putMethodName(@PathVariable int id, @RequestBody GrupoProducto grupoProducto) {
      if (StringUtils.isBlank(grupoProducto.getNombre())) {
         return ResponseEntity.badRequest().body("Debe especificar un nombre para el grupo de productos");
      }
      var grupoUpdated = grupoProductoService.update(id, grupoProducto);
      logger.info(String.format("GrupoProducto [%d][%s] actualizado", id, grupoProducto.getNombre()));
      return ResponseEntity.ok().body(grupoUpdated);
   }
}
