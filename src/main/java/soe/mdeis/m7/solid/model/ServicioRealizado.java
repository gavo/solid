package soe.mdeis.m7.solid.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity(name = "servicios_realizados")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ServicioRealizado {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @NotNull
   private BigDecimal precio;

   private BigDecimal descuento = BigDecimal.ZERO;

   private String observaciones;

   @ManyToOne()
   @JoinColumn(name = "id_venta")
   private Venta venta;

   @ManyToOne(optional = false)
   @JoinColumn(name = "id_servicio", nullable = false)
   private Servicio servicio;
}
