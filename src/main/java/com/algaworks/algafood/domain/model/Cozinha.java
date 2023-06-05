package com.algaworks.algafood.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cozinha {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@JsonIgnore - ignorar a coluna no retorno da api
    private Long id;
    // @JsonProperty("gastronomia") - alterar o valor da coluna no retorno da api
    @Column(nullable = false) // usado para definir a coluna como notNull no banco
    private String nome;
}
