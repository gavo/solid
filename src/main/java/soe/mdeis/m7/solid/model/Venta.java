package soe.mdeis.m7.solid.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "ventas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Venta {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   Integer id;

   LocalDateTime fecha;

   BigDecimal descuento = BigDecimal.ZERO;

   @Column(name = "sub_total")
   BigDecimal subTotal;

   BigDecimal total;

   @ManyToOne(fetch = FetchType.LAZY, optional = true)
   @JoinColumn(name = "id_cliente", nullable = true)
   Cliente cliente;

   @OneToOne(mappedBy = "venta", cascade = CascadeType.ALL)
   Factura factura;

   @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   List<ServicioRealizado> servicios = new ArrayList<>();

   @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   List<ProductoVendido> productos = new ArrayList<>();

}
