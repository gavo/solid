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

import soe.mdeis.m7.solid.model.Almacen;
import soe.mdeis.m7.solid.service.AlmacenService;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(AlmacenController.class)
@AutoConfigureMockMvc
@DisplayName("Test Almacen Controller")
public class AlmacenControllerTest {
      @Autowired
      private MockMvc mockMvc;

      @MockBean
      private AlmacenService almacenService;

      @Test
      @DisplayName("Save new Almacen")
      void shouldSaveANewAlmacen() throws Exception {
            Almacen newAlmacen = new Almacen(1, "Almacen 1");

            Mockito.when(almacenService.save(Mockito.any(Almacen.class))).thenReturn(newAlmacen);

            mockMvc.perform(post("/api/almacen")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombreAlmacen\":\"Almacen 1\",\"denominativo\":\"Alm-01\",\"estado\":\"true\"}"))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.id", is(1)))
                        .andExpect(jsonPath("$.nombre", is("Almacen 1")));
      }

      @Test
      @DisplayName("Get Almacenes")
      void showListAllAlmacenes() throws Exception {
            List<Almacen> Almacenes = new ArrayList<Almacen>(Arrays.asList(
                        new Almacen(1, "Almacen 1"),
                        new Almacen(2, "Almacen 2")));

            Mockito.when(almacenService.getAll()).thenReturn(Almacenes);

            mockMvc.perform(get("/api/almacen")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", hasSize(2)));
      }
}
