package soe.mdeis.m7.solid.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity(name = "facturas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nro;

    private LocalDateTime fecha;

    @Column(nullable = false)
    private String nit;

    @Column(name = "razon_social", nullable = false)
    private String razonSocial;

    private BigDecimal total;

    @Column(name = "credito-fiscal")
    private BigDecimal creditoFiscal;

    @OneToOne
    @JoinColumn(name = "id_venta")
    private Venta venta;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Factura)) return false;
        Factura factura = (Factura) o;
        return id != null && id.equals(factura.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
