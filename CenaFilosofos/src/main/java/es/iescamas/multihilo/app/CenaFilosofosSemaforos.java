package es.iescamas.multihilo.app;

import es.iescamas.multihilo.monitor.hilo.FilosofoConSemaforos;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public final class CenaFilosofosSemaforos {
    
    private CenaFilosofosSemaforos() {}

    public static void main(String[] args) {
        final int N = 5;
        System.out.println("=== CENA CON SEMÁFOROS (Solución Portero) ===");

        final Semaphore[] tenedores = new Semaphore[N];
        for (int i = 0; i < N; i++) {
            tenedores[i] = new Semaphore(1, true); 
        }

        final Semaphore portero = new Semaphore(N - 1, true);

        Thread[] hilos = new Thread[N];
        for (int i = 0; i < N; i++) {
            FilosofoConSemaforos f = new FilosofoConSemaforos(i, tenedores, portero);
            hilos[i] = new Thread(f, "FilosofoSem-" + i);
            hilos[i].start();
        }

        try (Scanner sc = new Scanner(System.in)) {
            sc.nextLine();
        }
        for (Thread t : hilos) t.interrupt();
    }
}