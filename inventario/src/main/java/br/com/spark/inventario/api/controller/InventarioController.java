package br.com.spark.inventario.api.controller;

import br.com.spark.inventario.api.dto.InventarioDto;
import br.com.spark.inventario.api.mapper.InventarioMapper;
import br.com.spark.inventario.domain.service.InventarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/inventario")
public class InventarioController {

    private final InventarioService inventarioService;
    private final InventarioMapper mapper;

    @GetMapping("/{codigoProduto}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public InventarioDto buscarPorId(@PathVariable String codigoProduto) {
        log.info("Realizando busca do inventario do produto: " + codigoProduto);
        return mapper.toDto(inventarioService.buscarPorId(codigoProduto));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criar(@RequestBody @Valid InventarioDto dto) {
        inventarioService.salvar(mapper.toDomain(dto));
    }
}
