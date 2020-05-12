package br.com.spark.inventario.domain.model.enuns;

import br.com.spark.inventario.domain.exceptions.TipoNaoEncontradoException;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum Tipo {

    ENTRADA("entrada"), SAIDA("saida");

    private String descricao;

    Tipo(final String descricao) {
        this.descricao = descricao;
    }

    public static Tipo toEnum(final String descricao) {
        return Stream.of(Tipo.values()).filter(t -> t.getDescricao().equals(descricao)).findFirst()
                .orElseThrow(() -> new TipoNaoEncontradoException("Tipo não encontrado: " + descricao));
    }
}
