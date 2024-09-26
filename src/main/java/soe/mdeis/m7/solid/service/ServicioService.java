package soe.mdeis.m7.solid.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soe.mdeis.m7.solid.model.Servicio;
import soe.mdeis.m7.solid.repository.ServicioRepository;

@Service
public class ServicioService {
   @Autowired
   ServicioRepository repo;

   public Servicio save(Servicio servicio) {
      return repo.save(servicio);
   }

   public List<Servicio> getAll() {
      return repo.findAll();
   }
}
