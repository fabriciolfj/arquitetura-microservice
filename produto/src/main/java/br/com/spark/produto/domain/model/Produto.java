package br.com.spark.produto.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "produto")
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Long id;
    @Column(name = "descricao", nullable = false, length = 50)
    private String descricao;
    @Column(name = "preco", precision = 4, nullable = false)
    private BigDecimal preco;
    @Column(name = "codigo", length = 255)
    private String codigo;
}
