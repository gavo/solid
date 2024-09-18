package soe.mdeis.m7.solid.service;

import java.util.List;

import org.springframework.stereotype.Service;

import soe.mdeis.m7.solid.model.Fabricante;
import soe.mdeis.m7.solid.repository.FabricanteRepository;

@Service
public class FabricanteService {

   private final FabricanteRepository fabricanteRepository;

   public FabricanteService(FabricanteRepository fabricanteRepository) {
      this.fabricanteRepository = fabricanteRepository;
   }

   public Fabricante save(Fabricante fabricante) {
      return fabricanteRepository.save(fabricante);
   }

   public List<Fabricante> getAll() {
      return fabricanteRepository.findAll();
   }
}