package soe.mdeis.m7.solid.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soe.mdeis.m7.solid.model.Almacen;
import soe.mdeis.m7.solid.repository.AlmacenRepository;

@Service
public class AlmacenService {

   @Autowired
   AlmacenRepository repository;

   public Almacen update(long id, Almacen almacen) {
      Almacen newAlmacen = new Almacen(id, almacen.getNombre());
      return repository.save(newAlmacen);
   }

   public Almacen save(Almacen almacen) {
      return repository.save(almacen);
   }

   public List<Almacen> getAll() {
      return repository.findAll();
   }

   public Optional<Almacen> get(long id) {
      return repository.findById(id);
   }

   public void delete(long id) {
      repository.deleteById(id);
   }

}
