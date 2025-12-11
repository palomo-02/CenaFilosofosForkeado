Este proyecto es una práctica de programación concurrente en Java. Resuelve el clásico problema de los filósofos que quieren comer y pensar sin bloquearse entre ellos.
Simula a 5 filósofos (hilos) sentados en una mesa redonda. Compiten por coger los tenedores (recursos compartidos) de sus lados. Si no se gestiona bien, se quedan bloqueados (deadlock).

He implementado 3 soluciones distintas:
    Monitor Clásico: Usa synchronized y wait/notify para controlar los turnos.(Versión Base (Monitor): Ejecuta CenaFilosofo.java.)
    Semáforos (El Portero): Usa un "portero" que limita el aforo a 4 filósofos para asegurar que siempre haya hueco.(Versión Semáforos: Ejecuta CenaFilosofosSemaforos.java)
    Timeout: Si un filósofo tarda mucho en comer, se rinde y suelta los tenedores para no bloquear al resto.(Versión Timeout: (Requiere cambiar la clase instanciada en el main))
