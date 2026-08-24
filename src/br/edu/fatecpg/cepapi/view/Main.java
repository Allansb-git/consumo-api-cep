import br.edu.fatecpg.cepapi.model.Endereco;
import br.edu.fatecpg.cepapi.service.ServiceApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

    // Adicionado 'throws Exception' para delegar o tratamento de exceções do ServiceApi
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        ServiceApi serviceApi = new ServiceApi();
        List<Endereco> historico = new ArrayList<>();

        String opcao = "-1";

        while (!opcao.equals("0")) {
            System.out.println("\n=== CONSULTA VIA CEP ===");
            System.out.println("1 - Consultar CEP");
            System.out.println("2 - Ver CEPs Consultados");
            System.out.println("3 - Limpar Histórico de Consulta");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    System.out.print("Digite o CEP para consulta: ");
                    String cepInput = scanner.nextLine();

                    Endereco endereco = ServiceApi.buscarEndereco(cepInput);
                    historico.add(endereco);

                    System.out.println("\n--- Endereço Encontrado ---");
                    System.out.println(endereco);
                    break;

                case "2":
                    System.out.println("\n--- Histórico de Consultas ---");
                    if (historico.isEmpty()) {
                        System.out.println("Nenhum CEP consultado até o momento.");
                    } else {
                        for (int i = 0; i < historico.size(); i++) {
                            System.out.printf("%d. %s\n", (i + 1), historico.get(i));
                        }
                    }
                    break;

                case "3":
                    if (historico.isEmpty()) {
                        System.out.println("O histórico já está vazio.");
                    } else {
                        historico.clear();
                        System.out.println("Histórico de consultas limpo com sucesso!");
                    }
                    break;

                case "0":
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }

        scanner.close();
    }