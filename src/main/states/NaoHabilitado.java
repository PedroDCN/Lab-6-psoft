package main.states;

import main.Pessoa;

public class NaoHabilitado implements EstadoVacinacao{

    Pessoa pessoa;

    public NaoHabilitado(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public void mudaEstado() {
        this.pessoa.setEstadoVacinacao(new Habilitado1(pessoa));
    }
}
