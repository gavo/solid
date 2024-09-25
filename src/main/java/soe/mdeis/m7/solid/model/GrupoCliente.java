package soe.mdeis.m7.solid.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "grupo_cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GrupoCliente {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   Integer id;

   @Column(name = "nombre")
   String nombre;

   Integer descuento;

   Boolean estado;
}