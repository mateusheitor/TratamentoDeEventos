package atv10;

public class Soma {
    public static void main(String[] args) {
        double[] vet = {0.25, 1.68, 0.85, 0.010, 5.55};
        double sum = 0;

        for (double e : vet) {
            sum += e;
        }

        System.out.println(sum);
    }
}

