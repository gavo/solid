package soe.mdeis.m7.solid.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import soe.mdeis.m7.solid.model.Cliente;
import soe.mdeis.m7.solid.model.Factura;
import soe.mdeis.m7.solid.model.GrupoCliente;
import soe.mdeis.m7.solid.model.Producto;
import soe.mdeis.m7.solid.model.ProductoVendido;
import soe.mdeis.m7.solid.model.Servicio;
import soe.mdeis.m7.solid.model.ServicioRealizado;
import soe.mdeis.m7.solid.model.TipoDocumento;
import soe.mdeis.m7.solid.model.Venta;
import soe.mdeis.m7.solid.repository.VentaRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test Venta Service")
class VentaServiceTest {

      @Mock
      private VentaRepository repository;

      @InjectMocks
      private VentaService service;

      @Test
      @DisplayName("Venta Service Save Venta all discounts without Services")
      void ventaServiceSaveVentaAllDiscountsWithoutServices() throws NoSuchAlgorithmException {
            GrupoCliente grupoCliente = new GrupoCliente(1l, "Grupo Premium", BigDecimal.valueOf(20));
            Cliente cliente = new Cliente(1l, "Juan Perez", "001", "1156321", TipoDocumento.CI, "juanPerez@email.com",
                        grupoCliente);
            Producto p1 = new Producto(
                        1l, "Galleta", "Cookie", 1,
                        "111", "lbs", BigDecimal.valueOf(2),
                        null, null, null, null);
            Producto p2 = new Producto(
                        1l, "Galleta Salada", "Cracker", 1,
                        "111", "lbs", BigDecimal.valueOf(2),
                        null, null, null, null);

            var productosVendidos = Arrays.asList(
                        new ProductoVendido(1l, 10, p1.getPrecio(), BigDecimal.valueOf(10), null, p1),
                        new ProductoVendido(2l, 10, p2.getPrecio(), BigDecimal.ZERO, null, p2));
            Venta venta = new Venta();

            venta.setProductos(productosVendidos);
            venta.setCliente(cliente);
            venta.setFactura(new Factura(1l, "null", null, "12345", "Juan Perez", null, null, venta));
            when(repository.save(any(Venta.class))).thenReturn(venta);

            Venta result = service.save(venta);

            verify(repository, times(1)).save(any(Venta.class));
            assertNotNull(venta.getFecha());
            assertEquals(BigDecimal.valueOf(40), result.getSubTotal());
            assertEquals(BigDecimal.valueOf(16.0), result.getDescuento());
            assertEquals(BigDecimal.valueOf(24.0), result.getTotal());
            assertNotNull(result.getFactura().getFecha());
            assertEquals(BigDecimal.valueOf(24.0), result.getFactura().getTotal());
            assertEquals(BigDecimal.valueOf(3.6), result.getFactura().getCreditoFiscal());
      }

      @Test
      @DisplayName("Venta Service Save Venta OF Services with Discount but GrupoCLiente")
      void ventaServiceSaveVentaOfServicesWithDiscountButGrupoCliente() throws NoSuchAlgorithmException {
            Cliente cliente = new Cliente(1l, "Juan Perez", "001", "1156321", TipoDocumento.CI, "juanPerez@email.com",
                        null);

            Venta venta = new Venta();
            Servicio servicio = new Servicio(1l, "s1", "Atención al cliente", BigDecimal.valueOf(10));
            Servicio servicio2 = new Servicio(1l, "s2", "Soporte Técnico", BigDecimal.valueOf(20));
            venta.getServicios().add(
                        new ServicioRealizado(1l, servicio.getPrecio(), BigDecimal.ZERO, "ninguna", null, servicio));
            venta.getServicios().add(
                        new ServicioRealizado(1l, servicio2.getPrecio(), BigDecimal.valueOf(10), "Ninguna", null,
                                    servicio2));

            venta.setCliente(cliente);
            venta.setFactura(new Factura(1l, "null", null, "12345", "Juan Perez", null, null, venta));

            when(repository.save(any(Venta.class))).thenReturn(venta);

            Venta result = service.save(venta);

            verify(repository, times(1)).save(any(Venta.class));
            assertNotNull(venta.getFecha());
            assertEquals(BigDecimal.valueOf(30), result.getSubTotal());
            assertEquals(BigDecimal.valueOf(10), result.getDescuento());
            assertEquals(BigDecimal.valueOf(20), result.getTotal());
            assertNotNull(result.getFactura().getFecha());
            assertEquals(BigDecimal.valueOf(20), result.getFactura().getTotal());
            assertEquals(BigDecimal.valueOf(3.0), result.getFactura().getCreditoFiscal());
      }

      @Test
      @DisplayName("Venta Service save Venta without clientes and factura")
      void vetaServiceVentaWithoutClientesAndFactura() throws NoSuchAlgorithmException {
            Venta venta = new Venta();
            Producto p1 = new Producto(
                        1l, "Chocolate", "chocolate", 1, "1121",
                        "lbs", BigDecimal.valueOf(2), null,
                        null, null, null);
            Producto p2 = new Producto(
                        1l, "Dulces", "Candy", 1,
                        "3312", "lbs", BigDecimal.valueOf(2),
                        null, null, null, null);

            var productosVendidos = Arrays.asList(
                        new ProductoVendido(1l, 10, p1.getPrecio(), BigDecimal.valueOf(10), null, p1),
                        new ProductoVendido(2l, 10, p2.getPrecio(), BigDecimal.ZERO, null, p2));

            Servicio servicio = new Servicio(1l, "s1", "Atención al cliente", BigDecimal.valueOf(10));
            Servicio servicio2 = new Servicio(1l, "s2", "Soporte Técnico", BigDecimal.valueOf(20));
            venta.getServicios().add(
                        new ServicioRealizado(1l, servicio.getPrecio(), BigDecimal.ZERO, "ninguna", null, servicio));
            venta.getServicios().add(
                        new ServicioRealizado(1l, servicio2.getPrecio(), BigDecimal.valueOf(10), "Ninguna", null,
                                    servicio2));

            venta.setProductos(productosVendidos);

            when(repository.save(any(Venta.class))).thenReturn(venta);
            Venta result = service.save(venta);
            verify(repository, times(1)).save(any(Venta.class));

            assertNotNull(venta.getFecha());
            assertEquals(BigDecimal.valueOf(70), result.getSubTotal());
            assertEquals(BigDecimal.valueOf(20), result.getDescuento());
            assertEquals(BigDecimal.valueOf(50), result.getTotal());
            assertNull(result.getFactura());
      }

      @Test
      @DisplayName("Venta Service Get All Ventas")
      void ventaServiceGetAllVentas() {
            Mockito.when(repository.findAll()).thenReturn(Arrays.asList(
                        new Venta(),
                        new Venta(),
                        new Venta()));
            var ventas = service.getAllVentas();
            assertFalse(ventas.isEmpty());
            assertEquals(3, ventas.size());
      }

}
