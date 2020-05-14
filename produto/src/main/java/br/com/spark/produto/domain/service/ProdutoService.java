package br.com.spark.produto.domain.service;

import br.com.spark.produto.api.dto.InventarioDto;
import br.com.spark.produto.domain.exceptions.ProdutoNaoEncontradoException;
import br.com.spark.produto.domain.facade.fetcher.InventarioFetcher;
import br.com.spark.produto.domain.mensageria.ProdutoProducer;
import br.com.spark.produto.domain.model.Produto;
import br.com.spark.produto.domain.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;
    private final InventarioFetcher inventarioFetcher;
    private final ProdutoProducer produtoProducer;

    @Transactional(propagation = Propagation.NEVER)
    public Produto buscarPorCodigo(final String codigo) {
        return repository.findByCodigo(codigo)
                .map(p -> {
                    var dto = inventarioFetcher.getInventario(codigo);
                    p.setQuantidade(dto.getQuantidade());
                    return p;
                })
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado para o codigo: " + codigo));
    }

    @Transactional(propagation = Propagation.NEVER)
    public List<Produto> buscarTodos() {
        return repository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Produto salvar(final Produto produto) {
        produto.setCodigo(UUID.randomUUID().toString());
        repository.save(produto);
        alimentarInventario(produto);
        return produto;
    }

    private void alimentarInventario(Produto produto) {
        InventarioDto dto = InventarioDto.builder()
                .codigoProduto(produto.getCodigo())
                .tipo("entrada")
                .codigoProduto(produto.getCodigo())
                .quantidade(produto.getQuantidade())
                .build();
        produtoProducer.send(dto);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deletar(final String codigo) {
        try {
            var produto = buscarPorCodigo(codigo);
            repository.delete(produto);
        } catch (Exception e) { }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void atualizar(final Produto produto, final String codigo) {
        Optional.ofNullable(buscarPorCodigo(codigo))
                .map(p -> {
                    p.setDescricao(produto.getDescricao());
                    p.setPreco(produto.getPreco());
                    return repository.save(p);
                });
    }
}
