package soe.mdeis.m7.solid.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Producto {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   Integer id;

   String nombre;

   @Column(name = "nombre_extranjero")
   String nombreExtranjero;

   int peso;

   @Column(name = "cod_barra")
   String codBarra;

   String um;

   BigDecimal precio = BigDecimal.ZERO;

   @ManyToOne
   @JoinColumn(name = "id_grupo_producto")
   GrupoProducto grupoProducto;

   @ManyToOne()
   @JoinColumn(name = "id_proveedor")
   Proveedor proveedor;

   @ManyToOne()
   @JoinColumn(name = "id_fabricante")
   Fabricante fabricante;

   @ManyToOne()
   Producto alternante;
}
