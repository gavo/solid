package soe.mdeis.m7.solid.controller;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import soe.mdeis.m7.solid.model.GrupoProducto;
import soe.mdeis.m7.solid.service.GrupoProductoService;

@WebMvcTest(controllers = GrupoProductoController.class)
@AutoConfigureMockMvc
@DisplayName("Test Grupo Producto Controller")
class GrupoProductoControllerTest {

      @Autowired
      MockMvc mockMvc;

      @MockBean
      GrupoProductoService service;

      @Test
      @DisplayName("Grupo Producto Controller Register Grupo Producto")
      void grupoProductoControllerRegisterGrupoProducto() throws Exception {
            Mockito.when(service.save(Mockito.any(GrupoProducto.class)))
                        .thenReturn(new GrupoProducto(1, "Grupo 1"));
            mockMvc.perform(post("/api/grupo-producto").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Grupo 1\"}"))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.data.id", is(1)))
                        .andExpect(jsonPath("$.data.nombre", is("Grupo 1")));
      }

      @Test
      @DisplayName("Grupo Producto Controller Register Grupo Producto without Nombre")
      void grupoProductoControllerRegisterGrupoProductoWithoutNombre() throws Exception {
            mockMvc.perform(post("/api/grupo-producto").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"\"}"))
                        .andExpect(status().isBadRequest());
      }

      @Test
      @DisplayName("Grupo Producto Controller Update Grupo Producto")
      void grupoProductoControllerUpdateGrupoProducto() throws Exception {
            Mockito.when(service.update(Mockito.anyInt(), Mockito.any(GrupoProducto.class)))
                        .thenReturn(new GrupoProducto(1, "Grupo 1"));

            mockMvc.perform(put("/api/grupo-producto/1").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Grupo 1\"}"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.data.id", is(1)))
                        .andExpect(jsonPath("$.data.nombre", is("Grupo 1")));
      }

      @Test
      @DisplayName("Grupo Producto Controller update Grupo Producto Without Nombre")
      void grupoProductoControllerUpdateGrupoProductoWithoutNombre() throws Exception {
            mockMvc.perform(put("/api/grupo-producto/1").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"\"}"))
                        .andExpect(status().isBadRequest());
      }

      @Test
      @DisplayName("Grupo Producto Controller Get All Grupo Producto")
      void grupoProductoControllerGetAllGrupoProducto() throws Exception {
            Mockito.when(service.getAll()).thenReturn(
                        Arrays.asList(new GrupoProducto(), new GrupoProducto(), new GrupoProducto()));

            mockMvc.perform(get("/api/grupo-producto").contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.data", hasSize(3)));
      }

}
