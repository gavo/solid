package soe.mdeis.m7.solid.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import soe.mdeis.m7.solid.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
