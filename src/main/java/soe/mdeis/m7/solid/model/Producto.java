package soe.mdeis.m7.solid.model;

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
   @Column(name = "precio_lista")
   int precioLista;

   @ManyToOne
   @JoinColumn(name = "id_gru_pro")
   GrupoProducto grupoProducto;

   @ManyToOne
   @JoinColumn(name = "id_prov")
   Proveedor proveedor;

   @ManyToOne
   @JoinColumn(name = "id_fab")
   Fabricante fabricante;

   @ManyToOne
   @JoinColumn(name = "id_alt")
   Producto alternante;
}
