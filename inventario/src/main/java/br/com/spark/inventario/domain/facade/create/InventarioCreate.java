package br.com.spark.inventario.domain.facade.create;

import br.com.spark.inventario.domain.exceptions.InventarioNegativoException;
import br.com.spark.inventario.domain.model.Inventario;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
public class InventarioCreate {

    public Inventario criar(final List<Inventario> inventarios, final Inventario newEntity) {
        Optional.of(inventarios)
                .filter(v -> !v.isEmpty())
                .ifPresentOrElse(invs -> {
                    Long saldo = buscarSaldo(newEntity, invs);
                    newEntity.setSaldo(saldo);
                }, () -> {
                    if(newEntity.getQuantidadeSaida() < 0 ){
                        throw new InventarioNegativoException("Produto não encontra-se no inventario para efetuar saida. Codigo: " + newEntity.getCodigoProduto());
                    }

                    newEntity.setSaldo(newEntity.getQuantidadeEntrada());
                });

        return newEntity;
    }

    private Long buscarSaldo(Inventario inventario, List<Inventario> invs) {
        return invs.stream()
                .max(Comparator.comparing(Inventario::getId))
                .map(inv -> calcularSaldo(inventario, inv)).get();
    }

    private Long calcularSaldo(Inventario inv, Inventario entity) {
        var saldo = entity.getSaldo() + inv.getQuantidadeCorrente();

        if (saldo < 0) {
            throw new InventarioNegativoException("Inventario do produto encontra-se insuficiente. Codigo: " + entity.getCodigoProduto());
        }

        return saldo;
    }
}
