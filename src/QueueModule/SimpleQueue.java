package QueueModule;

public interface SimpleQueue<E> {

	public void enqueue(E element); //● Inserta element al final de la cola.
	public E dequeue();  //● Remueve el primer elemento de la cola y lo devuelve.
	public E peek ();  //● Devuelve el primer elemento de la cola sin removerlo.
	public void clear();  //● Borra todos los elementos de la cola, dejándola vacía.
	public int size();  //● Devuelve la cantidad de elementos en la cola.
	public boolean isEmpty();
}