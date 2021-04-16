package main.states;

import main.Pessoa;

public class FinalizadaVacinacao implements EstadoVacinacao{

    Pessoa pessoa;

    public FinalizadaVacinacao(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public void mudaEstado() {
    }
}
