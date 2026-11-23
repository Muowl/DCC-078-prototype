package padroes.prototype;

import java.util.ArrayList;
import java.util.List;

public class Procuracao implements ModeloDocumento {

    private String outorgante;
    private final List<String> poderes = new ArrayList<>();

    public Procuracao(String outorgante) {
        this.outorgante = outorgante;
    }

    private Procuracao(Procuracao original) {
        this.outorgante = original.outorgante;
        this.poderes.addAll(original.poderes);
    }

    public void setOutorgante(String outorgante) {
        this.outorgante = outorgante;
    }

    public void adicionarPoder(String poder) {
        poderes.add(poder);
    }

    public List<String> getPoderes() {
        return poderes;
    }

    @Override
    public Procuracao clonar() {
        return new Procuracao(this);
    }

    @Override
    public String resumo() {
        return "Procuração de " + outorgante + " [poderes: " + poderes.size() + "]";
    }
}
