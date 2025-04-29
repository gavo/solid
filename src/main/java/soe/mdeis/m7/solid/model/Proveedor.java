package soe.mdeis.m7.solid.model;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "proveedores")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Proveedor {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "nombre_proveedor")
   private String nombre;
}
