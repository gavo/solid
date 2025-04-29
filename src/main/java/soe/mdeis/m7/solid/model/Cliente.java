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
import lombok.*;

@Entity(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Cliente {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "nombre_cliente")
   private String nombre;

   private String code;

   private String documento;

   @Enumerated(EnumType.STRING)
   @Column(name = "tipo_documento")
   private TipoDocumento tipoDocumento;

   private String email;

   @ManyToOne
   @JoinColumn(name = "id_grupo_clientes", nullable = true)
   private GrupoCliente grupoCliente;
}
