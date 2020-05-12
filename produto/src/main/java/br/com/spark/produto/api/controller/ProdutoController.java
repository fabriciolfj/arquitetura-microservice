package br.com.spark.produto.api.controller;

import br.com.spark.produto.api.dto.ProdutoDto;
import br.com.spark.produto.api.mapper.ProdutoMapper;
import br.com.spark.produto.domain.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/produtos")
@RestController
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoMapper mapper;
    private final ProdutoService service;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/{codigo}")
    public ProdutoDto buscarPorId(@PathVariable final String codigo) {
        return mapper.toDto(service.buscarPorCodigo(codigo));
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping
    public List<ProdutoDto> listar() {
        return service.buscarTodos()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{codigo}")
    public void atualizar(@PathVariable final String codigo, @RequestBody ProdutoDto dto) {
        service.atualizar(mapper.toModel(dto), codigo);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void criar(@RequestBody ProdutoDto dto) {
        service.salvar(mapper.toModel(dto));
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{codigo}")
    public void remover(@PathVariable final String codigo) {
        service.deletar(codigo);
    }

}
