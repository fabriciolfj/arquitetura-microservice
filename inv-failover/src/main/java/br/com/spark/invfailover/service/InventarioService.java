package br.com.spark.invfailover.service;


import br.com.spark.invfailover.api.dto.InventarioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventarioService {

    public InventarioDto buscarPorId() {
        return InventarioDto.builder()
                .codigoProduto(UUID.randomUUID().toString())
                .quantidade(9999L)
                .tipo("Fallback")
                .build();
    }
}
