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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(ServicioController.class)
@AutoConfigureMockMvc
@DisplayName("Test Servicio Controller")
public class ServicioControllerTest {

      @Autowired
      MockMvc mockMvc;

      @MockBean
      ServicioService service;

      @Test
      @DisplayName("Save new Servicio")
      void shouldSaveANewServicio() throws Exception {
            Servicio newServicio = new Servicio(1, "S-01", "Servicio 1", BigDecimal.ONE);

            Mockito.when(service.save(Mockito.any(Servicio.class))).thenReturn(newServicio);

            mockMvc.perform(post("/api/servicio")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"codigo\":\"S-01\",\"nombre\":\"Servicio 1\",\"precio\":\"1\"}"))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.id", is(1)))
                        .andExpect(jsonPath("$.nombre", is("Servicio 1")))
                        .andExpect(jsonPath("$.precio", is(BigDecimal.ONE.intValue())));
      }

      @Test
      @DisplayName("Get Servicios")
      void showListAllAlmacenes() throws Exception {
            List<Servicio> list = new ArrayList<Servicio>(Arrays.asList(
                        new Servicio(1, "ser-01", "Servicio 1", BigDecimal.ONE),
                        new Servicio(1, "ser-02", "Servicio 2", BigDecimal.ONE),
                        new Servicio(1, "ser-03", "Servicio 3", BigDecimal.ONE)));

            Mockito.when(service.getAll()).thenReturn(list);

            mockMvc.perform(get("/api/servicio")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", hasSize(3)));
      }
}
