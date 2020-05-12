package br.com.spark.inventario.domain.repository;

import br.com.spark.inventario.domain.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {

    List<Inventario> findByCodigoProduto(final String codigo);
}
