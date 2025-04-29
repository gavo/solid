package soe.mdeis.m7.solid.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import soe.mdeis.m7.solid.model.Fabricante;

import java.util.Optional;

public interface FabricanteRepository extends JpaRepository<Fabricante, Long> {

    Optional<Fabricante> findByNombre(String nombre);

}
