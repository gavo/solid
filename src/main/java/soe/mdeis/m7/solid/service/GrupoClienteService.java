package soe.mdeis.m7.solid.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soe.mdeis.m7.solid.model.GrupoCliente;
import soe.mdeis.m7.solid.repository.GrupoClienteRepository;

@Service
public class GrupoClienteService {

   @Autowired
   GrupoClienteRepository grupoClienteRepository;

   public GrupoCliente save(GrupoCliente grupoCliente) {
      return grupoClienteRepository.save(grupoCliente);
   }

   public List<GrupoCliente> getAll() {
      return grupoClienteRepository.findAll();
   }
}
