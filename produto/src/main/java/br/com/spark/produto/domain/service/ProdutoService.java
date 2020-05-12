package br.com.spark.produto.domain.service;

import br.com.spark.produto.domain.exceptions.ProdutoNaoEncontradoException;
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

    @Transactional(propagation = Propagation.NEVER)
    public Produto buscarPorCodigo(final String codigo) {
        return repository.findByCodigo(codigo)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado para o codigo: " + codigo));
    }

    @Transactional(propagation = Propagation.NEVER)
    public List<Produto> buscarTodos() {
        return repository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Produto salvar(final Produto produto) {
        produto.setCodigo(UUID.randomUUID().toString());
        return repository.save(produto);
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
