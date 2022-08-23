package nave;

import planeta.Planeta;

import java.util.Comparator;

public enum Comparador {

    POSICAO(Comparator.comparing(Planeta::getPosicao)),
    VALOR_TOTAL(Comparator.comparing(Planeta::valorTotal).reversed()),
    VALOR_POR_PESO(Comparator.comparing(Planeta::valorPorPeso).reversed());

    private final Comparator<Planeta> comparador;

    Comparador(Comparator<Planeta> comparador) {
        this.comparador = comparador;
    }

    public Comparator<Planeta> getComparador() {
        return comparador;
    }
}
