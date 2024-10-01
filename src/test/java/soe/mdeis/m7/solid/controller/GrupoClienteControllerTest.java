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

import soe.mdeis.m7.solid.model.GrupoCliente;
import soe.mdeis.m7.solid.service.GrupoClienteService;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Arrays;

@WebMvcTest(GrupoClienteController.class)
@AutoConfigureMockMvc
@DisplayName("Test Grupo Cliente Controller")
class GrupoClienteControllerTest {
      @Autowired
      private MockMvc mockMvc;

      @MockBean
      private GrupoClienteService service;

      @Test
      @DisplayName("Grupo Cliente Controller Register GrupoCliente")
      void grupoClienteControllerRegisterGrupoCliente() throws Exception {
            Mockito.when(service.save(Mockito.any(GrupoCliente.class)))
                        .thenReturn(new GrupoCliente(1, "GrupoC 1", BigDecimal.valueOf(15)));

            mockMvc.perform(post("/api/grupo-cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"1\",\"nombre\":\"GrupoC 1\", \"descuento\":\"10\", \"estado\":\"true\"}"))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.data.id", is(1)))
                        .andExpect(jsonPath("$.data.descuento", is(15)))
                        .andExpect(jsonPath("$.data.nombre", is("GrupoC 1")));
      }

      @Test
      @DisplayName("Grupo Cliente Controller Register Grupo Cliente without nombre")
      void grupoClienteControllerRegisterGrupoClienteWithoutNombre() throws Exception {
            mockMvc.perform(post("/api/grupo-cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"1\",\"nombre\":\"\", \"descuento\":\"10\", \"estado\":\"true\"}"))
                        .andExpect(status().isBadRequest());
      }

      @Test
      @DisplayName("Grupo Cliente Controller Update Grupo Cliente")
      void grupoClienteControllerUpdateGrupoCliente() throws Exception {
            Mockito.when(service.update(Mockito.anyInt(), Mockito.any(GrupoCliente.class)))
                        .thenReturn(new GrupoCliente(1, "Grupo 1", BigDecimal.TEN));
            mockMvc.perform(put("/api/grupo-cliente/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"1\",\"nombre\":\"Grupo 1\",\"descuento\":\"10\"}"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.data.id", is(1)));
      }

      @Test
      @DisplayName("Grupo Cliente Controller update Grupo Cliente Without Nombre")
      void grupoClienteControllerUpdateGrupoClienteWithoutNombre() throws Exception {
            mockMvc.perform(put("/api/grupo-cliente/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"1\",\"nombre\":\"\",\"descuento\":\"10\"}"))
                        .andExpect(status().isBadRequest());
      }

      @Test
      @DisplayName("Grupo Cliente Controller get All Grupo Clientes")
      void grupoClienteControllerGetAllGrupoClientes() throws Exception {
            Mockito.when(service.getAll()).thenReturn(Arrays.asList(
                        new GrupoCliente(1, "GrupoC 1", BigDecimal.valueOf(10)),
                        new GrupoCliente(2, "GrupoC 2", BigDecimal.valueOf(15))));

            mockMvc.perform(get("/api/grupo-cliente")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.data", hasSize(2)));
      }
}
