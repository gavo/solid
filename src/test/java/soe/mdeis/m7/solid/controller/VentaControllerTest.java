package soe.mdeis.m7.solid.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import soe.mdeis.m7.solid.model.Cliente;
import soe.mdeis.m7.solid.model.Producto;
import soe.mdeis.m7.solid.model.ProductoVendido;
import soe.mdeis.m7.solid.model.TipoDocumento;
import soe.mdeis.m7.solid.model.Venta;
import soe.mdeis.m7.solid.service.VentaService;

@WebMvcTest(controllers = VentaController.class)
@AutoConfigureMockMvc
@DisplayName("Test Venta Controller")
class VentaControllerTest {
   @Autowired
   MockMvc mockMvc;

   @MockBean
   VentaService service;

   @Test
   @DisplayName("Venta Controller register Venta")
   void ventaControllerRegisterVenta() throws Exception {
      Producto producto = new Producto(1, "Producto 1", "Product 1", 1, "1234", "lbs", BigDecimal.ONE, null, null, null,
            null);
      var productos = Arrays.asList(new ProductoVendido(1, 5, BigDecimal.valueOf(2), BigDecimal.ZERO, null, producto));
      Cliente cliente = new Cliente(1, "Juan Perez", "10", "52123", TipoDocumento.CI, "juan@yopmail.com", null);
      Venta venta = new Venta(1, LocalDateTime.now(), BigDecimal.ZERO, BigDecimal.valueOf(10), BigDecimal.valueOf(10),
            cliente, null, null,
            productos);

      Mockito.when(service.save(Mockito.any(Venta.class))).thenReturn(venta);

      mockMvc.perform(post("/api/venta")
            .contentType(MediaType.APPLICATION_JSON)
            .content(
                  "{\"descuento\":\"0\",\"subTotal\":\"10\",\"total\":\"10\",\"cliente\":{\"id\":\"1\",\"nombre\":\"Juan Perez\"},\"productos\":[{\"id\":\"1\",\"cantidad\":\"5\",\"precio\":\"2\",\"descuento\":\"0\",\"producto\":{\"id\":\"1\"}}]}"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id", is(1)));
   }

   @Test
   @DisplayName("Venta Controller Get All Ventas")
   void ventaControllerGetAllVentas() throws Exception {
      Mockito.when(service.getAllVentas()).thenReturn(Arrays.asList(
            new Venta(), new Venta(), new Venta()));
      mockMvc.perform(get("/api/venta").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)));
   }

}
