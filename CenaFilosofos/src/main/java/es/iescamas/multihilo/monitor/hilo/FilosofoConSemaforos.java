package es.iescamas.multihilo.monitor.hilo;

import java.util.Random;
import java.util.concurrent.Semaphore;

public final class FilosofoConSemaforos implements Runnable {

    private final int id;
    private final Semaphore[] tenedores;
    private final Semaphore portero;
    private final Random random = new Random();

    public FilosofoConSemaforos(final int id, final Semaphore[] tenedores, final Semaphore portero) {
        this.id = id;
        this.tenedores = tenedores;
        this.portero = portero;
    }

    private int izquierda() { return id; }
    private int derecha() { return (id + 1) % tenedores.length; }

    private void pensar() throws InterruptedException {
        System.out.println("Filósofo Semáforo " + id + " 🧠 PENSANDO...");
        Thread.sleep(random.nextInt(1000) + 500L);
    }

    private void comer() throws InterruptedException {
        System.out.println("Filósofo Semáforo " + id + " 🍝 COMIENDO");
        Thread.sleep(random.nextInt(1000) + 500L);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                pensar();

                // 1. EL PORTERO: Limita el aforo a N-1 para evitar deadlock
                portero.acquire();
                try {
                    // 2. Tomar tenedores (Semáforos)
                    tenedores[izquierda()].acquire();
                    tenedores[derecha()].acquire();
                    try {
                        comer();
                    } finally {
                        // 3. Soltar tenedores
                        tenedores[derecha()].release();
                        tenedores[izquierda()].release();
                    }
                } finally {
                    // 4. Salir (Liberar al portero)
                    portero.release();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}