package soe.mdeis.m7.solid.service;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soe.mdeis.m7.solid.model.Fabricante;
import soe.mdeis.m7.solid.model.GrupoProducto;
import soe.mdeis.m7.solid.model.Proveedor;
import soe.mdeis.m7.solid.repository.FabricanteRepository;
import soe.mdeis.m7.solid.repository.GrupoProductoRepository;
import soe.mdeis.m7.solid.repository.ProductoRepository;
import soe.mdeis.m7.solid.repository.ProveedorRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

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
