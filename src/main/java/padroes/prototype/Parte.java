package padroes.prototype;

public class Parte {

    private String nome;
    private String documento;

    public Parte(String nome, String documento) {
        this.nome = nome;
        this.documento = documento;
    }

    public Parte(Parte original) {
        this(original.nome, original.documento);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}
