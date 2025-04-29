package soe.mdeis.m7.solid.model;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "almacenes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Almacen {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "nombre_almacen")
   private String nombre;

}
