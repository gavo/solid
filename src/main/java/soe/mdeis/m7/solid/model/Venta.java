package soe.mdeis.m7.solid.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity(name = "ventas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha;

    private BigDecimal descuento = BigDecimal.ZERO;

    @Column(name = "sub_total")
    private BigDecimal subTotal;

    private BigDecimal total;

    @ManyToOne(optional = true)
    @JoinColumn(name = "id_cliente", nullable = true)
    private Cliente cliente;

    @OneToOne(mappedBy = "venta", cascade = CascadeType.ALL)
    private Factura factura;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<ServicioRealizado> servicios = new ArrayList<>();

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<ProductoVendido> productos = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Venta)) return false;
        Venta venta = (Venta) o;
        return id != null && id.equals(venta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
