package soe.mdeis.m7.solid.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import soe.mdeis.m7.solid.model.GrupoProducto;

import java.util.Optional;

public interface GrupoProductoRepository extends JpaRepository<GrupoProducto, Long> {

    Optional<GrupoProducto> findByNombre(String nombre);

}
