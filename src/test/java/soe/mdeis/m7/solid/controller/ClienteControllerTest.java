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

import soe.mdeis.m7.solid.model.Cliente;
import soe.mdeis.m7.solid.model.TipoDocumento;
import soe.mdeis.m7.solid.service.ClienteService;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            Cliente newCliente = new Cliente(1, "Juan Perez", "123", "1234123", TipoDocumento.CI, "jp@email.com", null);

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

      @Test
      @DisplayName("Get Clientes")
      void showListAllCliente() throws Exception {
            List<Cliente> clientes = new ArrayList<Cliente>(
                        Arrays.asList(
                                    new Cliente(1, "Juan 1", "001", "112235", TipoDocumento.CI, "juan@email.com", null),
                                    new Cliente(2, "Juan 2", "002", "223321", TipoDocumento.NIT, "j2@email.com", null),
                                    new Cliente(3, "Juan3", "003", "11234", TipoDocumento.NIT, "j3@email.com", null)));

            Mockito.when(clienteService.getAll()).thenReturn(clientes);

            mockMvc.perform(get("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", hasSize(3)));
      }
}
