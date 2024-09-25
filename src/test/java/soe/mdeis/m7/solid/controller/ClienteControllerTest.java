package soe.mdeis.m7.solid.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import soe.mdeis.m7.solid.model.Cliente;
import soe.mdeis.m7.solid.service.ClienteService;

@WebMvcTest(ClienteController.class)
@AutoConfigureMockMvc
@DisplayName("Test Cliente Controller")
public class ClienteControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @MockBean
   private ClienteService clienteService;

   @Test
   @DisplayName("Save new Cliente")
   void shouldSaveANewCliente() throws Exception {
      Cliente newCliente = new Cliente(1, "Juan Perez", "123");

      Mockito.when(clienteService.save(Mockito.any(Cliente.class)))
            .thenReturn(newCliente);

      mockMvc.perform(post("/api/clientes")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"nombre\":\"Juan Perez\",\"code\":\"123\"}"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.code", is("123")))
            .andExpect(jsonPath("$.nombre", is("Juan Perez")));
   }
}