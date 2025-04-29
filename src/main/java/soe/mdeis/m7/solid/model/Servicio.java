package soe.mdeis.m7.solid.model;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "servicios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Servicio {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String codigo;

   @Column(name = "nombre_servicio")
   private String nombre;

   private BigDecimal precio;

}
