import java.util.ArrayList;
import java.util.Scanner;
import java.text.SimpleDateFormat;


public class Principal {
	 private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	public static void main(String[] args) {
        RegistroDocumental registro = new RegistroDocumental();
        Scanner scanner = new Scanner(System.in);
       
        while (true) {
        	System.out.println("-------------------------------");
            System.out.println("Menu:");
            System.out.println("(1) Gerenciar Pessoas");
            System.out.println("(2) Gerenciar Documentos");
            System.out.println("(3) Sair");
            System.out.println("-------------------------------");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    gerenciarPessoas(registro, scanner);
                    break;
                case 2:
                    gerenciarDocumentos(registro, scanner);
                    break;
                case 3:
                    System.out.println("Saindo do programa.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void gerenciarPessoas(RegistroDocumental registro, Scanner scanner) {
        while (true) {
        	System.out.println("-------------------------------");
            System.out.println("Gerenciar Pessoas:");
            System.out.println("(1) Adicionar Pessoa");
            System.out.println("(2) Listar Pessoas");
            System.out.println("(3) Excluir Pessoas");
            System.out.println("(4) Voltar");
            System.out.println("-------------------------------");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome da pessoa: ");      
                    scanner.nextLine();
                    String nome = scanner.nextLine();                 
                    System.out.print("Digite o email: ");                    
                    String email = scanner.nextLine();                  
                    System.out.print("Digite o CPF: ");                  
                    String cpf = scanner.nextLine();
                    System.out.print("Digite o telefone: ");
                    String telefone = scanner.nextLine();
                    registro.adicionarPessoa(nome, email, cpf, telefone);
                    break;
                case 2:
                    registro.listarPessoas();
                    break;
                case 3:
                	System.out.print("Digite o nome da pessoa a ser excluída: ");
                    scanner.nextLine();
               	    String nomeExcluir = scanner.nextLine();
               	    registro.excluirPessoa(nomeExcluir);                        	
                	break;
                case 4:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void gerenciarDocumentos(RegistroDocumental registro, Scanner scanner) {
        while (true) {
        	System.out.println("-------------------------------");
            System.out.println("Gerenciar Documentos:");
            System.out.println("(1) Adicionar Documento");
            System.out.println("(2) Listar Documentos");
            System.out.println("(3) Associar Documento a Pessoa");
            System.out.println("(4) Listar Associações");
            System.out.println("(5) Alterar Documento");
            System.out.println("(6) Listar Versões do Documento");
            System.out.println("(7) Voltar");
            System.out.println("-------------------------------");

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o ID do documento: ");
                    scanner.nextLine();
                    String idDoc = scanner.nextLine();
                    System.out.print("Digite a descricao do documento: ");
                    String descricao = scanner.nextLine();
                    System.out.print("Digite a URL do documento: ");
                    String url = scanner.nextLine();
                    registro.adicionarDocumento(idDoc, descricao, url);
                    break;
                case 2:
                    registro.listarDocumentos();
                    break;
                case 3:
                    System.out.print("Digite o ID do documento: ");
                    scanner.nextLine();
                    String idAssoc = scanner.nextLine();
                    System.out.print("Digite o nome da pessoa: ");
                    String nomeAssoc = scanner.nextLine();
                    Documento docAssoc = encontrarDocumentoPorID(registro, idAssoc);
                    Pessoa pessoaAssoc = encontrarPessoaPorNome(registro, nomeAssoc);
                    if (docAssoc != null && pessoaAssoc != null) {
                        registro.associarDocumentoPessoa(docAssoc, pessoaAssoc);
                    } else {
                        System.out.println("Documento ou pessoa não encontrada.");
                    }
                    break;
                case 4:
                    registro.listarAssociacoes();
                    break;
                case 5:
                	 System.out.print("Digite o ID do documento a ser alterado: ");
                     scanner.nextLine();
                     String idAlterar = scanner.nextLine();
                     Documento docAlterar = encontrarDocumentoPorID(registro, idAlterar);
                     if (docAlterar != null) {
                    	 System.out.print("Digite a nova descrição do documento: ");
                    	 String novaDescricao = scanner.nextLine();
                         System.out.print("Digite a nova URL do documento: ");
                         String novaURL = scanner.nextLine();
                         System.out.print("Digite o nome do responsável pela alteração: ");
                         String nomeResponsavel = scanner.nextLine();
                         registro.alterarDocumento(docAlterar, nomeResponsavel, novaURL, novaDescricao);
                     } else {
                         System.out.println("Documento não encontrado.");
                     }
                    break;
                case 6:
                	 System.out.print("Digite o ID do documento: ");
                     scanner.nextLine(); 
                     String tituloVersoes = scanner.nextLine();
                     Documento docVersoes = encontrarDocumentoPorID(registro, tituloVersoes);
                     if (docVersoes != null) {
                         listarVersoesDocumento(docVersoes);
                     } else {
                         System.out.println("Documento não encontrado.");
                     }
                     break;
                case 7:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static Documento encontrarDocumentoPorID(RegistroDocumental registro, String idDoc) {
        for (Documento documento : registro.documentos) {
            if (documento.getidDoc().equals(idDoc)) {
                return documento;
            }
        }
        return null;
    }
    
    private static void listarVersoesDocumento(Documento documento) {
        System.out.println("Versões do Documento - " + documento.getidDoc());
        ArrayList<VersaoDocumento> versoes = documento.getVersoes();
        for (VersaoDocumento versao : versoes) {
            System.out.println("Data: " + dateFormat.format(versao.getDataAlteracao()) +
                    " | Responsável: " + versao.getNomeResponsavel());
        }
    }
    
    private static Pessoa encontrarPessoaPorNome(RegistroDocumental registro, String nome) {
        for (Pessoa pessoa : registro.pessoas) {
            if (pessoa.getNome().equals(nome)) {
                return pessoa;
            }
        }
        return null;
    }
}
                   

