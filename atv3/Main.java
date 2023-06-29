package atv3;

public class Main {
    public static void main(String[] args) {
        Deposito dep = new Deposito();
        Produtor p = new Produtor(dep, 2);
        Consumidor c1 = new Consumidor(dep, 1, 1); // Utiliza o método retirar()
        Consumidor c2 = new Consumidor(dep, 1, 2); // Utiliza o método retirar_timed_wait()
        Consumidor c3 = new Consumidor(dep, 1, 3); // Utiliza o método retirar_guarded_suspension()

        Thread produtorThread = new Thread(p);
        Thread consumidorThread1 = new Thread(c1);
        Thread consumidorThread2 = new Thread(c2);
        Thread consumidorThread3 = new Thread(c3);

        produtorThread.start();
        consumidorThread1.start();
        consumidorThread2.start();
        consumidorThread3.start();

        System.out.println("Execução do main da classe Deposito terminada");
    }
}
