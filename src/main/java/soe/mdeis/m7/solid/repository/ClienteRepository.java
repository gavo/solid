package soe.mdeis.m7.solid.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import soe.mdeis.m7.solid.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
