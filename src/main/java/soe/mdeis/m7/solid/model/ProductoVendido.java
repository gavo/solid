package soe.mdeis.m7.solid.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "productos_vendidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductoVendido {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   Integer id;

   Integer cantidad;

   BigDecimal precio;

   BigDecimal descuento = BigDecimal.ZERO;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "id_venta")
   Venta venta;

   @ManyToOne(fetch = FetchType.LAZY, optional = false)
   @JoinColumn(name = "id_producto", nullable = false)
   Producto producto;
}
