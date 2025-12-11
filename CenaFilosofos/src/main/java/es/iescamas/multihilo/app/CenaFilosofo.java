package es.iescamas.multihilo.app;

import java.util.Scanner;

import es.iescamas.multihilo.monitor.MonitorFilosofos;
import es.iescamas.multihilo.monitor.hilo.Filosofo;
import es.iescamas.multihilo.util.Utilidad;

/**
 * Punto de entrada de la aplicación. Crea N filósofos y un monitor
 * compartido para coordinar el acceso a los recursos (tenedores).
 */
public final class CenaFilosofo {
	/**
	 * N  es el total de filosofos
	 */
	final private static int N = 5;
	
    private CenaFilosofo() {
        // Evitamos instanciación
    }

    public static void main(String[] args) {
       

        final MonitorFilosofos monitor = new MonitorFilosofos(N);
        /**
         * Utilidad
         */
        Thread utilidad = new Thread( new Utilidad(monitor), "Consola-Tabla");
        utilidad.start();

     // Crear y lanzar los hilos de los filósofos
        for (int i = 0; i < N; i++) {
            final Filosofo f = new Filosofo(i, monitor);
            final Thread t = new Thread(f, "Filosofo-" + i);

           
            if (i % 2 == 0) {
                t.setPriority(Thread.MAX_PRIORITY);
            } else {
                t.setPriority(Thread.MIN_PRIORITY);
            }
            // ------------------------------------------

            t.start(); // El hilo pasa de NEW a RUNNABLE
        }
    }
}
