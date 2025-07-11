package soe.mdeis.m7.solid.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity(name = "productos_vendidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductoVendido {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private Integer cantidad;

   private BigDecimal precio;

   private BigDecimal descuento = BigDecimal.ZERO;

   @ManyToOne()
   @JoinColumn(name = "id_venta")
   @JsonIgnore
   private Venta venta;

   @ManyToOne(optional = false)
   @JoinColumn(name = "id_producto", nullable = false)
   private Producto producto;
}
