package soe.mdeis.m7.solid.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soe.mdeis.m7.solid.model.Cliente;
import soe.mdeis.m7.solid.repository.ClienteRepository;

@Service
public class ClienteService {

   @Autowired
   ClienteRepository repository;

   public Cliente save(Cliente cliente) {
      return repository.save(cliente);
   }

   public List<Cliente> getAll() {
      return repository.findAll();
   }

   public Optional<Cliente> get(int id) {
      return repository.findById(id);
   }

    public Cliente updateCliente(int id, Cliente cliente) {
      Cliente uCliente = new Cliente();
      uCliente.setId(id);
      uCliente.setNombre(cliente.getNombre());
      uCliente.setGrupoCliente(cliente.getGrupoCliente());
      uCliente.setCode(cliente.getCode());
      uCliente.setEmail(cliente.getEmail());
      uCliente.setTipoDocumento(cliente.getTipoDocumento());
      uCliente.setDocumento(cliente.getDocumento());
      return repository.save(cliente);
    }
}
