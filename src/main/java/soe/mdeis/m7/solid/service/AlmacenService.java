package soe.mdeis.m7.solid.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soe.mdeis.m7.solid.model.Almacen;
import soe.mdeis.m7.solid.repository.AlmacenRepository;

@Service
public class AlmacenService {

   @Autowired
   AlmacenRepository almacenRepository;

   public Almacen save(Almacen almacen) {
      return almacenRepository.save(almacen);
   }

   public List<Almacen> getAll() {
      return almacenRepository.findAll();
   }

}
