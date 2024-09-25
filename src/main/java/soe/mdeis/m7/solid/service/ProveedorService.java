package soe.mdeis.m7.solid.service;

import java.util.List;

import org.springframework.stereotype.Service;

import soe.mdeis.m7.solid.model.Proveedor;
import soe.mdeis.m7.solid.repository.ProveedorRepository;

@Service
public class ProveedorService {

   private final ProveedorRepository proveedorRepository;

   public ProveedorService(ProveedorRepository proveedorRepository) {
      this.proveedorRepository = proveedorRepository;
   }

   public Proveedor save(Proveedor proveedor) {
      return this.proveedorRepository.save(proveedor);
   }

   public List<Proveedor> getAll() {
      return this.proveedorRepository.findAll();
   }

   public Proveedor update(int id, Proveedor proveedor) {
      Proveedor proveedorUpdated = new Proveedor(id, proveedor.getNombre());
      return this.proveedorRepository.save(proveedorUpdated);
   }
}
