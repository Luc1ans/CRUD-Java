import java.util.ArrayList;

     public class Documento {
		private String idDoc;
	    private String descricao;
	    private String url;
	    private ArrayList<VersaoDocumento> versoes;
	    private int versao;
	

	    public Documento(String idDoc, String descricao, String url) {
	        this.idDoc = idDoc;
	        this.descricao = descricao;
	        this.url = url;
	        this.versoes = new ArrayList<>();
	        adicionarVersao("Inicialização do documento");
	        this.versao = 1;
	           }
	    
	    public String getidDoc() {
	        return idDoc;
	    }

	    public String getDescricao() {
	        return descricao;
	    }

	    public String getUrl() {
	        return url;
	    }
	    
	    public void adicionarVersao(String nomeResponsavel) {
	        VersaoDocumento novaVersao = new VersaoDocumento(nomeResponsavel);
	        versoes.add(novaVersao);
	    }
	    
	    public void atualizarVersao(String novaDescricao) {
	        this.descricao = novaDescricao;
	        this.versao++;
	    }
	    
	    public ArrayList<VersaoDocumento> getVersoes() {
	        return versoes;
	    }

	    public String toString() {
	        return "\nID do documento=" + idDoc + 
	                "\nDescrição=" + descricao + 
	                "\nUrl=" + url +
	                "\nversao=" + versao;
	                
	    }
	}

