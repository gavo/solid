package soe.mdeis.m7.solid.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import soe.mdeis.m7.solid.model.Fabricante;
import soe.mdeis.m7.solid.service.FabricanteService;

@WebMvcTest(controllers = FabricanteController.class)
@AutoConfigureMockMvc
@DisplayName("Test Fabricante Controller")
class FabricanteControllerTest {

      @Autowired
      MockMvc mockMvc;

      @MockBean
      FabricanteService service;

      @Test
      @DisplayName("Fabricante Controller Register Fabricante")
      void fabricanteControllerRegisterFabricante() throws Exception {
            Mockito.when(service.save(Mockito.any(Fabricante.class)))
                        .thenReturn(new Fabricante(1l, "Fabricante 1"));

            mockMvc.perform(post("/api/fabricante").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Fabricante 1\"}"))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.id", is(1)))
                        .andExpect(jsonPath("$.nombre", is("Fabricante 1")));
      }

      @Test
      @DisplayName("Fabricante Controller Register Fabricante without nombre")
      void fabricanteControllerRegisterFabricanteWithoutNombre() throws Exception {
            mockMvc.perform(post("/api/fabricante").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"\"}"))
                        .andExpect(status().isBadRequest());
      }

      @Test
      @DisplayName("Fabricante Controller update Fabricante")
      void fabricanteControllerUpdateFabricante() throws Exception {
            Mockito.when(service.update(Mockito.anyLong(), Mockito.any(Fabricante.class)))
                        .thenReturn(new Fabricante(1l, "Fabricante 1"));
            mockMvc.perform(put("/api/fabricante/1").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Fabricante 1\"}"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id", is(1)))
                        .andExpect(jsonPath("$.nombre", is("Fabricante 1")));
      }

      @Test
      @DisplayName("Fabricante Controller Update Fabricante without Nombre")
      void fabricanteControllerUpdateFabricanteWithoutNombre() throws Exception {
            mockMvc.perform(put("/api/fabricante/1").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"\"}"))
                        .andExpect(status().isBadRequest());
      }

      @Test
      @DisplayName("Fabricante Controller get All Fabricantes")
      void fabricanteControllerGetAllFabricantes() throws Exception {
            Mockito.when(service.getAll()).thenReturn(Arrays.asList(
                        new Fabricante(1l, "Fabricante 1"), new Fabricante(2l, "Fabricante 2"),
                        new Fabricante(3l, "Fabricante 3")));
            mockMvc.perform(get("/api/fabricante").contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", hasSize(3)));
      }
}
