package br.com.spark.inventario.api.mapper;

import br.com.spark.inventario.api.dto.InventarioDto;
import br.com.spark.inventario.domain.model.Inventario;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
@DecoratedWith(InventarioMapperDecorator.class)
public interface InventarioMapper {

    @Mapping(source = "codigoProduto", target = "codigoProduto")
    Inventario toDomain(final InventarioDto dto);

    @Mapping(source = "codigoProduto", target = "codigoProduto")
    @Mapping(source = "saldo", target = "quantidade")
    InventarioDto toDto(final Inventario inventario);
}
