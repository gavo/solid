package soe.mdeis.m7.solid.service;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soe.mdeis.m7.solid.model.*;
import soe.mdeis.m7.solid.repository.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class FakeDataService {

    private static final Faker faker = new Faker(new Locale("es-MX"));
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    ProveedorRepository proveedorRepository;

    @Autowired
    FabricanteRepository fabricanteRepository;

    @Autowired
    GrupoProductoRepository grupoProductoRepository;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    ServicioRepository servicioRepository;

    @Autowired
    GrupoClienteRepository grupoClienteRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    VentaRepository ventaRepository;

    @Autowired
    VentaService ventaService;

    public List<Cliente> newFakeClientes(int quanity) {
        List<Cliente> list = new ArrayList<>();
        final HashSet<String> names = new HashSet<>();
        final HashMap<Long, GrupoCliente> grupoClientes = new HashMap<>();
        grupoClienteRepository.findAll().forEach(gc -> grupoClientes.put(gc.getId(), gc));
        int n = 0;
        while (n < quanity) {
            Cliente cliente = Cliente.builder()
                    .code(faker.regexify("[A-Z]{5}-[0-9]{7}"))
                    .nombre(faker.name().fullName())
                    .documento(faker.idNumber().valid())
                    .tipoDocumento(faker.options().option(TipoDocumento.CI, TipoDocumento.NIT))
                    .email(faker.internet().emailAddress())
                    .grupoCliente(grupoClientes.get(faker.number().numberBetween(0l, grupoClientes.size() * 3l)))
                    .build();
            if (!names.contains(cliente.getNombre())) {
                names.add(cliente.getNombre());
                list.add(clienteRepository.save(cliente));
                n++;
            }
        }

        return list;
    }

    public List<GrupoCliente> newFakeGrupoClientes() {
        List<GrupoCliente> inDB = grupoClienteRepository.findAll();
        if (!inDB.isEmpty()) {
            return inDB;
        }
        List<GrupoCliente> list = new ArrayList<>();
        String[] rangoGrupo = {
                "",
                " Minimo",
                " Menor",
                " Regular",
                " Estandar",
                " Mayor",
                " Super",
                " Maximo",
                " Super Maximo",
                " Prioritario",
                " Exclusivo",
                " Semi Vip",
                " Vip",
                " Super Vip",
                " Especial"
        };
        String[] nombresGrupo = {
                "Minorista",
                "Cliente",
                "Institucional",
                "Usuario",
                "Local",
                "Corporativo",
                "Frecuente",
                "Emprendedor",
                "Mayorista",
                "Distribuidor",
                "Socio",
                "Club",
                "Estrategico",
                "Suscriptor",
                "Alianza Estratégica"
        };
        double descuento = 0.0001d;
        for (String pref : nombresGrupo) {
            for (String suf : rangoGrupo) {
                GrupoCliente grupoCliente = GrupoCliente.builder()
                        .nombre(pref + suf)
                        .descuento(
                                BigDecimal.valueOf(descuento)
                                        .divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP))
                        .build();
                list.add(grupoClienteRepository.save(grupoCliente));
                descuento = descuento + 0.222;
            }
        }

        return list;
    }

    public List<Servicio> newFakeServicios(int quantity) {
        final List<Servicio> list = new ArrayList<>();
        final HashSet<String> names = new HashSet<>();
        int n = 0;
        while (n < quantity) {
            Servicio servicio = Servicio.builder()
                    .codigo("SVC-" + faker.number().digits(10))
                    .nombre(faker.company().buzzword() + " " + faker.company().industry())
                    .precio(BigDecimal.valueOf(faker.number().randomDouble(2, 20, 500)))
                    .build();
            if (!names.contains(servicio.getNombre())) {
                list.add(servicioRepository.save(servicio));
                names.add(servicio.getNombre());
                n++;
            }
        }
        return list;
    }

    public List<Producto> newFakeProductos(int quatity) {
        final List<Producto> list = new ArrayList<>();
        final HashMap<Long, Proveedor> proveedores = new HashMap<>();
        proveedorRepository.findAll().forEach(p -> proveedores.put(p.getId(), p));

        final HashMap<Long, Fabricante> fabricantes = new HashMap<>();
        fabricanteRepository.findAll().forEach(f -> fabricantes.put(f.getId(), f));

        final HashMap<Long, GrupoProducto> grupoProductos = new HashMap<>();
        grupoProductoRepository.findAll().forEach(gp -> grupoProductos.put(gp.getId(), gp));

        final HashMap<Long, Producto> productos = new HashMap<>();
        productoRepository.findAll().forEach(p -> productos.put(p.getId(), p));

        final HashSet<String> names = new HashSet<>();
        int n = 0;
        while (n < quatity) {
            final String name = faker.commerce().productName();
            if (!names.contains(name)) {
                Producto producto = Producto.builder()
                        .nombre(name)
                        .nombreExtranjero(name + " " + faker.country().countryCode2())
                        .peso(faker.number().numberBetween(1, 1000))
                        .codBarra(faker.number().digits(faker.number().numberBetween(6, 15)))
                        .um(faker.options().option("kg", "g", "lb", "oz"))
                        .precio(BigDecimal.valueOf(faker.number().randomDouble(2, 1, 1000)))
                        .fabricante(fabricantes.get(0L + faker.number().numberBetween(0, fabricantes.size() + 1)))
                        .proveedor(proveedores.get(0L + faker.number().numberBetween(0, proveedores.size() + 1)))
                        .grupoProducto(grupoProductos.get(0L + faker.number().numberBetween(0, grupoProductos.size() + 1)))
                        .alternante(productos.get(0L + faker.number().numberBetween(0, productos.size() * 10)))
                        .build();
                names.add(name);
                Producto saved = productoRepository.save(producto);
                productos.put(producto.getId(), producto);
                list.add(producto);
                n++;
            }

        }
        return list;
    }

    public List<Proveedor> newFakesProveedor(int quantity) {
        List<Proveedor> list = new ArrayList<>();
        HashSet<String> names = new HashSet<>();
        int n = 0;
        while (n < quantity) {
            Proveedor proveedor = Proveedor.builder()
                    .nombre(faker.company().name())
                    .build();
            if (!names.contains(proveedor.getNombre()) &&
                    proveedorRepository.findByNombre(proveedor.getNombre()).isEmpty() &&
                    fabricanteRepository.findByNombre(proveedor.getNombre().toUpperCase()).isEmpty()
            ) {
                names.add(proveedor.getNombre());
                list.add(proveedorRepository.save(proveedor));
                n++;
            }
        }
        return list;
    }

    public List<GrupoProducto> newFakesGrupoProducto(int quantity) {
        List<GrupoProducto> list = new ArrayList<>();
        HashSet<String> names = new HashSet<>();
        int n = 0;
        while (n < quantity) {
            GrupoProducto grupoProducto = GrupoProducto.builder()
                    .nombre(faker.commerce().department())
                    .build();
            if (!names.contains(grupoProducto.getNombre()) &&
                    grupoProductoRepository.findByNombre(grupoProducto.getNombre()).isEmpty()) {
                names.add(grupoProducto.getNombre());
                list.add(grupoProductoRepository.save(grupoProducto));
                n++;
            }
        }
        return list;
    }

    public List<Fabricante> newFakesFabricantes(int quantity) {
        List<Fabricante> list = new ArrayList<>();
        HashSet<String> names = new HashSet<>();
        int n = 0;
        while (n < quantity) {
            Fabricante fabricante = Fabricante.builder()
                    .nombre(faker.company().name().toUpperCase())
                    .build();
            if (!names.contains(fabricante.getNombre().toUpperCase()) &&
                    fabricanteRepository.findByNombre(fabricante.getNombre()).isEmpty() &&
                    proveedorRepository.findByNombre(fabricante.getNombre()).isEmpty()
            ) {
                names.add(fabricante.getNombre());
                list.add(fabricanteRepository.save(fabricante));
                n++;
            }
        }
        return list;
    }

    public List<Venta> newFakeVentas(int cantidad) {
        final HashMap<Long, Producto> products = new HashMap<>();
        final HashMap<Long, Servicio> servicios = new HashMap<>();
        final HashMap<Long, Cliente> clientes = new HashMap<>();
        productoRepository.findAll().forEach(producto -> products.put(producto.getId(), producto));
        servicioRepository.findAll().forEach(servicio->servicios.put(servicio.getId(), servicio));
        clienteRepository.findAll().forEach(cliente -> clientes.put(cliente.getId(), cliente));
        List<Venta> list = new ArrayList<>();
        List<Venta> auxList = ventaRepository.findAll();
        Venta aux = auxList.isEmpty() ? null : auxList.getLast();
        LocalDateTime fecha = aux != null ? aux.getFecha() : LocalDateTime.parse("2010-01-01 00:00:01", formatter);
        int n = 0;
        while (n < cantidad) {
            fecha = fecha.plusMinutes(faker.number().numberBetween(1, 60));
            Venta venta = new Venta();
            List<ProductoVendido> productList = new ArrayList<>();
            List<ServicioRealizado> serviceList = new ArrayList<>();
            int cantP = faker.number().numberBetween(0, 10);
            for (int i = 0; i < cantP; i++) {
                Producto producto = products.get(faker.number().numberBetween(1l, products.size()));
                ProductoVendido productoVendido = ProductoVendido.builder()
                        .producto(producto)
                        .venta(venta)
                        .precio(producto.getPrecio())
                        .cantidad(faker.number().numberBetween(1, 10))
                        .descuento(BigDecimal.valueOf(faker.number().numberBetween(0, producto.getPrecio().intValue() / 2)))
                        .build();
                productList.add(productoVendido);
            }
            int cantS = faker.number().numberBetween(0, 5);
            for (int i = 0; i < cantS; i++) {
                Servicio servicio = servicios.get(faker.number().numberBetween(1l, servicios.size()));
                ServicioRealizado servicioRealizado = ServicioRealizado.builder()
                        .servicio(servicio)
                        .venta(venta)
                        .precio(servicio.getPrecio())
                        .descuento(BigDecimal.valueOf(faker.number().numberBetween(0, servicio.getPrecio().intValue() / 3)))
                        .build();
                serviceList.add(servicioRealizado);
            }
            venta.setFecha(fecha);
            venta.setProductos(productList);
            venta.setServicios(serviceList);
            venta.setCliente(clientes.get(faker.number().numberBetween(1l, clientes.size())));
            if (faker.number().numberBetween(0, 10) % 2 == 0) {
                Factura factura = Factura.builder()
                        .nit(venta.getCliente().getDocumento())
                        .razonSocial(venta.getCliente().getNombre())
                        .build();
                venta.setFactura(factura);
            }
            if (!venta.getProductos().isEmpty() || !venta.getServicios().isEmpty()) {
                try {
                    list.add(ventaService.save(venta, venta.getFecha()));
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
                n++;
            }
        }
        return list;
    }
}
