package semaforos;

import java.util.concurrent.Semaphore;

/*Pre:--
 *Post: se crea la cola que sera un ob jeto compartido por todos los procesos 
 * */

public class ColaFIFO {
	private int size;
	private Node first;
	private Node last;
	private int maxsize;
	private Semaphore escribir ;
	private Semaphore leer ;
	private Semaphore mutex;
	

	/*
	 * Pre:-- 
	 * Post: inicializamos los semaforos. 3 semaforos, inicializamos escribir
	 * para que primero se pueda escribir primero. el leer se inicialice a 0 y el
	 * mutex de exclusividad mutua lo iniciamoa a 1 para dar paso
	 */
	 // exclusion mutua
	/*
	 * Pre:-- 
	 * Post: se generan los getters y setters
	 * 
	 */
		
	public ColaFIFO(int maxsize) {
		this.maxsize = maxsize;
		this.size = 0;
		this.first = null;
		this.escribir = new Semaphore(maxsize);
		this.leer = new Semaphore(0);
		this.mutex = new Semaphore(1);
		
	}
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Node getFirst() {
		return first;
	}

	public void setFirst(Node first) {
		this.first = first;
	}

	public Node getLast() {
		return last;
	}

	public void setLast(Node last) {
		this.last = last;
	}

	/*
	 * Pre:--
	 * Post: el metodo push() mete los nodos en la cola
	 */

	public boolean push(Node node) {
		try {
			escribir.acquire();
			mutex.acquire();// revisamos la exclision mutua
			if (size == 0) {
				first = node;
				last = node;
			} else if(size != maxsize) {
				last.setNext(node);// el segundo va despues del primero

				last = node;
			}
			size++;
			mutex.release(1);// liberamos
			leer.release(1);
			
			return true;

		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}

	}

	/*
	 * Pre:--
	 * Post:el metodo pop() saca los datos de la cola de manera que (en la
	 * cola) el primero en entrar es el primero en salir
	 */
	public Node pop() { // sacar
		try {
			leer.acquire();
			mutex.acquire();
			if (size == 0) {
				System.out.println(" ERROR:nada que sacar");
				return null;
			} else {
				Node p = first;
				first = p.getNext();
				size--;
				escribir.release();
				mutex.release();
				return p;
			}

		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}

	}

	/*
	 * Pre:-- 
	 * Post:creamos el metodo para ver lo que hay en la cola
	 */
	public void show() {
		Node p = first;
		for (int i = 0; i < size; i++) {
			System.out.println("[ " + i + "] -> " + p.getContent());
			p = p.getNext();
		}
	}
}
