package soe.mdeis.m7.solid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soe.mdeis.m7.solid.model.Cliente;
import soe.mdeis.m7.solid.repository.ClienteRepository;

@Service
public class ClienteService {

   @Autowired
   ClienteRepository clienteRepository;

   public Cliente save(Cliente cliente) {
      return clienteRepository.save(cliente);
   }
}
