package soe.mdeis.m7.solid.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import soe.mdeis.m7.solid.model.GrupoCliente;
import soe.mdeis.m7.solid.repository.GrupoClienteRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test Grupo Cliente Service")
class GrupoClienteServiceTest {

   @Mock
   GrupoClienteRepository repository;

   @InjectMocks
   GrupoClienteService service;

   @Test
   @DisplayName("Grupo Cliente Service save Grupo Cliente")
   void grupoClienteServiceSaveGrupoCliente() {
      Mockito.when(repository.save(Mockito.any(GrupoCliente.class)))
            .thenReturn(new GrupoCliente(1l, "Grupo CLiente 1", BigDecimal.TEN));
      GrupoCliente grupo = service.save(new GrupoCliente());
      assertEquals(1, grupo.getId());
      assertEquals("Grupo CLiente 1", grupo.getNombre());
   }

   @Test
   @DisplayName("Grupo Cliente Service Update Grupo Cliente")
   void grupoClienteServiceUpdateGrupoCliente() {
      Mockito.when(repository.save(Mockito.any(GrupoCliente.class)))
            .thenReturn(new GrupoCliente(1l, "Grupo CLiente 1", BigDecimal.TEN));
      GrupoCliente grupo = service.update(1, new GrupoCliente());
      assertEquals(1, grupo.getId());
      assertEquals("Grupo CLiente 1", grupo.getNombre());
   }

   @Test
   @DisplayName("Grupo Cliente Service Get All Grupos Clientes")
   void grupoClienteServiceGetAllGruposClientes() {
      Mockito.when(repository.findAll()).thenReturn(
            Arrays.asList(new GrupoCliente(), new GrupoCliente()));
      var list = service.getAll();
      assertTrue(!list.isEmpty());
      assertEquals(2, list.size());
   }
}
