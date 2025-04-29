package soe.mdeis.m7.solid.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import soe.mdeis.m7.solid.model.Almacen;
import soe.mdeis.m7.solid.repository.AlmacenRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test Almacen Service")
class AlmacenServiceTest {

   @Mock
   AlmacenRepository repository;

   @InjectMocks
   AlmacenService service;

   @Test
   @DisplayName("Almacen Service update service")
   void almacenServiceUpdateService() {
      Mockito.when(service.save(Mockito.any(Almacen.class))).thenReturn(new Almacen(1l, "Almacen"));
      Almacen almacen = new Almacen(0l, "Almacen");
      Almacen savedAlmacen = service.update(1l, almacen);
      assertNotEquals(almacen.getId(), savedAlmacen.getId());
      assertEquals(almacen.getNombre(), savedAlmacen.getNombre());
   }

   @Test
   @DisplayName("Almacen Service save Service")
   void almacenServiceSaveService() {
      Mockito.when(service.save(Mockito.any(Almacen.class))).thenReturn(new Almacen(1l, "Almacen"));
      Almacen almacen = new Almacen(0l, "Almacen");
      Almacen savedAlmacen = service.save(almacen);
      assertNotEquals(almacen.getId(), savedAlmacen.getId());
      assertEquals(almacen.getNombre(), savedAlmacen.getNombre());
   }

   @Test
   @DisplayName("Almacen Service get All Almacenes")
   void almacenServiceGetAllAlmacenes() {
      Mockito.when(repository.findAll()).thenReturn(Arrays.asList(
            new Almacen(1l, "Almacen 1"),
            new Almacen(2l, "Almacen 2")));
      var list = service.getAll();
      assertTrue(!list.isEmpty());
      assertEquals(2, list.size());
   }

   @Test
   @DisplayName("Almacen Service Get Almacen")
   void almacenServiceGetAlmacen() {
      Mockito.when(repository.findById(1l)).thenReturn(Optional.of(new Almacen(1l, "Almacen 1")));
      Mockito.when(repository.findById(2l)).thenReturn(Optional.of(new Almacen(2l, "Almacen 2")));
      Almacen a1 = service.get(1).get();
      Almacen a2 = service.get(2).get();
      assertNotEquals(a1, a2);
      assertEquals(1, a1.getId());
      assertEquals(2, a2.getId());
   }

   @Test
   @DisplayName("Almacen ServiceDeleteAlmacen")
   void almacenServiceDeleteAlmacen() {
      long almacenId = 1l;
      Mockito.doNothing().when(repository).deleteById(almacenId);
      service.delete(almacenId);
      Mockito.verify(repository, Mockito.times(1)).deleteById(almacenId);
   }
}