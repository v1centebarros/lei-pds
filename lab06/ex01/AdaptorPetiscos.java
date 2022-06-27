package lab06.ex01;

public class AdaptorPetiscos implements Adaptor{
    private Registos registos;

    public AdaptorPetiscos(Registos registos) {
        this.registos = registos;
    }

    public AdaptorPetiscos() {
        this.registos = new Registos();
    }

    @Override
    public void addWorker(Worker worker) {
        registos.insere(new Empregado(worker.name(),worker.surname(), (int) worker.code(),worker.salary()));
    }

    @Override
    public void removeWorker(long code) {
        registos.remove((int) code);
    }

    @Override
    public boolean isWorker(long code) {
        return registos.isEmpregado((int) code);
    }

    @Override
    public void listWorkers() {
        for (Empregado e: registos.listaDeEmpregados()) {
            System.out.println(e);
        }
    }

    @Override
    public String toString() {
        return registos.listaDeEmpregados().toString();
    }
}
