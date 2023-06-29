package atv3;

public class Deposito {
    private int items = 0;
    private final int capacidade = 10;

    public synchronized int retirar() {
        if (items > 0) {
            items--;
            System.out.println("Caixa retirada: Sobram " + items + " caixas");
            notifyAll();
            return 1;
        }
        return 0;
    }

    public synchronized int colocar() {
        if (items < capacidade) {
            items++;
            System.out.println("Caixa armazenada: Passaram a ser " + items + " caixas");
            notifyAll();
            return 1;
        }
        return 0;
    }

    public synchronized boolean retirar_timed_wait(long milliseconds) throws InterruptedException {
        long endTime = System.currentTimeMillis() + milliseconds;
        long remainingTime = milliseconds;
        while (items == 0 && remainingTime > 0) {
            wait(remainingTime);
            remainingTime = endTime - System.currentTimeMillis();
        }
        if (items > 0) {
            items--;
            System.out.println("Caixa retirada: Sobram " + items + " caixas");
            notifyAll();
            return true;
        }
        return false;
    }

    public synchronized void retirar_guarded_suspension() throws InterruptedException {
        while (items == 0) {
            wait();
        }
        items--;
        System.out.println("Caixa retirada: Sobram " + items + " caixas");
        notifyAll();
    }
}
