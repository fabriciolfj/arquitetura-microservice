package br.com.spark.inventario.domain.service;

import br.com.spark.inventario.domain.exceptions.InventarioNaoLocalizadoException;
import br.com.spark.inventario.domain.model.Inventario;
import br.com.spark.inventario.domain.repository.InventarioRepository;
import br.com.spark.inventario.domain.service.facade.create.InventarioCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventarioService {

    private final InventarioRepository repository;
    private final InventarioCreate inventarioCreate;

    public void salvar(final Inventario inventario) {
        var newEntity = inventarioCreate.criar(buscarHistoricoProduto(inventario.getCodigoProduto()), inventario);
        repository.save(newEntity);
    }

    public Inventario buscarPorId(final String codigoProduto) {
        return Optional.ofNullable(repository.findByCodigoProduto(codigoProduto))
                .filter(p -> !p.isEmpty())
                .map(p -> p.stream().max(Comparator.comparing(Inventario::getId)).get())
                .orElseThrow(() -> new InventarioNaoLocalizadoException("Inventario não localizado para produto: " + codigoProduto));
    }

    private List<Inventario> buscarHistoricoProduto(final String codigo) {
        return repository.findByCodigoProduto(codigo);
    }
}
