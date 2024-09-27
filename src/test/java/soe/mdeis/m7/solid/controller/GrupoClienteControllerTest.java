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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(GrupoClienteController.class)
@AutoConfigureMockMvc
@DisplayName("Test Cliente Controller")
public class GrupoClienteControllerTest {
      @Autowired
      private MockMvc mockMvc;

      @MockBean
      private GrupoClienteService grupoClienteService;

      @Test
      @DisplayName("Save new GrupoCliente")
      void shouldSaveNewGrupoCliente() throws Exception {
            GrupoCliente newGrupoCliente = new GrupoCliente(1, "GrupoC 1", BigDecimal.valueOf(15));

            Mockito.when(grupoClienteService.save(Mockito.any(GrupoCliente.class))).thenReturn(newGrupoCliente);

            mockMvc.perform(post("/api/grupo-cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"1\",\"nombre\":\"GrupoC 1\", \"descuento\":\"10\", \"estado\":\"true\"}"))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.id", is(1)))
                        .andExpect(jsonPath("$.descuento", is(15)))
                        .andExpect(jsonPath("$.nombre", is("GrupoC 1")));
      }

      @Test
      @DisplayName("Get Grupos Clientes")
      void showListAllAlmacenes() throws Exception {
            List<GrupoCliente> grupoClientes = new ArrayList<GrupoCliente>(Arrays.asList(
                        new GrupoCliente(1, "GrupoC 1", BigDecimal.valueOf(10)),
                        new GrupoCliente(2, "GrupoC 2", BigDecimal.valueOf(15))));

            Mockito.when(grupoClienteService.getAll()).thenReturn(grupoClientes);

            mockMvc.perform(get("/api/grupo-cliente")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", hasSize(2)));
      }
}
