package br.com.spark.inventario.domain.model;

import br.com.spark.inventario.domain.model.enuns.Tipo;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "inventario")
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "codigo_produto", nullable = false, length = 255)
    private String codigoProduto;
    @Column(name = "quantidade_entrada")
    private Long quantidadeEntrada = 0L;
    @Column(name = "quantidade_saida")
    private Long quantidadeSaida = 0L;
    @Column(name = "saldo")
    private Long saldo = 0L;

    public void setQuantidade(final String tipo, final Long quantidade) {
        var operacao = Tipo.toEnum(tipo);

        if(operacao.equals(Tipo.ENTRADA)) {
            this.quantidadeEntrada = quantidade;
            return;
        }

        this.quantidadeSaida = -quantidade;

    }

    public Long getQuantidadeCorrente() {
        return this.getQuantidadeEntrada() > 0 ? this.getQuantidadeEntrada() : this.getQuantidadeSaida();
    }
}
