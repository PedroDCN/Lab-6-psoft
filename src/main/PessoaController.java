package main;


import java.util.*;

public class PessoaController {

    private PessoaRepository pacientes = new PessoaRepository();
    Set<String> profissoes = new HashSet<String>();
    Set<Integer> idades = new HashSet<Integer>();
    Set<String> comorbidades = new HashSet<String>();

    public boolean adicionaPessoa(String nome, String idade, String cpf, String endereco, String numerosus,
                                  String email, String telefone, String profissao, Set<String> comorbidadesPaciente) {
        int newIdade = 0;
        try {
            newIdade = Integer.parseInt(idade);
        } catch (NumberFormatException e) {
            return false;
        }

        Pessoa pessoa = pacientes.adicionaPessoa(nome, newIdade, cpf, endereco, numerosus, email, telefone, profissao, comorbidadesPaciente);
        if (pessoa != null) {
            notifyPacienteAtualizado(pessoa);
            return true;
        }
        return false;
    }

    public String getAllPessoas() {
        return pacientes.getAllPessoas();
    }

    public Pessoa getPessoaCPF(String cpf) {
        return pacientes.getPessoaCPF(cpf);
    }

    public Pessoa getPessoaSUS(String numerosus) {
        return pacientes.getPessoaSUS(numerosus);
    }

    public Pessoa getPessoaNome(String nome) {
        return pacientes.getPessoaNome(nome);
    }

    public boolean removePessoaCPF(String cpf) {
        return pacientes.removePessoaCPF(cpf);
    }

    public boolean removePessoaSUS(String numerosus) {
        return pacientes.removePessoaSUS(numerosus);
    }

    public boolean removePessoaNome(String nome) {
        return pacientes.removePessoaNome(nome);
    }

    public boolean atualizaPessoaNome(Pessoa pessoa, String nome) {
        return pacientes.atualizaPessoaNome(pessoa, nome);
    }

    public boolean atualizaPessoaCPF(Pessoa pessoa, String cpf) {
        return pacientes.atualizaPessoaCPF(pessoa, cpf);
    }

    public boolean atualizaPessoaNumSUS(Pessoa pessoa, String numsus) {
        return pacientes.atualizaPessoaNumSUS(pessoa, numsus);
    }

    public boolean atualizaPessoaIdade(Pessoa pessoa, String idade) {
        int nIdade = 0;
        try {
            nIdade = Integer.parseInt(idade);
        } catch (NumberFormatException e) {
            return false;
        }
        pacientes.atualizaPessoaIdade(pessoa, nIdade);
        notifyPacienteAtualizado(pessoa);
        return true;
    }

    public void atualizaPessoaEndereco(Pessoa pessoa, String endereco) {
        pacientes.atualizaPessoaEndereco(pessoa, endereco);
    }

    public void atualizaPessoaEmail(Pessoa pessoa, String email) {
        pacientes.atualizaPessoaEmail(pessoa, email);
    }

    public void atualizaPessoaTelefone(Pessoa pessoa, String telefone) {
        pacientes.atualizaPessoaTelefone(pessoa, telefone);
    }

    public void atualizaPessoaProfissao(Pessoa pessoa, String profissao) {
        pacientes.atualizaPessoaProfissao(pessoa, profissao);
        notifyPacienteAtualizado(pessoa);
    }

    public void atualizaPessoaComorbidadaes(Pessoa pessoa, Set<String> comorbidades) {
        pacientes.atualizaPessoaComorbidadaes(pessoa, comorbidades);
        notifyPacienteAtualizado(pessoa);
    }

    public boolean alteracaoGovernoProfissaoAdd(String profissao) {
        if (profissao.isEmpty()) {
            return false;
        }
        if (profissoes.contains(profissao)) {
            return true;
        }

        profissoes.add(profissao);
        notifyPacientes(profissao, null, 0);
        return true;
    }

    public boolean alteracaoGovernoProfissaoRm(String profissao) {
        if (profissao.isEmpty()) {
            return true;
        }
        if (!profissoes.contains(profissao)) {
            return false;
        }

        profissoes.remove(profissao);
        return true;
    }

    public boolean alteracaoGovernoIdadeAdd(String idade) {
        int ni = 0;
        try {
            ni = Integer.parseInt(idade);
        } catch (NumberFormatException e) {
            return false;
        }

        if (ni <= 0) {
            return false;
        }
        if (idades.contains(ni)) {
            return true;
        }

        idades.add(ni);
        notifyPacientes(null, null, ni);
        return true;
    }

    public boolean alteracaoGovernoIdadeRm(String idade) {
        int ni = 0;
        try {
            ni = Integer.parseInt(idade);
        } catch (NumberFormatException e) {
            return false;
        }

        if (ni <= 0) {
            return false;
        }
        if (!idades.contains(ni)) {
            return false;
        }

        idades.remove(ni);
        return true;
    }

    public boolean alteracaoGovernoComorbidadeAdd(String comorbidade) {
        if (comorbidade.isEmpty()) {
            return false;
        }
        if (comorbidades.contains(comorbidade)) {
            return true;
        }

        comorbidades.add(comorbidade);
        notifyPacientes(null, comorbidade, 0);
        return true;
    }

    public boolean alteracaoGovernoComorbidadeRm(String comorbidade) {
        if (comorbidade.isEmpty()) {
            return true;
        }
        if (!comorbidades.contains(comorbidade)) {
            return false;
        }

        comorbidades.remove(comorbidade);
        notifyPacientes(null, comorbidade, 0);
        return true;
    }

    public String getInfoGoverno() {
        String msg = "";
        msg += "Profissões habilitadas:\n" + profissoes.toString() +"\n";
        msg += "Idades habilitadas:\n" + idades.toString() + "\n";
        msg += "Comorbidades habilitadas:\n" + comorbidades.toString() + "\n";
        return msg;
    }

    public void notifyPacientes(String profissao, String comorbidade, int idade) {
        for (Pessoa paciente : pacientes.getPessoas()) {
            notifyPaciente(paciente, profissao, comorbidade, idade);
        }
    }

    public void notifyPaciente(Pessoa paciente, String profissao, String comorbidade, int idade) {
        paciente.novaAlteracaoGoverno(profissao, comorbidade, idade);
    }

    public void notifyPacienteAtualizado(Pessoa pessoa) {
        String newprofissaoPaciente = pessoa.getProfissao();
        int newidadePaciente  = pessoa.getIdade();
        Set<String> newcomorbidadesPaciente = pessoa.getComorbidades();

        boolean newComorbidade = false;
        String comorbidade = "";
        for (String c : newcomorbidadesPaciente) {
            if (comorbidades.contains(c)){
                newComorbidade = true;
                comorbidade = c;
                break;
            }
        }

        if (profissoes.contains(newprofissaoPaciente)) {
            notifyPaciente(pessoa, newprofissaoPaciente, null, 0);
        } else if (newComorbidade) {
            notifyPaciente(pessoa, null, comorbidade, 0);
        } else if (idades.contains(newidadePaciente)) {
            notifyPaciente(pessoa, null, null, newidadePaciente);
        }
    }

    public boolean tomarVacina(Pessoa pessoa) {
        return pacientes.tomarVacina(pessoa);
    }

    public boolean atualiza20dias(Pessoa pessoa) {
        return pacientes.atualiza20dias(pessoa);
    }
}