package soe.mdeis.m7.solid.service;

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
}
