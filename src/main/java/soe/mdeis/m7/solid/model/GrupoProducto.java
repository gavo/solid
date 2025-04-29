package soe.mdeis.m7.solid.model;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "grupos_productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class GrupoProducto {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "nombre_grupo_producto")
   private String nombre;
}
