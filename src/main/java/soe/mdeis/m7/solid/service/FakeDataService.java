package soe.mdeis.m7.solid.service;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soe.mdeis.m7.solid.model.*;
import soe.mdeis.m7.solid.repository.*;

import java.math.BigDecimal;
import java.util.*;

@Service
public class FakeDataService {

    private static final Faker faker = new Faker(new Locale("es-MX"));

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
}
