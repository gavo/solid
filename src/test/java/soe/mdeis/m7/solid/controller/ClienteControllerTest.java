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

import soe.mdeis.m7.solid.model.Cliente;
import soe.mdeis.m7.solid.model.TipoDocumento;
import soe.mdeis.m7.solid.service.ClienteService;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;

@WebMvcTest(ClienteController.class)
@AutoConfigureMockMvc
@DisplayName("Test Cliente Controller")
class ClienteControllerTest {

      @Autowired
      private MockMvc mockMvc;

      @MockBean
      private ClienteService service;

      @Test
      @DisplayName("Cliente Controller Register Cliente")
      void clienteControllerRegisterCliente() throws Exception {

            Mockito.when(service.save(Mockito.any(Cliente.class)))
                        .thenReturn(new Cliente(1, "Juan Perez", "123", "1234123", TipoDocumento.CI, "jp@email.com",
                                    null));

            mockMvc.perform(post("/api/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"code\": \"12341\",\"nombre\": \"Juan Perez\", \"documento\":\"1234123\",\"tipoDocumento\":\"CI\",\"email\":\"jp@email.com\"}"))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.id", is(1)))
                        .andExpect(jsonPath("$.code", is("123")))
                        .andExpect(jsonPath("$.nombre", is("Juan Perez")))
                        .andExpect(jsonPath("$.documento", is("1234123")))
                        .andExpect(jsonPath("$.tipoDocumento", is("CI")))
                        .andExpect(jsonPath("$.email", is("jp@email.com")));
      }

      @Test
      @DisplayName("Cliente Controller Register Cliente without nombre")
      void clienteControllerRegisterClienteWithoutNombre() throws Exception {
            mockMvc.perform(post("/api/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"code\": \"12341\",\"nombre\": \"\", \"documento\":\"1234123\",\"tipoDocumento\":\"CI\",\"email\":\"jp@email.com\"}"))
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.nombre", is("")));
      }

      @Test
      @DisplayName("Cliente Controller Register Cliente without code")
      void clienteControllerRegisterClienteWithoutCode() throws Exception {
            mockMvc.perform(post("/api/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"code\": \"\",\"nombre\": \"Juan Perez\", \"documento\":\"1234123\",\"tipoDocumento\":\"CI\",\"email\":\"jp@email.com\"}"))
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.code", is("")));
      }

      @Test
      @DisplayName("Cliente Controller Register Cliente Without documento")
      void clienteControllerRegisterClienteWithoutDocumento() throws Exception {
            mockMvc.perform(post("/api/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"code\": \"12341\",\"nombre\": \"Juan Perez\", \"documento\":\"\",\"tipoDocumento\":\"CI\",\"email\":\"jp@email.com\"}"))
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.documento", is("")));
      }

      @Test
      @DisplayName("Cliente Controller Register Cliente without Tipo Documento")
      void clienteControllerRegisterClienteWithoutTipoDocumento() throws Exception {
            mockMvc.perform(post("/api/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"code\": \"12341\",\"nombre\": \"Juan Perez\", \"documento\":\"1234123\",\"tipoDocumento\":null,\"email\":\"jp@email.com\"}"))
                        .andExpect(status().isBadRequest());
      }

      @Test
      @DisplayName("Cliente Controller Register Cliente Bad Tipo Documento")
      void clienteControllerRegisterClienteBadTipoDocumento() throws Exception {
            mockMvc.perform(post("/api/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"code\": \"12341\",\"nombre\": \"Juan Perez\", \"documento\":\"1234123\",\"tipoDocumento\":\"\",\"email\":\"jp@email.com\"}"))
                        .andExpect(status().isBadRequest());
      }

      @Test
      @DisplayName("Cliente Controller Register Cliente without Email")
      void clienteControllerRegisterClienteWithoutEmail() throws Exception {
            mockMvc.perform(post("/api/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"code\": \"12341\",\"nombre\": \"Juan Perez\", \"documento\":\"1234123\",\"tipoDocumento\":\"CI\",\"email\":\"\"}"))
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.email", is("")));
      }

      @Test
      @DisplayName("Cliente Controller Get All Clientes")
      void clienteControllerGetAllClientes() throws Exception {
            var clientes = Arrays.asList(
                        new Cliente(1, "Juan 1", "001", "112235", TipoDocumento.CI, "juan@email.com", null),
                        new Cliente(2, "Juan 2", "002", "223321", TipoDocumento.NIT, "j2@email.com", null),
                        new Cliente(3, "Juan3", "003", "11234", TipoDocumento.NIT, "j3@email.com", null));

            Mockito.when(service.getAll()).thenReturn(clientes);

            mockMvc.perform(get("/api/cliente")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", hasSize(3)));
      }

      @Test
      @DisplayName("Cliente Controller Get Cliente By ID")
      void clienteControllerGetClienteById() throws Exception {
            Mockito.when(service.get(1)).thenReturn(Optional.of(
                        new Cliente(1, "Juan Perez", "1234", "12345", TipoDocumento.CI, "", null)));
            mockMvc.perform(get("/api/cliente/1")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id", is(1)));
      }

      @Test
      @DisplayName("Cliente Controller Get Cliente By Id NotFound")
      void clienteControllerGetClienteByIdNotFound() throws Exception {
            Mockito.when(service.get(1)).thenReturn(Optional.of(
                        new Cliente(1, "Juan Perez", "1234", "12345", TipoDocumento.CI, "", null)));
            mockMvc.perform(get("/api/cliente/2")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
      }
}
