package lab06.ex01;

import java.util.ArrayList;
import java.util.List;

public class Registos {
    private ArrayList<Empregado> empregados;

    public Registos () {
        empregados = new ArrayList<>();
    }

    public void insere (Empregado emp) {
        if (!isEmpregado(emp.codigo()))
            empregados.add(emp);
    }
    public void remove (int codigo) {
        empregados.removeIf(e -> codigo == e.codigo());
    }

    public boolean isEmpregado (int codigo) {
        for (Empregado e : empregados) {
            if (e.codigo() == codigo){
                return true;
            }
        }
        return false;
    }

    public List<Empregado> listaDeEmpregados() {
        return empregados;
    }

    @Override
    public String toString() {
        return "Registos " + empregados;
    }
}
