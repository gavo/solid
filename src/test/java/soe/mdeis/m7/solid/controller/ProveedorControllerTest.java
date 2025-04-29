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

import soe.mdeis.m7.solid.model.Proveedor;
import soe.mdeis.m7.solid.service.ProveedorService;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

@WebMvcTest(ProveedorController.class)
@AutoConfigureMockMvc
@DisplayName("Test Proveedor Controller")
class ProveedorControllerTest {

      @Autowired
      private MockMvc mockMvc;

      @MockBean
      private ProveedorService service;

      @Test
      @DisplayName("Proveedor Controller get All Proveedores")
      void proveedorControllerGetAllProveedores() throws Exception {
            Mockito.when(service.getAll()).thenReturn(Arrays.asList(
                        new Proveedor(1l, "Proveedor 1"),
                        new Proveedor(2l, "Proveedor 2"),
                        new Proveedor(3l, "Proveedor 3")));
            mockMvc.perform(get("/api/proveedor").contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", hasSize(3)));
      }

      @Test
      @DisplayName("Proveedor Controller Register Proveedor")
      void proveedorControllerRegisterProveedor() throws Exception {
            Mockito.when(service.save(Mockito.any(Proveedor.class))).thenReturn(new Proveedor(1l, "Proveedor 1"));
            mockMvc.perform(post("/api/proveedor").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Proveedor 1\"}"))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.id", is(1)));
      }

      @Test
      @DisplayName("Proveedor Controller register Proveedor without nombre")
      void proveedorControllerRegisterProveedorWithoutNombre() throws Exception {
            mockMvc.perform(post("/api/proveedor").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"\"}"))
                        .andExpect(status().isBadRequest());
      }

      @Test
      @DisplayName("Proveedor Controller Update Proveedor")
      void proveedorControllerUpdateProveedor() throws Exception {
            Mockito.when(service.update(Mockito.anyLong(), Mockito.any(Proveedor.class)))
                        .thenReturn(new Proveedor(1l, "Proveedor 1"));
            mockMvc.perform(put("/api/proveedor/1").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Proveedor 1\"}"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id", is(1)))
                        .andExpect(jsonPath("$.nombre", is("Proveedor 1")));
      }

      @Test
      @DisplayName("Proveedor Controller Update Proveedor Without nombre")
      void proveedorControllerUpdateProveedorWithoutNombre() throws Exception {
            mockMvc.perform(put("/api/proveedor/1").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"\"}"))
                        .andExpect(status().isBadRequest());
      }
}
