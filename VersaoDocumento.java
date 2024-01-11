import java.util.Date;

class VersaoDocumento {
    private Date dataAlteracao;
    private String nomeResponsavel;

    public VersaoDocumento(String nomeResponsavel) {
        this.dataAlteracao = new Date();
        this.nomeResponsavel = nomeResponsavel;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    @Override
    public String toString() {
        return "VersaoDocumento" +
                "dataAlteracao=" + dataAlteracao +
                "nomeResponsavel=" + nomeResponsavel;
                
    }
}
