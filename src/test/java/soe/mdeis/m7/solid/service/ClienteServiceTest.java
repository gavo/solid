package soe.mdeis.m7.solid.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import soe.mdeis.m7.solid.model.Cliente;
import soe.mdeis.m7.solid.model.TipoDocumento;
import soe.mdeis.m7.solid.repository.ClienteRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test Cliente Service")
class ClienteServiceTest {

   @Mock
   ClienteRepository repository;

   @InjectMocks
   ClienteService service;

   @Test
   @DisplayName("Cliente Service Save Cliente")
   void clienteServiceSaveCliente() {
      Cliente cliente = new Cliente(1l, "Juan Perez", "1234", "123456", TipoDocumento.CI, "email@email.com", null);
      Mockito.when(repository.save(Mockito.any(Cliente.class))).thenReturn(cliente);
      Cliente newCliente = service.save(new Cliente());
      assertEquals(1, newCliente.getId());
   }

   @Test
   @DisplayName("Cliente Service Get Cliente")
   void clienteServiceGetCliente() {
      Cliente cliente = new Cliente(1l, "Juan Perez", "1234", "123456", TipoDocumento.CI, "email@email.com", null);
      Mockito.when(repository.findById(1l)).thenReturn(Optional.of(cliente));
      Optional<Cliente> newCliente = service.get(1);
      assertEquals(1, newCliente.get().getId());
   }

   @Test
   @DisplayName("Cliente Service Get All Clientes")
   void clienteServiceGetAllClientes() {
      Mockito.when(repository.findAll()).thenReturn(Arrays.asList(
            new Cliente(1l, "Juan Perez", "1234", "123456", TipoDocumento.CI, "email@email.com", null),
            new Cliente(2l, "Juan Perez2", "1234", "123456", TipoDocumento.CI, "email@email.com", null)));
      var clientes = service.getAll();
      assertEquals(2, clientes.size());
      assertEquals(1l, clientes.get(0).getId());
      assertEquals(2l, clientes.get(1).getId());
   }

   @Test
   @DisplayName("Cliente Service Update Cliente")
   void clienteServiceUpdateCliente() {
      Cliente cliente = new Cliente(1l, "Juan Perez", "1234", "123456", TipoDocumento.CI, "email@email.com", null);
      Mockito.when(repository.save(Mockito.any(Cliente.class))).thenReturn(cliente);
      Cliente newCliente = service.updateCliente(1, new Cliente());
      assertEquals(1, newCliente.getId());
   }
}
