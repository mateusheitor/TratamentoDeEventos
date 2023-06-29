package atv3;

public class Consumidor extends Thread {
    private Deposito deposito;
    private int tempoEspera;
    private int metodo;

    public Consumidor(Deposito deposito, int tempoEspera, int metodo) {
        this.deposito = deposito;
        this.tempoEspera = tempoEspera;
        this.metodo = metodo;
    }

    @Override
    public void run() {
        switch (metodo) {
            case 1:
                while (true) {
                    try {
                        deposito.retirar();
                        Thread.sleep(tempoEspera * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            case 2:
                while (true) {
                    try {
                        if (!deposito.retirar_timed_wait(3000))
                            System.out.println("Falha ao retirar caixa");
                        Thread.sleep(tempoEspera * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            case 3:
                while (true) {
                    try {
                        deposito.retirar_guarded_suspension();
                        Thread.sleep(tempoEspera * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }
    }
}
