package padroes.prototype;

import java.util.ArrayList;
import java.util.List;

public class Contrato implements ModeloDocumento {

    private String titulo;
    private Parte contratante;
    private final List<String> clausulas = new ArrayList<>();

    public Contrato(String titulo, Parte contratante) {
        this.titulo = titulo;
        this.contratante = contratante;
    }

    private Contrato(Contrato original) {
        this.titulo = original.titulo;
        this.contratante = new Parte(original.contratante);
        this.clausulas.addAll(original.clausulas);
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Parte getContratante() {
        return contratante;
    }

    public void adicionarClausula(String clausula) {
        clausulas.add(clausula);
    }

    public List<String> getClausulas() {
        return clausulas;
    }

    @Override
    public Contrato clonar() {
        return new Contrato(this);
    }

    @Override
    public String resumo() {
        return titulo + " - " + contratante.getNome() + " [cláusulas: " + clausulas.size() + "]";
    }
}
