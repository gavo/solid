package soe.mdeis.m7.solid.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import soe.mdeis.m7.solid.model.Almacen;
import soe.mdeis.m7.solid.service.AlmacenService;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;

@WebMvcTest(controllers = AlmacenController.class)
@AutoConfigureMockMvc
@DisplayName("Test Almacen Controller")
class AlmacenControllerTest {

      @Autowired
      MockMvc mockMvc;

      @MockBean
      AlmacenService service;

      @Test
      @DisplayName("Almacen Controller Register Almacen")
      void almacenControllerRegisterAlmacen() throws Exception {
            Mockito.when(service.save(Mockito.any(Almacen.class)))
                        .thenReturn(new Almacen(1, "Almacen 1"));

            mockMvc.perform(post("/api/almacen")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Almacen 1\"}"))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.data.id", is(1)))
                        .andExpect(jsonPath("$.data.nombre", is("Almacen 1")));
      }

      @Test
      @DisplayName("Almacen Controller Register Almacen Bad Request")
      void almacenControllerRegisterAlmacenBadRequest() throws Exception {
            mockMvc.perform(post("/api/almacen")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"\"}"))
                        .andExpect(status().isBadRequest());

      }

      @Test
      @DisplayName("Almacen Controller Get All Almacenes")
      void almacenControllerGetAllAlmacenes() throws Exception {
            Mockito.when(service.getAll()).thenReturn(Arrays.asList(
                        new Almacen(1, "Almacen 1"),
                        new Almacen(2, "Almacen 2")));
            mockMvc.perform(get("/api/almacen")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.data", hasSize(2)));
      }

      @Test
      @DisplayName("Almacen Controller Update Almacen")
      void almacenControllerUpdateAlmacen() throws Exception {
            Mockito.when(service.update(Mockito.anyInt(), Mockito.any(Almacen.class)))
                        .thenReturn(new Almacen(1, "Almacen 1"));

            mockMvc.perform(put("/api/almacen/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"1\",\"nombre\":\"Almacen 1\"}"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.data.id", is(1)))
                        .andExpect(jsonPath("$.data.nombre", is("Almacen 1")));
      }

      @Test
      @DisplayName("Almacen Controller Update Almacen Bad Request")
      void almacenControllerUpdateAlmacenBadRequest() throws Exception {
            mockMvc.perform(put("/api/almacen/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"1\",\"nombre\":\"\"}"))
                        .andExpect(status().isBadRequest());

      }

      @Test
      @DisplayName("Almacen Controller Get Almacen By ID")
      void almacenControllerGetAlmacenById() throws Exception {
            Mockito.when(service.get(Mockito.anyInt()))
                        .thenReturn(Optional.of(new Almacen(1, "Almacen 1")));
            mockMvc.perform(get("/api/almacen/1")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.data.id", is(1)))
                        .andExpect(jsonPath("$.data.nombre", is("Almacen 1")));
      }

      @Test
      @DisplayName("Almacen Controller Get Almacen by Id Not Found")
      void almacenControllerGetAlmacenByIdNotFound() throws Exception {
            Mockito.when(service.get(1)).thenReturn(Optional.empty());
            mockMvc.perform(get("/api/almacen/1")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().is(HttpStatus.NOT_FOUND.value()));

      }

      @Test
      @DisplayName("Almacen Controller Delete Almacen")
      void almacenControllerDeleteAlmacen() throws Exception {
            int almacenId = 1;
            Mockito.doNothing().when(service).delete(almacenId);

            mockMvc.perform(delete("/api/almacen/{id}", almacenId)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNoContent());

            Mockito.verify(service, Mockito.times(1)).delete(almacenId);
      }

}
