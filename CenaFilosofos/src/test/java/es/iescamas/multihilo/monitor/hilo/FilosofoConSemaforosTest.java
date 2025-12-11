package es.iescamas.multihilo.monitor.hilo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.concurrent.Semaphore;
import static org.junit.jupiter.api.Assertions.*;

class FilosofoConSemaforosTest {

    @Test
    @DisplayName("El portero se inicializa con N-1 permisos")
    void testPorteroPermisos() {
        int N = 5;
        Semaphore portero = new Semaphore(N - 1, true);
        assertEquals(4, portero.availablePermits(), "Debe haber 4 sitios libres");
    }

    @Test
    @DisplayName("Los tenedores son binarios")
    void testTenedorBinario() throws InterruptedException {
        Semaphore tenedor = new Semaphore(1);
        tenedor.acquire();
        assertEquals(0, tenedor.availablePermits(), "Tenedor ocupado vale 0");
        tenedor.release();
        assertEquals(1, tenedor.availablePermits(), "Tenedor libre vale 1");
    }
}