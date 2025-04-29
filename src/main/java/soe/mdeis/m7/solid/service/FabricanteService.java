package soe.mdeis.m7.solid.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soe.mdeis.m7.solid.model.Fabricante;
import soe.mdeis.m7.solid.repository.FabricanteRepository;

@Service
public class FabricanteService {

   @Autowired
   FabricanteRepository repository;

   public Fabricante save(Fabricante fabricante) {
      return repository.save(fabricante);
   }

   public Fabricante update(long id, Fabricante fabricante) {
      Fabricante f = new Fabricante(id, fabricante.getNombre());
      return repository.save(f);
   }

   public List<Fabricante> getAll() {
      return repository.findAll();
   }

}
