package soe.mdeis.m7.solid.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soe.mdeis.m7.solid.model.GrupoProducto;
import soe.mdeis.m7.solid.repository.GrupoProductoRepository;

@Service
public class GrupoProductoService {

   @Autowired
   GrupoProductoRepository repository;

   public GrupoProducto save(GrupoProducto grupoProducto) {
      return this.repository.save(grupoProducto);
   }

   public List<GrupoProducto> getAll() {
      return this.repository.findAll();
   }

   public GrupoProducto update(long id, GrupoProducto grupoProducto) {
      GrupoProducto updated = new GrupoProducto(id, grupoProducto.getNombre());
      return this.repository.save(updated);
   }
}
