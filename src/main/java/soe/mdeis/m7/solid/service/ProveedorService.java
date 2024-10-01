package soe.mdeis.m7.solid.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soe.mdeis.m7.solid.model.Proveedor;
import soe.mdeis.m7.solid.repository.ProveedorRepository;

@Service
public class ProveedorService {

   @Autowired
   ProveedorRepository repository;

   public Proveedor save(Proveedor proveedor) {
      return this.repository.save(proveedor);
   }

   public List<Proveedor> getAll() {
      return this.repository.findAll();
   }

   public Proveedor update(int id, Proveedor proveedor) {
      Proveedor proveedorUpdated = new Proveedor(id, proveedor.getNombre());
      return this.repository.save(proveedorUpdated);
   }
}
