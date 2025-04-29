package soe.mdeis.m7.solid.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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

import soe.mdeis.m7.solid.model.Servicio;
import soe.mdeis.m7.solid.repository.ServicioRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("test Servicio Service")
class ServicioServiceTest {

   @Mock
   ServicioRepository repository;

   @InjectMocks
   ServicioService service;

   @Test
   @DisplayName("Servicio Service save Servicio")
   void servicioServiceSaveServicio() {
      Mockito.when(service.save(Mockito.any(Servicio.class)))
            .thenReturn(new Servicio(1l, "s-001", "Servicio 1", BigDecimal.TEN));
      Servicio servicio = new Servicio(0l, "s-001", "Servicio 1", BigDecimal.TEN);
      Servicio servicioSaved = service.save(servicio);
      assertNotEquals(servicio.getId(), servicioSaved.getId());
      assertEquals(servicio.getNombre(), servicioSaved.getNombre());
   }

   @Test
   @DisplayName("Servicio Service get All Servicios")
   void servicioServiceGetAllServicios() {
      Mockito.when(repository.findAll()).thenReturn(
            Arrays.asList(new Servicio(), new Servicio(), new Servicio()));
      var list = service.getAll();
      assertTrue(!list.isEmpty());
      assertEquals(3, list.size());
   }
}
