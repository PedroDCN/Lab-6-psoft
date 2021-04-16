package main.states;

import main.Pessoa;

public class Habilitado2 implements EstadoVacinacao{

    Pessoa pessoa;

    public Habilitado2(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public void mudaEstado() {
        this.pessoa.setEstadoVacinacao(new FinalizadaVacinacao(pessoa));
    }
}
