package priorityqueueModule;

public interface SimplePriorityqueue<E> {
    public void enqueue(E element, int priority); //● Inserta element en la cola con la prioridad dada.
	public E dequeue();  //● Remueve el primer elemento de la cola y lo devuelve.
	public E peek ();  //● Devuelve el primer elemento de la cola sin removerlo.
    public int getHighestPriority(); //● Devuelve la prioridad más alta de los elementos en la cola.
	public void clear();  //● Borra todos los elementos de la cola, dejándola vacía.
	public int size();  //● Devuelve la cantidad de elementos en la cola.
	public boolean isEmpty();
}
