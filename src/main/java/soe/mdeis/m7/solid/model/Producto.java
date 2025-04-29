package soe.mdeis.m7.solid.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Producto {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "nombre_producto")
   private String nombre;

   @Column(name = "nombre_producto_extranjero")
   private String nombreExtranjero;

   private int peso;

   @Column(name = "cod_barra")
   private String codBarra;

   private String um;

   private BigDecimal precio = BigDecimal.ZERO;

   @ManyToOne
   @JoinColumn(name = "id_grupo_producto")
   private GrupoProducto grupoProducto;

   @ManyToOne()
   @JoinColumn(name = "id_proveedor")
   private Proveedor proveedor;

   @ManyToOne()
   @JoinColumn(name = "id_fabricante")
   private Fabricante fabricante;

   @ManyToOne()
   @JoinColumn(name = "id_producto")
   private Producto alternante;
}
