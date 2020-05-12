package br.com.spark.produto.api.mapper;

import br.com.spark.produto.api.dto.ProdutoDto;
import br.com.spark.produto.domain.model.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProdutoMapper {

    @Mapping(source = "descricao", target = "descricao")
    @Mapping(source = "preco", target = "preco")
    @Mapping(source = "codigo", target = "codigo")
    ProdutoDto toDto(final Produto produto);

    @Mapping(source = "descricao", target = "descricao")
    @Mapping(source = "preco", target = "preco")
    @Mapping(source = "codigo", target = "codigo")
    Produto toModel(final ProdutoDto dto);
}
