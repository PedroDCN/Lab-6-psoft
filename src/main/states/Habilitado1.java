package main.states;

import main.Pessoa;

public class Habilitado1 implements EstadoVacinacao{

    Pessoa pessoa;

    public Habilitado1(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public void mudaEstado() {
        this.pessoa.setEstadoVacinacao(new Tomou(pessoa));
    }
}
