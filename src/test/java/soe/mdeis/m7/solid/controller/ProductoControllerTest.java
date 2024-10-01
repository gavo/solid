package soe.mdeis.m7.solid.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
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

import soe.mdeis.m7.solid.model.Producto;
import soe.mdeis.m7.solid.service.ProductoService;

@WebMvcTest(controllers = ProductoController.class)
@AutoConfigureMockMvc
@DisplayName("Test Producto Controller")
class ProductoControllerTest {

      @Autowired
      MockMvc mockMvc;

      @MockBean
      ProductoService service;

      @Test
      @DisplayName("Producto Controller Register Producto")
      void productoControllerRegisterProducto() throws Exception {
            Mockito.when(service.save(Mockito.any(Producto.class))).thenReturn(
                        new Producto(1, "Galleta", "Cookie", 1, "111", "lbs", BigDecimal.ONE, null, null, null, null));

            mockMvc.perform(post("/api/producto").contentType(MediaType.APPLICATION_JSON).content(
                        "{\"nombre\":\"Galleta\", \"nombreExtranjero\":\"Cookie\",\"peso\":\"1\",\"codBarra\":\"111\",\"um\":\"lbs\",\"precio\":\"1\"}"))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.id", is(1)))
                        .andExpect(jsonPath("$.nombre", is("Galleta")))
                        .andExpect(jsonPath("$.nombreExtranjero", is("Cookie")));
      }

      @Test
      @DisplayName("Producto Controller Register Producto without Nombre")
      void productoControllerRegisterProductoWithoutNombre() throws Exception {
            mockMvc.perform(post("/api/producto").contentType(MediaType.APPLICATION_JSON).content(
                        "{\"nombre\":\"\", \"nombreExtranjero\":\"Cookie\",\"peso\":\"1\",\"codBarra\":\"111\",\"um\":\"lbs\",\"precio\":\"1\"}"))
                        .andExpect(status().isBadRequest());
      }

      @Test
      @DisplayName("Producto Controller Register Producto without nombreExtranjero")
      void productoControllerRegisterProductoWithoutNombreExtranjero() throws Exception {
            mockMvc.perform(post("/api/producto").contentType(MediaType.APPLICATION_JSON).content(
                        "{\"nombre\":\"Galleta\", \"nombreExtranjero\":\"\",\"peso\":\"1\",\"codBarra\":\"111\",\"um\":\"lbs\",\"precio\":\"1\"}"))
                        .andExpect(status().isBadRequest());
      }

      @Test
      @DisplayName("Producto Controller Register Producto without peso")
      void productoControllerRegisterProductoWithoutPeso() throws Exception {
            mockMvc.perform(post("/api/producto").contentType(MediaType.APPLICATION_JSON).content(
                        "{\"nombre\":\"Galleta\", \"nombreExtranjero\":\"Cookie\",\"peso\":\"\",\"codBarra\":\"111\",\"um\":\"lbs\",\"precio\":\"1\"}"))
                        .andExpect(status().isBadRequest());
      }

      @Test
      @DisplayName("Producto Controller Register Producto without codBarra")
      void productoControllerRegisterProductoWithoutCodBarra() throws Exception {
            mockMvc.perform(post("/api/producto").contentType(MediaType.APPLICATION_JSON).content(
                        "{\"nombre\":\"Galleta\", \"nombreExtranjero\":\"Cookie\",\"peso\":\"1\",\"codBarra\":\"\",\"um\":\"lbs\",\"precio\":\"1\"}"))
                        .andExpect(status().isBadRequest());
      }

      @Test
      @DisplayName("Producto Controller Register Producto without um")
      void productoControllerRegisterProductoWithoutUm() throws Exception {
            mockMvc.perform(post("/api/producto").contentType(MediaType.APPLICATION_JSON).content(
                        "{\"nombre\":\"Galleta\", \"nombreExtranjero\":\"Cookie\",\"peso\":\"1\",\"codBarra\":\"111\",\"um\":\"\",\"precio\":\"1\"}"))
                        .andExpect(status().isBadRequest());
      }

      @Test
      @DisplayName("Producto Controller Register Producto without precio")
      void productoControllerRegisterProductoWithoutPrecio() throws Exception {
            mockMvc.perform(post("/api/producto").contentType(MediaType.APPLICATION_JSON).content(
                        "{\"nombre\":\"Galleta\", \"nombreExtranjero\":\"Cookie\",\"peso\":\"1\",\"codBarra\":\"111\",\"um\":\"lbs\"}"))
                        .andExpect(status().isBadRequest());
      }

      @Test
      @DisplayName("Producto Controller Register Producto without precio bad request")
      void productoControllerRegisterProductoWithoutPrecioBadRequest() throws Exception {
            mockMvc.perform(post("/api/producto").contentType(MediaType.APPLICATION_JSON).content(
                        "{\"nombre\":\"Galleta\", \"nombreExtranjero\":\"Cookie\",\"peso\":\"1\",\"codBarra\":\"111\",\"um\":\"lbs\", \"precio\":\"\"}"))
                        .andExpect(status().isBadRequest());
      }

      @Test
      @DisplayName("Producto Controller Update Producto")
      void productoControllerUpdateProducto() throws Exception {
            Mockito.when(service.update(Mockito.anyInt(), Mockito.any(Producto.class))).thenReturn(
                        new Producto(1, "Galleta", "Cookie", 1, "111", "lbs", BigDecimal.ONE, null, null, null, null));

            mockMvc.perform(put("/api/producto/1").contentType(MediaType.APPLICATION_JSON).content(
                        "{\"nombre\":\"Galleta\", \"nombreExtranjero\":\"Cookie\",\"peso\":\"1\",\"codBarra\":\"111\",\"um\":\"lbs\",\"precio\":\"1\"}"))
                        .andExpect(status().isOk())
                        .andDo(e -> System.out.println(e.getResponse()))
                        .andExpect(jsonPath("$.id", is(1)))
                        .andExpect(jsonPath("$.nombre", is("Galleta")))
                        .andExpect(jsonPath("$.nombreExtranjero", is("Cookie")));
      }

      @Test
      @DisplayName("Producto Controller Update Producto without Nombre")
      void productoControllerUpdateProductoWithoutNombre() throws Exception {
            mockMvc.perform(put("/api/producto/1").contentType(MediaType.APPLICATION_JSON).content(
                        "{\"nombre\":\"\", \"nombreExtranjero\":\"Cookie\",\"peso\":\"1\",\"codBarra\":\"111\",\"um\":\"lbs\",\"precio\":\"1\"}"))
                        .andExpect(status().isBadRequest());
      }

      @Test
      @DisplayName("Producto Controller get all productos")
      void productoControllerGetAllProductos() throws Exception {
            Mockito.when(service.getAll()).thenReturn(Arrays.asList(
                        new Producto(), new Producto(), new Producto()));
            mockMvc.perform(get("/api/producto").contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", hasSize(3)));
      }

}
