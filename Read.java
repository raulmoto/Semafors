package semaforos;

import java.util.Random;

public class Read extends Thread {
	private ColaFIFO cola;
	private String leector;
	Random rn = new Random();

	public Read(ColaFIFO cola, String leector) {
		this.cola = cola;
		this.leector = leector;
	}

	public String getLeector() {
		return leector;
	}

	public void setLeector(String leector) {
		this.leector = leector;
	}

	/*
	 * Pre:-- 
	 * Post: En este metodo run, lo que se hace es un pop() que simulala que
	 * un usuario esta leyendo en la cola los lectores solo pueden leer 6 veces
	 */
	public void run() {
		for (int i = 0; i < 6; i++) {
			System.out.println(
					"************************************************************************hilo de lectura numero: "
							+ getLeector());
				cola.pop();
				System.out.println("******** " + getLeector() + " ****esta leyendo");
		}
	}
}
