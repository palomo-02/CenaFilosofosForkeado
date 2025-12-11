package es.iescamas.multihilo.monitor.hilo;

import es.iescamas.multihilo.monitor.MonitorFilosofos;
import java.util.Random;

public final class FilosofoTimeout implements Runnable {

    private final int id;
    private final MonitorFilosofos monitor;
    private final Random random = new Random();

    public FilosofoTimeout(final int id, final MonitorFilosofos monitor) {
        this.id = id;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                
                System.out.println("Filósofo Timeout " + id + " 🧠 PENSANDO...");
                Thread.sleep(random.nextInt(1000) + 100L);

                long inicio = System.currentTimeMillis();
                long timeout = 3000L; 

                
                monitor.tomarTenedores(id);

                if (System.currentTimeMillis() - inicio > timeout) {
                     System.out.println("Filósofo " + id + " se cansó de esperar.");
                     monitor.dejarTenedores(id);
                     continue; 
                }

                System.out.println("Filósofo Timeout " + id + " 🍝 COMIENDO");
                Thread.sleep(random.nextInt(1000) + 500L);
                
                monitor.dejarTenedores(id);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}