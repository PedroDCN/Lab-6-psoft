package main;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SistemaController {

    private final String OPCOES = "\nSISTEMA DE VACINAÇÃO\n" +
            "Qual operação você deseja realizar:\n" +
            "1 - Cadastrar paciente no sistema\n" +
            "2 - Remover paciente do sistema\n" +
            "3 - Visualizar todos os pacientes do sistema\n" +
            "4 - Visualizar dados de um paciente do sistema\n" +
            "5 - Atualizar dados de um paciente sistema\n" +
            "6 - Visualizar informações sobre profissões, faixas etárias e comorbidades habilitadas pelo governo\n" +
            "7 - Atualizar informações sobre profissões, faixas etárias e comorbidades habilitadas\n" +
            "8 - Atualizar um paciente que tomou a vacina\n" +
            "9 - Atualizar um paciente depois de 20 dias desde a primeira dose\n" +
            "10 - Sair\n" +
            "opção: ";

    private final String OPCAOADD = "\nInsira as informações de cadastro do paciente (campos obrigatórios: nome, CPF, número SUS, idade): \n";

    private final String OPCAOREMOVE = "\nPara remover um paciente do sistema, insira qual informação usar para procurar pelo paciente:\n" +
            "1 - Nome\n" +
            "2 - CPF\n" +
            "3 - Número Cartão SUS\n" +
            "opção: ";

    private final String OPCAOLISTALL = "\nListando todos os pacientes cadastrados no sistema:\n";

    private final String OPCAOLIST = "\nPara listar as informações de um paciente do sistema, insira qual informação usar para procurar pelo paciente:\n" +
            "1 - Nome\n" +
            "2 - CPF\n" +
            "3 - Número Cartão SUS\n" +
            "opção: ";

    private final String OPCAOATT = "\nPara atualizar um paciente do sistema, insira qual informação usar para procurar pelo paciente:\n" +
            "1 - Nome\n" +
            "2 - CPF\n" +
            "3 - Número Cartão SUS\n" +
            "opção: ";

    private final String OPCAOATTOP = "\nSelecione qual informação a ser atualizada:\n" +
            "1 - Nome: \n" +
            "2 - CPF: \n" +
            "3 - Número do SUS\n" +
            "4 - Idade\n" +
            "5 - Endereço (linha única)\n" +
            "6 - Endereço de email\n" +
            "7 - Telefone\n" +
            "8 - Profissão\n" +
            "9 - Comorbidades (para mais de uma, separar elas com ',')\n" +
            "opção: ";

    private final String OPCAOLISTINFO = "\nListando as informações do sistema selecionadas pelo governo\n";

    private final String OPCAOATTINFO = "\nSelecione qual informação a ser atualizada:\n" +
            "1 - Profissão\n" +
            "2 - Faixa etária\n" +
            "3 - Comorbidades\n" +
            "opção: ";

    private final String OPCAOATTINFOAR = "\nIrá adicionar ou remover informação?\n" +
            "1 - Adicionar\n" +
            "2 - Remover\n" +
            "opção: ";

    private final String OPCAOTV = "\nPara dar a vacina a um paciente do sistema, insira qual informação usar para procurar pelo paciente:\n"+
            "1 - Nome\n" +
            "2 - CPF\n" +
            "3 - Número Cartão SUS\n" +
            "opção: ";

    private final String OPCAOINVALIDA = "Valor de opção inválido.";

    PessoaController controllerPacientes;

    public SistemaController() {
        this.controllerPacientes = new PessoaController();
    }

    public void mainloop() {

        Scanner sc = new Scanner(System.in);
        boolean loop = true;

        while (loop){

            System.out.print(OPCOES);

            int opc = recebeOpcao(sc, 10);
            if (opc == 0)
                continue;

            switch (opc) {

                case 1: // Cadastrar paciente no sistema
                    System.out.print(OPCAOADD);

                    System.out.print("Nome: ");
                    String nome = sc.nextLine(); // nome

                    System.out.print("\nCPF: ");
                    String cpf = sc.nextLine(); // cpf

                    System.out.print("\nNúmero do SUS: ");
                    String numSUS = sc.nextLine(); // número cartão sus

                    System.out.print("\nIdade: ");
                    String idade = sc.nextLine(); // idade

                    System.out.print("\nEndereço (linha única): ");
                    String endereco = sc.nextLine(); // endereço

                    System.out.print("\nEndereço de email: ");
                    String email = sc.nextLine(); // email

                    System.out.print("\nTelefone: ");
                    String telefone = sc.nextLine(); // telefone

                    System.out.print("\nProfissão: ");
                    String profissao = sc.nextLine(); // profissão

                    System.out.print("\nComorbidades (para mais de uma, separar elas com ','): ");
                    Set<String> comorbidades = new HashSet<String>(Arrays.asList(sc.nextLine().split(","))); // comorbidades

                    boolean adicionado = controllerPacientes.adicionaPessoa(nome, idade, cpf, endereco, numSUS, email, telefone, profissao, comorbidades);
                    if (adicionado) {
                        System.out.println("\nPaciente adicionado ao sistema.");
                    } else {
                        System.out.println("\nO paciente não pode ser adicionado ao sistema, verifique as informações inseridas e tente novamente.");
                    }
                    break;

                case 2: // Remover paciente do sistema
                    System.out.print(OPCAOREMOVE);
                    int opc2 = recebeOpcao(sc, 3);
                    if (opc2 == 0)
                        continue;

                    boolean removeu = false;
                    if (opc2 == 1) {
                        System.out.print("\nInsira o nome do paciente a ser removido: ");
                        String nome2 = sc.nextLine();
                        removeu = controllerPacientes.removePessoaNome(nome2);
                    } else if (opc2 == 2) {
                        System.out.print("\nInsira o CPF do paciente a ser removido: ");
                        String cpf2 = sc.nextLine();
                        removeu = controllerPacientes.removePessoaCPF(cpf2);
                    } else if (opc2 == 3) {
                        System.out.print("\nInsira o número do SUS do paciente a ser removido: ");
                        String numSUS2 = sc.nextLine();
                        removeu = controllerPacientes.removePessoaSUS(numSUS2);
                    }

                    if (removeu) {
                        System.out.println("\nPessoa removida.");
                    } else {
                        System.out.println("\nNão foi possível remover este paciente, verifique as informações inseridas e tente novamente.");
                    }
                    break;

                case 3: // Visualizar todos os pacientes do sistema
                    System.out.print(OPCAOLISTALL);
                    System.out.println(controllerPacientes.getAllPessoas());

                    break;

                case 4: // Visualizar dados de um paciente do sistema
                    System.out.print(OPCAOLIST);
                    int opc4 = recebeOpcao(sc, 3);
                    if (opc4 == 0)
                        continue;

                    Pessoa paciente = null;
                    if (opc4 == 1) {
                        System.out.print("\nInsira o nome do paciente a ser exibido: ");
                        String nome4 = sc.nextLine();
                        paciente = controllerPacientes.getPessoaNome(nome4);
                    } else if (opc4 == 2) {
                        System.out.print("\nInsira o CPF do paciente a ser exibido: ");
                        String cpf4 = sc.nextLine();
                        paciente = controllerPacientes.getPessoaCPF(cpf4);
                    } else if (opc4 == 3) {
                        System.out.print("\nInsira o número do SUS do paciente a ser exibido: ");
                        String numSUS4 = sc.nextLine();
                        paciente = controllerPacientes.getPessoaSUS(numSUS4);
                    }

                    if (paciente != null) {
                        System.out.println("\n" + paciente.toString());
                        System.out.println("\nPaciente listado.");
                    } else {
                        System.out.println("\nNão foi possível listar o paciente, verifique as informações inseridas e tente novamente.");
                    }
                    break;

                case 5: // Atualizar dados de um paciente sistema
                    System.out.print(OPCAOATT);
                    int opc5 = recebeOpcao(sc, 3);
                    if (opc5 == 0)
                        continue;

                    Pessoa pessoa = null;
                    if (opc5 == 1) {
                        System.out.print("\nInsira o nome do paciente a ser atualizado: ");
                        String nome5 = sc.nextLine();
                        pessoa = controllerPacientes.getPessoaNome(nome5);
                    } else if (opc5 == 2) {
                        System.out.print("\nInsira o CPF do paciente a ser atualizado: ");
                        String cpf5 = sc.nextLine();
                        pessoa = controllerPacientes.getPessoaCPF(cpf5);
                    } else if (opc5 == 3) {
                        System.out.print("\nInsira o número do SUS do paciente a ser atualizado: ");
                        String numSUS5 = sc.nextLine();
                        pessoa = controllerPacientes.getPessoaSUS(numSUS5);
                    }

                    if (pessoa == null) {
                        System.out.println("\nNão foi possível encontrar o paciente, verifique as informações inseridas e tente novamente.");
                        continue;
                    }

                    System.out.print(OPCAOATTOP);
                    String opcatt = recebeOpcaoAtt(sc);
                    if (opcatt == null)
                        continue;

                    System.out.print("\nInsira o\\a novo " + opcatt + " a ser atualizado: ");
                    String newValor = sc.nextLine();
                    Set<String> nvs = new HashSet<>();
                    boolean att = true;

                    if (opcatt.equals("idade")) {
                        att = controllerPacientes.atualizaPessoaIdade(pessoa, newValor);
                    } else if (opcatt.equals("comorbidades")) {
                        nvs = new HashSet<>(Arrays.asList(newValor.split(",")));
                        controllerPacientes.atualizaPessoaComorbidadaes(pessoa, nvs);
                    } else if (opcatt.equals("nome")) {
                        att = controllerPacientes.atualizaPessoaNome(pessoa, newValor);
                    } else if (opcatt.equals("cpf")) {
                        att =controllerPacientes.atualizaPessoaCPF(pessoa, newValor);
                    } else if (opcatt.equals("numsus")) {
                        att = controllerPacientes.atualizaPessoaNumSUS(pessoa, newValor);
                    } else if (opcatt.equals("endereco")) {
                        controllerPacientes.atualizaPessoaEndereco(pessoa, newValor);
                    } else if (opcatt.equals("email")) {
                        controllerPacientes.atualizaPessoaEmail(pessoa, newValor);
                    } else if (opcatt.equals("telefone")) {
                        controllerPacientes.atualizaPessoaTelefone(pessoa, newValor);
                    } else if (opcatt.equals("profissao")) {
                        controllerPacientes.atualizaPessoaProfissao(pessoa, newValor);
                    }

                    if (att) {
                        System.out.println("\nPessoa atualizada.");
                    } else {
                        System.out.println("\nNão foi possível atualizar o paciente, verifique as informações inseridas e tente novamente.");
                    }
                    break;

                case 6: // Visualizar informações sobre profissões, faixas etárias e comorbidades habilitadas pelo governo
                    System.out.print(OPCAOLISTINFO);
                    System.out.println(controllerPacientes.getInfoGoverno());
                    System.out.println("Informações listadas.");
                    break;

                case 7: // Atualizar informações sobre profissões, faixas etárias e comorbidades habilitadas
                    System.out.print(OPCAOATTINFO);
                    int opc7 = recebeOpcao(sc, 3);
                    if (opc7 == 0)
                        continue;

                    System.out.print(OPCAOATTINFOAR);
                    int opc71 = recebeOpcao(sc, 2);
                    if (opc71 == 0)
                        continue;

                    boolean att7 = false;
                    if (opc7 == 1) {
                        System.out.print("\nInsira a profissão a ser adicionada\\removida: ");
                        String profissao7 = sc.nextLine();
                        if (opc71 == 1) { // adicionar
                            att7 = controllerPacientes.alteracaoGovernoProfissaoAdd(profissao7);
                        } else if (opc71 == 2) { // remover
                            att7 = controllerPacientes.alteracaoGovernoProfissaoRm(profissao7);
                        }
                    } else if (opc7 == 2) {
                        System.out.print("\nInsira a idade a ser adicionada\\removida: ");
                        String idade7 = sc.nextLine();
                        if (opc71 == 1) { // adicionar
                            att7 = controllerPacientes.alteracaoGovernoIdadeAdd(idade7);
                        } else if (opc71 == 2) { // remover
                            att7 = controllerPacientes.alteracaoGovernoIdadeRm(idade7);
                        }
                    } else if (opc7 == 3) {
                        System.out.print("\nInsira a comorbidade a ser adicionada\\removida: ");
                        String comorbidade7 = sc.nextLine();
                        if (opc71 == 1) { // adicionar
                            att7 = controllerPacientes.alteracaoGovernoComorbidadeAdd(comorbidade7);
                        } else if (opc71 == 2) { // remover
                            att7 = controllerPacientes.alteracaoGovernoComorbidadeRm(comorbidade7);
                        }
                    }

                    if (att7) {
                        System.out.println("\nInformações alteradas.");
                    } else {
                        System.out.println("\nNão foi possível atualizar as informações sobre profissão, faixa etária e comorbidades, verifique as informações inseridas e tente novamente.");
                    }
                    break;

                case 8: // Atualizar um paciente que tomou a vacina
                    System.out.print(OPCAOTV);
                    int opc8 = recebeOpcao(sc, 3);
                    if (opc8 == 0)
                        continue;

                    Pessoa paciente8 = null;
                    if (opc8 == 1) {
                        System.out.print("\nInsira o nome do paciente a tomar a vacina: ");
                        String nome8 = sc.nextLine();
                        paciente8 = controllerPacientes.getPessoaNome(nome8);
                    } else if (opc8 == 2) {
                        System.out.print("\nInsira o CPF do paciente a tomar a vacina: ");
                        String cpf8 = sc.nextLine();
                        paciente8 = controllerPacientes.getPessoaCPF(cpf8);
                    } else if (opc8 == 3) {
                        System.out.print("\nInsira o número do SUS do paciente a tomar a vacina: ");
                        String numSUS8 = sc.nextLine();
                        paciente8 = controllerPacientes.getPessoaSUS(numSUS8);
                    }

                    if (paciente8 != null) {
                        boolean tomou = controllerPacientes.tomarVacina(paciente8);
                        if (tomou) {
                            System.out.println("\nVacinação completa.");
                        } else {
                            System.out.println("\nPaciente não pode tomar vacina se não estiver habilitado.");
                        }
                    } else {
                        System.out.println("\nNão foi possível encontrar o paciente, verifique as informações inseridas e tente novamente.");
                    }
                    break;

                case 9: // Atualizar um paciente depois de 20 dias desde a primeira dose
                    System.out.print(OPCAOATT);
                    int opc9 = recebeOpcao(sc, 3);
                    if (opc9 == 0)
                        continue;

                    Pessoa paciente9 = null;
                    if (opc9 == 1) {
                        System.out.print("\nInsira o nome do paciente a ser atualizado após 20 dias: ");
                        String nome8 = sc.nextLine();
                        paciente9 = controllerPacientes.getPessoaNome(nome8);
                    } else if (opc9 == 2) {
                        System.out.print("\nInsira o CPF do paciente a ser atualizado após 20 dias: ");
                        String cpf8 = sc.nextLine();
                        paciente9 = controllerPacientes.getPessoaCPF(cpf8);
                    } else if (opc9 == 3) {
                        System.out.print("\nInsira o número do SUS do paciente a ser atualizado após 20 dias: ");
                        String numSUS8 = sc.nextLine();
                        paciente9 = controllerPacientes.getPessoaSUS(numSUS8);
                    }

                    if (paciente9 != null) {
                        boolean passou = controllerPacientes.atualiza20dias(paciente9);
                        if (passou) {
                            System.out.println("\nAtualização completa.");
                        } else {
                            System.out.println("\nPaciente não pode passar a ser habilitado se não tiver tomado a primera dose.");
                        }
                    } else {
                        System.out.println("\nNão foi possível encontrar o paciente, verifique as informações inseridas e tente novamente.");
                    }
                    break;

                case 10: // Sair
                    loop = false;
                    System.out.println("\n\nObrigado por usar o Sistema de Vacinação para a COVID-19.");
                    break;

                default: // Sair
                    loop = false;
                    break;
            } // fim switch
        } // fim while

        sc.close();
    }

    public int recebeOpcao(Scanner sc, int limite) {
        String entrada = sc.nextLine();
        int opc = 0;
        Boolean isInt = true;
        try {
            opc = Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            isInt = false;
        }
        if (!isInt || opc < 1 || opc > limite) {
            System.out.println("\n" + OPCAOINVALIDA);
            opc = 0;
        }
        return opc;
    }

    public String recebeOpcaoAtt(Scanner sc) {
        int opcao = recebeOpcao(sc,9);
        if (opcao == 0)
            return null;

        if (opcao == 1)
            return "nome";
        else if (opcao == 2)
            return "cpf";
        else if (opcao == 3)
            return "numsus";
        else if (opcao == 4)
            return "idade";
        else if (opcao == 5)
            return "endereco";
        else if (opcao == 6)
            return "email";
        else if (opcao == 7)
            return "telefone";
        else if (opcao == 8)
            return "profissao";
        else if (opcao == 9)
            return "comorbidades";
        return null;
    }
}