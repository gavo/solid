package soe.mdeis.m7.solid.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "facturas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Factura {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   Integer id;

   String nro;

   LocalDateTime fecha;

   @Column(nullable = false)
   String nit;

   @Column(name = "razon_social", nullable = false)
   String razonSocial;

   BigDecimal total;

   BigDecimal creditoFiscal;

   @OneToOne
   @JoinColumn(name = "id_venta")
   Venta venta;
}
