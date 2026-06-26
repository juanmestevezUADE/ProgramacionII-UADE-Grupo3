package stackModule;

// Implementación de pila usando un arreglo dinámico.
// El tope de la pila es siempre el último elemento del arreglo (posición size-1).
// Cuando el arreglo se llena, se duplica su capacidad automáticamente.
public class SimpleArrayStack<E> implements SimpleStack<E> {

    private int size;
    private E[] stack;
    private static final int DEFAULT_CAPACITY = 4;

    @SuppressWarnings("unchecked")
    public SimpleArrayStack() {
        stack = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public SimpleArrayStack(int capacity) {
        stack = (E[]) new Object[capacity];
    }

    // Apila el elemento en el tope (siguiente posición libre del arreglo)
    @Override
    public void push(E item) {
        resize();
        stack[size] = item;
        size++;
    }

    // Desapila y devuelve el elemento del tope (último en el arreglo)
    @Override
    public E pop() {
        if (size == 0) {
            throw new RuntimeException("Stack is empty");
        }
        E item = stack[size - 1];
        stack[size - 1] = null; // limpiar la referencia para evitar memory leaks
        size--;
        return item;
    }

    // Devuelve el elemento del tope sin eliminarlo
    @Override
    public E peek() {
        if (size == 0) {
            throw new RuntimeException("Stack is empty");
        }
        return stack[size - 1];
    }

    @Override
    public int size() {
        return size;
    }

    // Reinicia la pila con un arreglo vacío de capacidad por defecto
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        stack = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Si el arreglo está lleno, crea uno nuevo con el doble de capacidad y copia los datos
    @SuppressWarnings("unchecked")
    private void resize() {
        if (size == stack.length) {
            E[] newStack = (E[]) new Object[stack.length * 2];
            for (int i = 0; i < size; i++) {
                newStack[i] = stack[i];
            }
            stack = newStack;
        }
    }
}
