package soe.mdeis.m7.solid.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "servicios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Servicio {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   int id;

   String codigo;

   String nombre;

   BigDecimal precio;

}
