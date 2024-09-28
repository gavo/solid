package soe.mdeis.m7.solid.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
public class VentaServiceTest {

      @Mock
      private VentaRepository repository;

      @InjectMocks
      private VentaService service;

      @BeforeEach
      void setUp() {

      }

      @Test
      void shouldCalculateTotalForVenta() {
            Venta venta = new Venta();
            GrupoCliente grupoCliente = new GrupoCliente(1, "Grupo Premium", BigDecimal.valueOf(20));
            Cliente cliente = new Cliente(1, "Juan Perez", "001", "1156321", TipoDocumento.CI, "juanPerez@email.com",
                        grupoCliente);
            Producto p1 = new Producto(
                        1,
                        "Galleta",
                        "Cookie",
                        1,
                        "111",
                        "lbs",
                        BigDecimal.valueOf(2),
                        null,
                        null,
                        null,
                        null);
            Producto p2 = new Producto(
                        1,
                        "Galleta Salada",
                        "Cracker",
                        1,
                        "111",
                        "lbs",
                        BigDecimal.valueOf(2),
                        null,
                        null,
                        null,
                        null);

            List<ProductoVendido> productosVendidos = new ArrayList<ProductoVendido>(
                        Arrays.asList(
                                    new ProductoVendido(1, 10, p1.getPrecio(), BigDecimal.valueOf(10), null, p1),
                                    new ProductoVendido(2, 10, p2.getPrecio(), BigDecimal.ZERO, null, p2)));

            Servicio servicio = new Servicio(1, "s1", "Atención al cliente", BigDecimal.valueOf(10));
            Servicio servicio2 = new Servicio(1, "s2", "Soporte Técnico", BigDecimal.valueOf(20));
            venta.getServicios()
                        .add(new ServicioRealizado(1, servicio.getPrecio(), BigDecimal.ZERO, null, "ninguna"));
            venta.getServicios()
                        .add(new ServicioRealizado(1, servicio2.getPrecio(), BigDecimal.valueOf(10), null, "Ninguna"));

            venta.setProductos(productosVendidos);
            venta.setCliente(cliente);
            venta.setFactura(new Factura(1, "null", null, "12345", "Juan Perez", null, null, venta));

            when(repository.save(any(Venta.class))).thenReturn(venta);

            Venta result = service.save(venta);

            // Verificando cuantas veces se ejecuta el save del repository
            verify(repository, times(1)).save(any(Venta.class));

            // Verificando si se le asigno fecha a la venta
            assertNotNull(venta.getFecha());

            // Verificando si el calculo del subTotal para la venta es correcto
            // Suma de todos los precios de productos por la cantidad mas servicios
            // 10x2 + 10x2 + 10 + 20 = 70
            assertEquals(BigDecimal.valueOf(70), result.getSubTotal());

            // Verificando el descuento porcentual del grupoCliente aplicado en la venta
            // des p1[10] + s1[10] = 20
            // subtotal[70] - desProdServ[20] = 50
            // 50 x 20% = 10 + descProdServ[20] = 30
            assertEquals(BigDecimal.valueOf(30.0), result.getDescuento());

            // Verificando si el calculo del total para la venta es correcto
            // Subtotal[70] - descuento[30] = 40
            assertEquals(BigDecimal.valueOf(40.0), result.getTotal());

            // Verificando si se le asigno fecha a la factura
            assertNotNull(result.getFactura().getFecha());

            // Verificando si se le asigno el monto total correcto a la factura
            assertEquals(BigDecimal.valueOf(40.0), result.getFactura().getTotal());

            // Verificando si se calculo correctamente el credito fiscal
            assertEquals(BigDecimal.valueOf(6.0), result.getFactura().getCreditoFiscal());
      }

}
