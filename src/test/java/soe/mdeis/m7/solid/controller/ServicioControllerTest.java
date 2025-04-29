package soe.mdeis.m7.solid.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import soe.mdeis.m7.solid.model.Servicio;
import soe.mdeis.m7.solid.service.ServicioService;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Arrays;

@WebMvcTest(ServicioController.class)
@AutoConfigureMockMvc
@DisplayName("Test Servicio Controller")
class ServicioControllerTest {

      @Autowired
      MockMvc mockMvc;

      @MockBean
      ServicioService service;

      @Test
      @DisplayName("Servicio Controller Register Servicio")
      void servicioControllerRegisterServicio() throws Exception {
            Mockito.when(service.save(Mockito.any(Servicio.class)))
                        .thenReturn(new Servicio(1l, "S-01", "Servicio 1", BigDecimal.ONE));

            mockMvc.perform(post("/api/servicio")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"codigo\":\"S-01\",\"nombre\":\"Servicio 1\",\"precio\":\"1\"}"))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.id", is(1)))
                        .andExpect(jsonPath("$.nombre", is("Servicio 1")))
                        .andExpect(jsonPath("$.precio", is(BigDecimal.ONE.intValue())));
      }

      @Test
      @DisplayName("Servicio Controller Register Servicio Without Nombre")
      void servicioControllerRegisterServicioWithoutNombre() throws Exception {
            mockMvc.perform(post("/api/servicio")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"codigo\":\"S-01\",\"nombre\":\"\",\"precio\":\"1\"}"))
                        .andExpect(status().isBadRequest());
      }

      @Test
      @DisplayName("Servicio Controller Register Servicio Without Codigo")
      void servicioControllerRegisterServicioWithoutCodigo() throws Exception {
            mockMvc.perform(post("/api/servicio")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"codigo\":\"\",\"nombre\":\"Servicio 1\",\"precio\":\"1\"}"))
                        .andExpect(status().isBadRequest());
      }

      @Test
      @DisplayName("Servicio Controller Get All Servicios")
      void showListAllAlmacenes() throws Exception {
            Mockito.when(service.getAll())
                        .thenReturn(Arrays.asList(
                                    new Servicio(1l, "ser-01", "Servicio 1", BigDecimal.ONE),
                                    new Servicio(1l, "ser-02", "Servicio 2", BigDecimal.ONE),
                                    new Servicio(1l, "ser-03", "Servicio 3", BigDecimal.ONE)));
            mockMvc.perform(get("/api/servicio")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", hasSize(3)));
      }
}
