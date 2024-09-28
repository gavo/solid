package soe.mdeis.m7.solid.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cliente {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   Integer id;

   String nombre;

   String code;

   String documento;

   @Enumerated(EnumType.STRING)
   @Column(name = "tipo_documento")
   TipoDocumento tipoDocumento;

   String email;

   @ManyToOne
   @JoinColumn(name = "id_grupo_clientes", nullable = true)
   GrupoCliente grupoCliente;
}
