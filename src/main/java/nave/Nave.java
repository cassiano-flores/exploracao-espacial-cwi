package nave;

import planeta.Planeta;
import recursos.Agua;
import recursos.Recurso;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

public class Nave {

    private int quantidadeDeCombustivel;
    private int posicao;
    private static final int COMBUSTIVEL_GASTO_POR_POSICAO = 3;
    private Comparador comparador;

    public Nave(int quantidadeDeCombustivel) {
        this.quantidadeDeCombustivel = quantidadeDeCombustivel;
        this.posicao = 0;
        this.comparador = Comparador.POSICAO;
    }

    public int getQuantidadeDeCombustivel() {
        return quantidadeDeCombustivel;
    }

    public int getPosicao() {
        return posicao;
    }

//    public Comparador getComparador() {
//        return comparador;
//    }

    public void setComparador(Comparador comparador) {  //método bônus
        this.comparador = comparador;
    }

    public List<Recurso> explorar(List<Planeta> planetas) {

        if (this.quantidadeDeCombustivel < COMBUSTIVEL_GASTO_POR_POSICAO) {
            return emptyList();
        }

        planetas.sort(this.comparador.getComparador());

        List<Recurso> coletados = new ArrayList<>();
        planetas.forEach(planeta -> visitar(planeta, coletados));

        voltar();
        return coletados;
    }

    private void visitar(Planeta planeta, List<Recurso> recursosObtidos) {
        if (this.posicao == planeta.getPosicao()) {
            recursosObtidos.addAll(planeta.getRecursos());
        } else {
            int distancia = planeta.getPosicao() - this.posicao;
            int custoViagem = distancia * COMBUSTIVEL_GASTO_POR_POSICAO;
            if (this.quantidadeDeCombustivel >= custoViagem) {
                this.posicao = planeta.getPosicao();
                this.quantidadeDeCombustivel -= custoViagem;
                recursosObtidos.addAll(planeta.getRecursos());
            } else {
                int quantoDaPraAndar = this.quantidadeDeCombustivel / COMBUSTIVEL_GASTO_POR_POSICAO;
                this.quantidadeDeCombustivel = this.quantidadeDeCombustivel % COMBUSTIVEL_GASTO_POR_POSICAO;
                this.posicao = this.posicao + quantoDaPraAndar;
            }
        }
    }

    private void voltar() {
        int custoViagem = this.posicao * COMBUSTIVEL_GASTO_POR_POSICAO;
        if (this.quantidadeDeCombustivel >= custoViagem) {
            this.posicao = 0;
            this.quantidadeDeCombustivel -= custoViagem;
        } else {
            int quantoDaPraAndar = this.quantidadeDeCombustivel / COMBUSTIVEL_GASTO_POR_POSICAO;
            this.quantidadeDeCombustivel = this.quantidadeDeCombustivel % COMBUSTIVEL_GASTO_POR_POSICAO;
            this.posicao = this.posicao - quantoDaPraAndar;
        }
    }
}
