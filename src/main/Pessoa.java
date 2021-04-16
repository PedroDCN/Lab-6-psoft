package main;

import main.states.*;

import java.util.Set;

public class Pessoa {

    private String nome, endereco, email, profissao, telefone, cpf, numerosus;
    private Set<String> comorbidades;
    private int idade;

    private EstadoVacinacao estadoVacinacao;

    public Pessoa(String nome, int idade, String cpf, String endereco, String numerosus,
                  String email, String telefone, String profissao, Set<String> comorbidades) {
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.profissao = profissao;
        this.comorbidades = comorbidades;
        this.telefone = telefone;
        this.cpf = cpf;
        this.numerosus = numerosus;
        this.idade = idade;

        this.estadoVacinacao = new NaoHabilitado(this);
    }

    public void setEstadoVacinacao(EstadoVacinacao newEstadoVacinacao) {
        this.estadoVacinacao = newEstadoVacinacao;
    }

    public void mudaEstado() {
        this.estadoVacinacao.mudaEstado();
    }

    public void novaAlteracaoGoverno(String profissao, String comorbidade, int idade) {
        if ((profissao != null) && (this.profissao.equals(profissao)) && (estadoVacinacao instanceof NaoHabilitado)) {
            this.mudaEstado();
        } else
        if ((comorbidade != null) && (comorbidades.contains(comorbidade)) && (estadoVacinacao instanceof NaoHabilitado)) {
            this.mudaEstado();
        } else
        if ((idade >= 0) && (this.idade == idade) && (estadoVacinacao instanceof NaoHabilitado)) {
            this.mudaEstado();
        }
    }

    public boolean tomaVacina() {
        if (this.estadoVacinacao instanceof Habilitado1 || this.estadoVacinacao instanceof Habilitado2) {
            this.mudaEstado();
            return true;
        } else {
            return false;
        }
    }

    public boolean passa20dias() {
        if (this.estadoVacinacao instanceof Tomou) {
            this.mudaEstado();
            return true;
        } else {
            return false;
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public Set<String> getComorbidades() {
        return comorbidades;
    }

    public void setComorbidades(Set<String> comorbidades) {
        this.comorbidades = comorbidades;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String CPF) {
        this.cpf = CPF;
    }

    public String getNumerosus() {
        return numerosus;
    }

    public void setNumerosus(String numerosus) {
        this.numerosus = numerosus;
    }

    public String toString() {
        return "Paciente: " + getNome() + ", estado: " + getEstado() + ", idade: " + getIdade() + ", cpf: " + getCpf() + ", número do SUS: " + getNumerosus() + ", profissão: " + getProfissao() +
                ", endereço: " + getEndereco() + ", email: " + getEmail() + ", telefone: " + getTelefone() + ", comorbidades: " + getComorbidades().toString();
    }

    private String getEstado() {
        if (this.estadoVacinacao instanceof NaoHabilitado)
            return "Não habilitado";
        else if (this.estadoVacinacao instanceof Habilitado1 || this.estadoVacinacao instanceof Habilitado2)
            return "Habilitado";
        else if (this.estadoVacinacao instanceof Tomou)
            return "Vacinado apenas primeira dose";
        else if (this.estadoVacinacao instanceof FinalizadaVacinacao)
            return "Vacinado";
        return "Não habilitado";
    }
}
