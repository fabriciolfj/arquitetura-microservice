package br.com.spark.inventario.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InventarioDto {

    @JsonProperty(value = "codigo_produto")
    @NotEmpty(message = "Informe o codigo do produto")
    private String codigoProduto;
    @Positive
    @NotNull
    private Long quantidade;
    @NotEmpty
    private String tipo;
}
