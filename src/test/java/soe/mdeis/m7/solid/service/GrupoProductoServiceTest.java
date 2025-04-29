package soe.mdeis.m7.solid.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import soe.mdeis.m7.solid.model.GrupoProducto;
import soe.mdeis.m7.solid.repository.GrupoProductoRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test Grupo Producto Service")
class GrupoProductoServiceTest {

   @Mock
   GrupoProductoRepository repository;

   @InjectMocks
   GrupoProductoService service;

   @Test
   @DisplayName("Grupo Producto Service update Grupo Producto")
   void grupoProductoServiceUpdateGrupoProducto() {
      Mockito.when(repository.save(Mockito.any(GrupoProducto.class)))
            .thenReturn(new GrupoProducto(1l, "Grupo 1"));
      var grupo = service.update(1, new GrupoProducto());
      assertEquals(1, grupo.getId());
      assertEquals("Grupo 1", grupo.getNombre());
   }

   @Test
   @DisplayName("Grupo Producto Service save Grupo Producto")
   void grupoProductoServiceSaveGrupoProducto() {
      Mockito.when(repository.save(Mockito.any(GrupoProducto.class)))
            .thenReturn(new GrupoProducto(1l, "Grupo 1"));

      var grupo = service.save(new GrupoProducto());
      assertEquals(1l, grupo.getId());
      assertEquals("Grupo 1", grupo.getNombre());
   }

   @Test
   @DisplayName("Grupo Producto Service getAll Grupos Productos")
   void grupoProductoServiceGetAllGruposProductos() {
      Mockito.when(repository.findAll())
            .thenReturn(Arrays.asList(new GrupoProducto(), new GrupoProducto(), new GrupoProducto()));
      var list = service.getAll();

      assertFalse(list.isEmpty());
      assertEquals(3, list.size());
   }
}
