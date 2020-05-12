package br.com.spark.produto.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdutoDto {

    private String descricao;
    private BigDecimal preco;
    private String codigo;
}
