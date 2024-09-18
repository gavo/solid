package soe.mdeis.m7.solid.service;

import java.util.List;

import org.springframework.stereotype.Service;

import soe.mdeis.m7.solid.model.GrupoProducto;
import soe.mdeis.m7.solid.repository.GrupoProductoRepository;

@Service
public class GrupoProductoService {

   private final GrupoProductoRepository grupoProductoRepository;

   public GrupoProductoService(GrupoProductoRepository grupoProductoRepository) {
      this.grupoProductoRepository = grupoProductoRepository;
   }

   public GrupoProducto save(GrupoProducto grupoProducto) {
      return this.grupoProductoRepository.save(grupoProducto);
   }

   public List<GrupoProducto> getAll() {
      return this.grupoProductoRepository.findAll();
   }
}
