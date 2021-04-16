package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PessoaRepository {

    private List<Pessoa> pessoas = new ArrayList<Pessoa>();

    public Pessoa adicionaPessoa(String nome, int idade, String cpf, String endereco, String numerosus,
                                 String email, String telefone, String profissao, Set<String> comorbidadesPaciente) {

        Pessoa pessoa1 = getPessoaNome(nome);
        if (nome.isEmpty() || pessoa1 != null)
            return null;

        Pessoa pessoa2 = getPessoaCPF(cpf);
        if (cpf.isEmpty() || pessoa2 != null)
            return null;

        Pessoa pessoa3 = getPessoaSUS(numerosus);
        if (numerosus.isEmpty() || pessoa1 != null)
            return null;

        Pessoa pessoa = new Pessoa(nome, idade, cpf, endereco, numerosus, email, telefone, profissao, comorbidadesPaciente);
        pessoas.add(pessoa);
        return pessoa;
    }

    public List<Pessoa> getPessoas() {
        return this.pessoas;
    }

    public String getAllPessoas() {
        String msg = "";
        for (Pessoa pessoa : pessoas) {
            msg += pessoa.toString() + "\n";
        }
        if (msg.isEmpty())
            return "Sem pacientes cadastrados no sistema.";
        return msg;
    }

    public Pessoa getPessoaCPF(String cpf) {
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getCpf().equals(cpf))
                return pessoa;
        }
        return null;
    }

    public Pessoa getPessoaSUS(String numerosus) {
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getNumerosus().equals(numerosus))
                return pessoa;
        }
        return null;
    }

    public Pessoa getPessoaNome(String nome) {
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getNome().equals(nome))
                return pessoa;
        }
        return null;
    }

    public boolean removePessoaCPF(String cpf) {
        Pessoa pessoa = getPessoaCPF(cpf);
        if (pessoa == null)
            return false;
        pessoas.remove(pessoa);
        return true;
    }

    public boolean removePessoaSUS(String numerosus) {
        Pessoa pessoa = getPessoaSUS(numerosus);
        if (pessoa == null)
            return false;
        pessoas.remove(pessoa);
        return true;
    }

    public boolean removePessoaNome(String nome) {
        Pessoa pessoa = getPessoaCPF(nome);
        if (pessoa == null)
            return false;
        pessoas.remove(pessoa);
        return true;
    }

    public boolean atualizaPessoaNome(Pessoa pessoa, String nome) {
        Pessoa pessoa1 = getPessoaNome(nome);
        if (nome.isEmpty() || (pessoa1 != null && !pessoa.equals(pessoa1)))
            return false;
        pessoa.setNome(nome);
        return true;
    }

    public boolean atualizaPessoaCPF(Pessoa pessoa, String cpf) {
        Pessoa pessoa1 = getPessoaCPF(cpf);
        if (cpf.isEmpty() || (pessoa1 != null && !pessoa.equals(pessoa1)))
            return false;
        pessoa.setCpf(cpf);
        return true;
    }

    public boolean atualizaPessoaNumSUS(Pessoa pessoa, String numsus) {
        Pessoa pessoa1 = getPessoaSUS(numsus);
        if (numsus.isEmpty() || (pessoa1 != null && !pessoa.equals(pessoa1)))
            return false;
        pessoa.setNumerosus(numsus);
        return true;
    }

    public void atualizaPessoaIdade(Pessoa pessoa, int idade) {
        pessoa.setIdade(idade);
    }

    public void atualizaPessoaEndereco(Pessoa pessoa, String endereco) {
        pessoa.setEndereco(endereco);
    }

    public void atualizaPessoaEmail(Pessoa pessoa, String email) {
        pessoa.setEmail(email);
    }

    public void atualizaPessoaTelefone(Pessoa pessoa, String telefone) {
        pessoa.setTelefone(telefone);
    }

    public void atualizaPessoaProfissao(Pessoa pessoa, String profissao) {
        pessoa.setProfissao(profissao);
    }

    public void atualizaPessoaComorbidadaes(Pessoa pessoa, Set<String> comorbidades) {
        pessoa.setComorbidades(comorbidades);
    }

    public boolean tomarVacina(Pessoa pessoa) {
        return pessoa.tomaVacina();
    }

    public boolean atualiza20dias(Pessoa pessoa) {
        return pessoa.passa20dias();
    }
}
