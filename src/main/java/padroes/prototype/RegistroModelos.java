package padroes.prototype;

import java.util.HashMap;
import java.util.Map;

public class RegistroModelos {

    private final Map<String, ModeloDocumento> modelos = new HashMap<>();

    public void registrar(String nome, ModeloDocumento modelo) {
        modelos.put(nome, modelo);
    }

    public ModeloDocumento criar(String nome) {
        ModeloDocumento modelo = modelos.get(nome);
        if (modelo == null) {
            throw new IllegalArgumentException("Modelo não registrado: " + nome);
        }
        return modelo.clonar();
    }
}
