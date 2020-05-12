package br.com.spark.inventario.api.mapper;

import br.com.spark.inventario.api.dto.InventarioDto;
import br.com.spark.inventario.domain.model.Inventario;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
public abstract class InventarioMapperDecorator implements InventarioMapper {

    @Autowired
    private InventarioMapper mapper;

    @Override
    public Inventario toDomain(InventarioDto dto) {
        var inventario = mapper.toDomain(dto);
        inventario.setQuantidade(dto.getTipo(), dto.getQuantidade());
        return inventario;
    }

    @Override
    public InventarioDto toDto(Inventario inventario) {
        return mapper.toDto(inventario);
    }
}
