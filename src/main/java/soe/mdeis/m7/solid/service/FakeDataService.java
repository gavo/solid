package soe.mdeis.m7.solid.service;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soe.mdeis.m7.solid.model.Fabricante;
import soe.mdeis.m7.solid.repository.FabricanteRepository;
import soe.mdeis.m7.solid.repository.GrupoProductoRepository;
import soe.mdeis.m7.solid.repository.ProductoRepository;
import soe.mdeis.m7.solid.repository.ProveedorRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeDataService {

    private static final Faker faker = new Faker();

    @Autowired
    ProveedorRepository proveedorRepository;

    @Autowired
    FabricanteRepository fabricanteRepository;

    @Autowired
    GrupoProductoRepository grupoProductoRepository;

    @Autowired
    ProductoRepository productoRepository;

    public List<Fabricante> newFakesFabricantes(int quantity) {
        List<Fabricante> list = new ArrayList<>();
        int n = 0;
        while (n < quantity) {
            Fabricante fabricante = Fabricante.builder()
                    .nombre(faker.company().name())
                    .build();
            if(fabricanteRepository.findByNombre(fabricante.getNombre()).isEmpty()) {
                list.add(fabricanteRepository.save(fabricante));
                n++;
            }
        }

        return list;
    }
}
