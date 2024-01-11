import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class RegistroDocumental {
		ArrayList<Pessoa> pessoas;
	    ArrayList<Documento> documentos;
	    private Map<Documento, ArrayList<Pessoa>> associacoes;


	    public RegistroDocumental() {
	        this.pessoas = new ArrayList<>();
	        this.documentos = new ArrayList<>();
	        this.associacoes = new HashMap<>();
	    }

	    public void adicionarPessoa(String nome, String email, String cpf, String telefone) {
			Pessoa pessoa = new Pessoa(nome, cpf, email, telefone);
	        pessoas.add(pessoa);
	        System.out.println("Pessoa adicionada: \n" + pessoa);
	    }
	    
	    private Pessoa encontrarPessoaPorNome(String nome) {
	        for (Pessoa pessoa : pessoas) {
	            if (pessoa.getNome().equals(nome)) {
	                return pessoa;
	            }
	        }
	        return null;
	    }

	    public void listarPessoas() {
	        System.out.println("Pessoas:");
	        for (Pessoa pessoa : pessoas) {
	            System.out.println(pessoa);
	        }
	    }
	    public void excluirPessoa(String nome) {
	        Pessoa pessoaParaExcluir = encontrarPessoaPorNome(nome);

	        if (pessoaParaExcluir != null) {
	           
	            pessoas.remove(pessoaParaExcluir);

	            for (Map.Entry<Documento, ArrayList<Pessoa>> entry : associacoes.entrySet()) {
	                ArrayList<Pessoa> pessoasAssociadas = entry.getValue();
	                pessoasAssociadas.remove(pessoaParaExcluir);
	            }

	            System.out.println("Pessoa excluída: " + pessoaParaExcluir);
	        } else {
	            System.out.println("Pessoa não encontrada.");
	        }
	    }

	    public void adicionarDocumento(String idDoc, String descricao, String url) {
	        Documento documento = new Documento(idDoc, descricao, url);
	        documentos.add(documento);
	        associacoes.put(documento, new ArrayList<>());
	        System.out.println("Documento adicionado: " + documento);
	    }

	    public void listarDocumentos() {
	        System.out.println("Documentos:");
	        for (Documento documento : documentos) {
	            System.out.println(documento);
	        }
	    }

	    public void associarDocumentoPessoa(Documento documento, Pessoa pessoa) {
	        if (documentos.contains(documento) && pessoas.contains(pessoa)) {
	            associacoes.get(documento).add(pessoa);
	            System.out.println("Associação registrada: " + pessoa.getNome() + " - " + documento.getUrl());
	        } else {
	            System.out.println("Documento ou pessoa não encontrada.");
	        }
	    }

	    public void listarAssociacoes() {
	        System.out.println("Associações:");
	        for (Map.Entry<Documento, ArrayList<Pessoa>> entry : associacoes.entrySet()) {
	            Documento documento = entry.getKey();
	            ArrayList<Pessoa> pessoasAssociadas = entry.getValue();
	            System.out.println("id do documento associado: " + documento.getidDoc()  + pessoasAssociadas);
	        }
	    }
	    

	    public void alterarDocumento(Documento documento, String novaDescricao, String novaUrl,  String nomeResponsavel) {
	        if (documentos.contains(documento)) {
	            documento.atualizarVersao(novaDescricao);
	            documento.atualizarVersao(novaUrl);
	            documento.atualizarVersao(nomeResponsavel);
	            System.out.println("Documento alterado: " + documento);
	        } else {
	            System.out.println("Documento não encontrado.");
	        }
	    }

}
