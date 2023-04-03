package semaforos;

import java.util.Random;

/*Pre:--
 Post: creamso el hilo de lectrura
 * */
public class Write extends Thread {
	private ColaFIFO cola;
	private String escritor;
	Random rn = new Random();

	public Write(ColaFIFO cola,String escritor) {
		this.cola = cola;
		this.escritor =escritor;
	}
	

	public String getEscritor() {
		return escritor;
	}


	public void setEscritor(String escritor) {
		this.escritor = escritor;
	}

	
	/*
	 * Pre:--
	 * Post: En este metodo run, lo que se hace es un push que simulala que
	 * un usuario esta escribiedno en la cola los escritores solo pueden escribir 8
	 * veces
	 */
	public void run() {
		for (int i = 0; i < 8; i++) {
			System.out.println("----------------------------------------------------------------------------------------hilo de escritura numero:"+getEscritor());
			
				Node n1 = new Node("hola:", null);
				cola.push(n1);
				System.out.println("******** " + getEscritor() + " ****esta escribiendo");	
				
			
			
		}
	}
}
