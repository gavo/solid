package soe.mdeis.m7.solid.model;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "fabricantes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Fabricante {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "nombre_fabricante")
   private String nombre;
}
