package atv3;

public class Produtor implements Runnable {
    private Deposito deposito;
    private int tempoEspera;

    public Produtor(Deposito deposito, int tempoEspera) {
        this.deposito = deposito;
        this.tempoEspera = tempoEspera;
    }

    @Override
    public void run() {
        while (true) {
            try {
                deposito.colocar();
                Thread.sleep(tempoEspera * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
