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

import soe.mdeis.m7.solid.model.Fabricante;
import soe.mdeis.m7.solid.repository.FabricanteRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test Fabricante Service")
class FabricanteServiceTest {

   @Mock
   FabricanteRepository repository;

   @InjectMocks
   FabricanteService service;

   @Test
   @DisplayName("Fabricante Service update Fabricante")
   void fabricanteServiceUpdateFabricante() {
      Mockito.when(repository.save(Mockito.any(Fabricante.class))).thenReturn(new Fabricante(1, "Fabricante 1"));
      var fabricante = service.update(1, new Fabricante());
      assertEquals(1, fabricante.getId());
      assertEquals("Fabricante 1", fabricante.getNombre());
   }

   @Test
   @DisplayName("Fabricante Service save Fabricante")
   void fabricanteServiceSaveFabricante() {
      Mockito.when(repository.save(Mockito.any(Fabricante.class))).thenReturn(new Fabricante(1, "Fabricante 1"));
      var fabricante = service.save(new Fabricante());
      assertEquals(1, fabricante.getId());
      assertEquals("Fabricante 1", fabricante.getNombre());
   }

   @Test
   @DisplayName("Fabricante Service Get All Fabricantes")
   void fabricanteServiceGetAllFabricantes() {
      Mockito.when(repository.findAll()).thenReturn(Arrays.asList(
            new Fabricante(1, "Fabricante 1"),
            new Fabricante(2, "Fabricante 2"),
            new Fabricante(3, "Fabricante 3")));

      var list = service.getAll();
      assertFalse(list.isEmpty());
      assertEquals(3, list.size());
   }
}
