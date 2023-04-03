package semaforos;

import java.util.ArrayList;

public class Main {
	/*
	 * Pre:--
	 *  Post: Este es el metodo principal. primero se crean listas de hilos de lectura y escritura, luego
	 *  	se meten los hilos a sus respectivas listas. con un bucle recorremos las listas y los vamos arrancando.
	 *  	en otro bucle los inicializamos.
	 */
	public static void main(String[] args) {
		ColaFIFO cola = new ColaFIFO(10);
		String[] escritor = { "E1", "E2", "E3", "E4" };
		String[] leector = { "L1", "L2", "L3", "L4","L5" };
		ArrayList<Read> lisLectura = new ArrayList<Read>();
		ArrayList<Write> listEscritura = new ArrayList<Write>();
		for (int i = 0; i < 5; i++) {
			Read readingProcessing = new Read(cola,leector[i]);
			lisLectura.add(readingProcessing);
		}

		for (int i = 0; i < 4; i++) {// 5 procesos lectores
			Write writingProcessing = new Write(cola,escritor[i]);
			listEscritura.add(writingProcessing);
		}
		// ---------------------------------------------------------------------
		for (int i = 0; i < lisLectura.size(); i++) { // i= 0,1,2,3,4
			if (i < listEscritura.size()) { // size =4
				System.out.println("escritor nº: " + i + " arranca");
				listEscritura.get(i).start(); // i = 0,1,2,3
			}
			lisLectura.get(i).start();// arrancamos el hilo read
			System.out.println("lector nº: " + i + " arranca");
		}

		for (int i = 0; i < lisLectura.size(); i++) {
			try {
				if (i < listEscritura.size()) {
					listEscritura.get(i).join();
				}
				lisLectura.get(i).join();
				

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} // arrancamos el hilo read
		System.out.println("size de la cola: "+cola.getSize());
	}
	
	
	
}
