package planeta;

import recursos.Recurso;

import java.util.List;

public final class Planeta {

    private final int posicao;
    private final List<Recurso> recursos;

    public Planeta(int posicao, List<Recurso> recursos) {
        this.posicao = posicao;
        this.recursos = recursos;
    }

    public int valorTotal(){
        int valorTotal = 0;

        for (Recurso recurso : recursos) {
            valorTotal += recurso.getValor();
        }

        return valorTotal;
    }

    public int valorPorPeso(){
        int aux;
        int valorPorPeso = 0;

        for (Recurso recurso : recursos) {
            aux = recurso.getValor() / recurso.getPeso();
            valorPorPeso += aux;
        }

        return valorPorPeso;
    }

    public int getPosicao() {
        return posicao;
    }

    public List<Recurso> getRecursos() {
        return recursos;
    }
}
