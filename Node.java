package semaforos;

public class Node {
	private String content;
	private Node next;

	/*
	 * Pre:--
	 *  Post: Esta es la clase nodo, son los nodos que se añaden a la cola
	 */

	public Node(String content, Node next) {
		this.content = content;
		this.next = next;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[content = " + content + ",next = " + next + "]";
	}

}
