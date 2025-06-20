import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RedDePetri redDePetri = new RedDePetri();

        while (true){
            boolean[] sensibilizadas = redDePetri.transicionesSensibilizadas();
            for (int i = 0; i < redDePetri.getTransiciones().size(); i++) {
                System.out.println("T" + i + ": " + sensibilizadas[i]);
            }

            System.out.print("\nTransicion a disparar: ");
            int transicion = scanner.nextInt();
            System.out.print("\n");

            if(transicion == 15){
                break;
            }

            redDePetri.dispararTransicion(transicion);
        }
    }
}