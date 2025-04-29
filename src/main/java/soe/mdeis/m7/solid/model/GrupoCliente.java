package soe.mdeis.m7.solid.model;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "grupo_cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class GrupoCliente {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "nombre_grupo_cliente")
   private String nombre;

   private BigDecimal descuento = BigDecimal.ZERO;

}