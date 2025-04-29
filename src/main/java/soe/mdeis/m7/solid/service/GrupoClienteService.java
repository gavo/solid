package soe.mdeis.m7.solid.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soe.mdeis.m7.solid.model.GrupoCliente;
import soe.mdeis.m7.solid.repository.GrupoClienteRepository;

@Service
public class GrupoClienteService {

   @Autowired
   GrupoClienteRepository repository;

   public GrupoCliente save(GrupoCliente grupoCliente) {
      return repository.save(grupoCliente);
   }

   public List<GrupoCliente> getAll() {
      return repository.findAll();
   }

   public GrupoCliente update(long id, GrupoCliente grupoCliente) {
      GrupoCliente grupo = new GrupoCliente(id, grupoCliente.getNombre(), grupoCliente.getDescuento());
      return repository.save(grupo);
   }
}
