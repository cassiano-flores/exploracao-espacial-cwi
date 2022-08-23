import nave.Comparador;
import nave.Nave;
import org.junit.Assert;
import org.junit.Test;
import planeta.Planeta;
import recursos.*;

import java.util.ArrayList;
import java.util.List;

public class NaveTest {

    @Test
    public void deveFicarADerivaQuandoFaltarCombustivelParaIrAteUmPlaneta() {
        //arrange
        Nave milleniumFalcon = new Nave(9);
        List<Planeta> planetas = new ArrayList<>();
        Planeta tatooine = new Planeta(4, new ArrayList<>());
        planetas.add(tatooine);
        int posicaoEsperada = 3;

        //act
        milleniumFalcon.explorar(planetas);
        int posicaoResultante = milleniumFalcon.getPosicao();

        //assert
        Assert.assertEquals(posicaoEsperada, posicaoResultante);
    }

    @Test
    public void deveColetarRecursosEVoltarQuandoTiverGasolinaSuficiente(){
        //arrange
        Nave nave = new Nave(10000);
        List<Planeta> planetas = new ArrayList<>();
        Planeta tatooine = new Planeta(1000, new ArrayList<>());
        Planeta marte = new Planeta(500, new ArrayList<>());
        planetas.add(tatooine);
        planetas.add(marte);

        //act
        nave.explorar(planetas);

        //assert
        Assert.assertEquals(4000, nave.getQuantidadeDeCombustivel());
        Assert.assertEquals(0, nave.getPosicao());
    }

    @Test
    public void deveTerValorTotalZeradoQuandoNaoExistirNenhumRecurso(){
        //arrange
        List<Planeta> planetas = new ArrayList<>();
        Planeta marte = new Planeta(1000, new ArrayList<>());
        planetas.add(marte);

        int valorEsperado = 0;

        //act
        int valorResultante = marte.valorTotal();

        //assert
        Assert.assertEquals(valorEsperado, valorResultante);
    }

    @Test
    public void deveTerValorTotalQuandoExistirRecursosNoPlaneta(){
        //arrange
        List<Recurso> recursos = new ArrayList<>();
        recursos.add(new Agua());
        recursos.add(new Ferro());

        Planeta marte = new Planeta(1000, recursos);
        List<Planeta> planetas = new ArrayList<>();
        planetas.add(marte);

        int valorEsperado = 210;

        //act
        int valorResultante = marte.valorTotal();

        //assert
        Assert.assertEquals(valorEsperado, valorResultante);
    }

    @Test
    public void deveTerValorPorPesoZeradoQuandoNaoExistirNenhumRecurso(){
        //arrange
        List<Planeta> planetas = new ArrayList<>();
        Planeta marte = new Planeta(1000, new ArrayList<>());
        planetas.add(marte);

        int valorEsperado = 0;

        //act
        int valorResultante = marte.valorPorPeso();

        //assert
        Assert.assertEquals(valorEsperado, valorResultante);

    }

    @Test
    public void deveTerValorPorPesoQuandoExistirRecursosNoPlaneta(){
        //arrange
        List<Recurso> recursos = new ArrayList<>();
        recursos.add(new Agua());
        recursos.add(new Oxigenio());

        Planeta marte = new Planeta(1000, recursos);
        List<Planeta> planetas = new ArrayList<>();
        planetas.add(marte);

        int valorEsperado = 168;

        //act
        int valorResultante = marte.valorPorPeso();

        //assert
        Assert.assertEquals(valorEsperado, valorResultante);

    }

    @Test
    public void deveAlterarPrioridadeEColetarTodosRecursosQuandoExplorado(){
        Nave nave = new Nave(3000);
        nave.setComparador(Comparador.VALOR_TOTAL);   //setando outra prioridade

        List<Recurso> recurso = new ArrayList<>();
        recurso.add(new Agua());
        recurso.add(new Ouro());
        recurso.add(new Oxigenio());
        recurso.add(new Ferro());
        recurso.add(new Silicio());

        List<Planeta> planetas = new ArrayList<>();
        Planeta saturno = new Planeta(1000, recurso);
        planetas.add(saturno);

        //act
        List<Recurso> recursosObtidos = nave.explorar(planetas);

        //assert
        Assert.assertNotNull(recursosObtidos);
    }
}
