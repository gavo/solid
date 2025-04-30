package soe.mdeis.m7.solid.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import soe.mdeis.m7.solid.model.Proveedor;

import java.util.Optional;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

    Optional<Proveedor> findByNombre(String nombre);

}
