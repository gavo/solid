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

import soe.mdeis.m7.solid.model.Proveedor;
import soe.mdeis.m7.solid.repository.ProveedorRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test Proveedor Service")
class ProveedorServiceTest {

   @Mock
   ProveedorRepository repository;

   @InjectMocks
   ProveedorService service;

   @Test
   @DisplayName("Proveedor Service Save Proveedor")
   void proveedorServiceSaveProveedor() {
      Mockito.when(repository.save(Mockito.any(Proveedor.class)))
            .thenReturn(new Proveedor(1l, "Proveedor 1"));
      Proveedor proveedor = service.save(new Proveedor());
      assertEquals(1, proveedor.getId());
      assertEquals("Proveedor 1", proveedor.getNombre());
   }

   @Test
   @DisplayName("Proveedor Service get All proveedores")
   void proveedorServiceGetAllProveedores() {
      Mockito.when(repository.findAll()).thenReturn(Arrays.asList(
            new Proveedor(1l, "Proveedor 1"),
            new Proveedor(2l, "Proveedor 2"),
            new Proveedor(3l, "Proveedor 3")));
      var list = service.getAll();
      assertFalse(list.isEmpty());
      assertEquals(3, list.size());
   }

   @Test
   @DisplayName("Proveedor Service update proveedor")
   void proveedorServiceUpdateProveedor() {
      Mockito.when(repository.save(Mockito.any(Proveedor.class)))
            .thenReturn(new Proveedor(1l, "Proveedor 1"));
      Proveedor proveedor = service.update(1, new Proveedor());
      assertEquals(1l, proveedor.getId());
      assertEquals("Proveedor 1", proveedor.getNombre());
   }
}
