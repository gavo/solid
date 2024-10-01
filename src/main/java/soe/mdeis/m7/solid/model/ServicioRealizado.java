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

@Entity(name = "servicios_realizados")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ServicioRealizado {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   Integer id;

   BigDecimal precio;

   BigDecimal descuento = BigDecimal.ZERO;

   String observaciones;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "id_venta")
   Venta venta;

   @ManyToOne(fetch = FetchType.LAZY, optional = false)
   @JoinColumn(name = "id_servicio", nullable = false)
   Servicio servicio;
}
