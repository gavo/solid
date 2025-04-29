package soe.mdeis.m7.solid.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import soe.mdeis.m7.solid.model.Producto;
import soe.mdeis.m7.solid.repository.ProductoRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test Producto Service")
class ProductoServiceTest {

   @Mock
   ProductoRepository repository;

   @InjectMocks
   ProductoService service;

   @Test
   @DisplayName("Producto Service Update Producto")
   void productoServiceUpdateProducto() {
      Mockito.when(repository.save(Mockito.any(Producto.class)))
            .thenReturn(new Producto(1l, "Galleta", "Cookie", 1, "111", "lbs", BigDecimal.ONE, null, null, null, null));
      var producto = service.update(1l, new Producto());
      assertEquals(1l, producto.getId());
      assertEquals("Galleta", producto.getNombre());
      assertEquals("Cookie", producto.getNombreExtranjero());
   }

   @Test
   @DisplayName("Producto Service save Producto")
   void productoServiceSaveProducto() {
      Mockito.when(repository.save(Mockito.any(Producto.class)))
            .thenReturn(new Producto(1l, "Galleta", "Cookie", 1, "111", "lbs", BigDecimal.ONE, null, null, null, null));
      var producto = service.save(new Producto());
      assertEquals(1l, producto.getId());
      assertEquals("Galleta", producto.getNombre());
      assertEquals("Cookie", producto.getNombreExtranjero());
   }

   @Test
   @DisplayName("Producto Service get all Productos")
   void productoServiceGetAllProductos() {
      Mockito.when(repository.findAll()).thenReturn(Arrays.asList(
            new Producto(),
            new Producto(),
            new Producto()));
      var list = service.getAll();
      assertFalse(list.isEmpty());
      assertEquals(3, list.size());
   }

}
