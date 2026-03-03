package projeto.cadastro_usuario;

import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();

        int opcao = -1;

        while (opcao != 6) {
            System.out.println("-=========- MENU -===========-");
            System.out.println("1 - Cadastrar Usuário");
            System.out.println("2 - Listar Usuarios");
            System.out.println("3 - Buscar Usuário Por Cpf");
            System.out.println("4 - Atualizar Usuario");
            System.out.println("5 - Remover Usuário");
            System.out.println("6 - Sair");
            System.out.println("Escolha uma opcao: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    cadastrarUsuario(scanner, listaUsuarios);
                    break;

                case 2:
                    listarUsuario(listaUsuarios);
                    break;

                case 3:
                    buscarUsuarioPorCpf(scanner, listaUsuarios);
                    break;
                case 4:
                    atualizarUsuario(scanner, listaUsuarios);
                    break;
                case 5:
                    removerUsuario(scanner, listaUsuarios);
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida, Tente novamente.");
            }
        }
    }

    public static void cadastrarUsuario(Scanner scanner, ArrayList<Usuario> listaUsuarios) {

        Usuario usuario = new Usuario();

        System.out.println("Digite seu nome:");
        usuario.setNome(scanner.nextLine());

        System.out.println("Digite sua idade:");
        int idade = scanner.nextInt();
        scanner.nextLine();

        while (idade < 0) {
            System.out.println("Idade inválida! Digite novamente:");
            idade = scanner.nextInt();
            scanner.nextLine();
        }

        usuario.setIdade(idade);

        String cpf;

        while (true) {
            System.out.println("Digite seu CPF: ");
            cpf = scanner.nextLine();

            boolean cpfExiste = false;

            for (Usuario usuario1 : listaUsuarios) {
                if (cpf.equals(usuario1.getCpf())) {
                    cpfExiste = true;
                    break;
                }
            }

            if (cpfExiste) {
                System.out.println("CPF já cadastrado! Digite outro.");
            } else {
                break;
            }
        }

        usuario.setCpf(cpf);

        System.out.println("Digite sua data de nascimento:");
        usuario.setDataDeNascimento(scanner.nextLine());

        System.out.println("Digite seu email:");
        String email = scanner.nextLine();

        while (!email.contains("@") || !email.contains(".")) {
            System.out.println("Email inválido! Digite novamente:");
            email = scanner.nextLine();
        }
        usuario.setEmail(email);

        System.out.println("Digite sua senha:");
        String senha = scanner.nextLine();

        while (senha.length() < 6) {
            System.out.println("Senha muito curta! Digite pelo menos 6 caracteres:");
            senha = scanner.nextLine();
        }

        usuario.setSenha(senha);

        listaUsuarios.add(usuario);

        System.out.println("Cadastro realizado com sucesso!");

        System.out.println("Pressione ENTER para continuar...");
        scanner.nextLine();

    }

    public static void listarUsuario(ArrayList<Usuario> listaUsuarios) {
        if (listaUsuarios.isEmpty()) {

            System.out.println("Nenhum cadastro realizado!");
        } else {
            System.out.println("-========= Lista De Usuários =========-");

            for (Usuario usuario : listaUsuarios) {
                System.out.println("---------------------------");
                System.out.println("Nome: " + usuario.getNome());
                System.out.println("Idade: " + usuario.getIdade());
                System.out.println("Cpf: " + usuario.getCpf());
                System.out.println("Data de Nascimento: " + usuario.getDataDeNascimento());
                System.out.println("Email: " + usuario.getEmail());
            }

            System.out.println("---------------------------");
        }
    }
    public static Usuario buscarPorCpf(String cpf, ArrayList<Usuario> listaUsuarios) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getCpf().equals(cpf)) {
                return usuario;
            }
        }
        return null;
    }

    public static void buscarUsuarioPorCpf(Scanner scanner, ArrayList<Usuario> listaUsuarios) {
        System.out.println("Digite um CPF: ");
        String buscarCpf = scanner.nextLine();

        Usuario usuario = buscarPorCpf(buscarCpf, listaUsuarios);

        if (usuario != null) {
            System.out.println("-======== USUÁRIO ENCONTRADO ========-");
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Idade: " + usuario.getIdade());
            System.out.println("CPF: " + usuario.getCpf());
            System.out.println("Data de Nascimento: " + usuario.getDataDeNascimento());
            System.out.println("Email: " + usuario.getEmail());
        } else {
            System.out.println("Usuário não encontrado!");
        }
    }
    public static String lerEmailValido(Scanner scanner) {
        String email;

        while (true) {
            System.out.print("Digite o novo email: ");
            email = scanner.nextLine();

            if (email.contains("@") || email.contains(".")) {
                break;
            } else {
                System.out.println("Email inválido! Tente novamente.");
            }
        }

        return email;
    }
    public static String lerSenhaValida(Scanner scanner) {
        String senha;

        while (true) {
            System.out.print("Digite a nova senha: ");
            senha = scanner.nextLine();

            if (senha.length() >= 6) {
                break;
            } else {
                System.out.println("Senha deve ter no mínimo 6 caracteres.");
            }
        }

        return senha;
    }
    public static void atualizarUsuario (Scanner scanner, ArrayList<Usuario> listaUsuarios) {
        System.out.print("Digite o CPF do usuário: ");
        String cpfAtual = scanner.nextLine();

        Usuario usuarioEncontrado = null;
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getCpf().equals(cpfAtual)) {
                usuarioEncontrado = usuario;
                break;
            }
        }

        if (usuarioEncontrado == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        System.out.println("O que deseja alterar?");
        System.out.println("1 - Email");
        System.out.println("2 - Senha");
        System.out.println("3 - Email e Senha");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {

            case 1:
                usuarioEncontrado.setEmail(lerEmailValido(scanner));
                System.out.println("Email atualizado com sucesso!");
                break;

            case 2:
                usuarioEncontrado.setSenha(lerSenhaValida(scanner));
                System.out.println("Senha atualizada com sucesso!");
                break;

            case 3:
                usuarioEncontrado.setEmail(lerEmailValido(scanner));
                usuarioEncontrado.setSenha(lerSenhaValida(scanner));
                System.out.println("Dados atualizados com sucesso!");
                break;

            default:
                System.out.println("Opção inválida.");
        }
    }
    public static void removerUsuario(Scanner scanner, ArrayList<Usuario> listaUsuarios) {

        System.out.println("Digite o CPF do usuário que deseja remover:");
        String cpfRemover = scanner.nextLine().trim();

        Usuario usuarioEncontrado = null;
        System.out.println("Total de usuários: " + listaUsuarios.size());
        for (Usuario u : listaUsuarios) {
            if (u.getCpf().trim().equals(cpfRemover)) {
                usuarioEncontrado = u;
                break;
            }
        }
        if (usuarioEncontrado != null) {
            listaUsuarios.remove(usuarioEncontrado);
            System.out.println("Usuário removido com sucesso!");
        } else {
            System.out.println("Usuário não encontrado.");
            }
        }
    }