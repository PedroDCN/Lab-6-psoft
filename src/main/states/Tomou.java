package main.states;

import main.Pessoa;

public class Tomou implements EstadoVacinacao{

    Pessoa pessoa;

    public Tomou(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public void mudaEstado() {
        this.pessoa.setEstadoVacinacao(new Habilitado2(pessoa));
    }
}
