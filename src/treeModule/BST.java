package treeModule;

import ListModule.SImpleList;
import ListModule.SimpleArrayList;
import QueueModule.SimpleArrayQueue;
import QueueModule.SimpleQueue;

// Árbol Binario de Búsqueda (BST: Binary Search Tree).
// Propiedad: para cada nodo, todos los valores del subárbol izquierdo son menores
// y todos los del subárbol derecho son mayores.
// E debe ser Comparable para poder comparar los valores al insertar/buscar.
public class BST<E extends Comparable<E>> {

    protected TreeNode<E> root = null; // raíz del árbol
    int size = 0;                      // cantidad de nodos

    // Inserta un valor en el árbol. No permite duplicados.
    public void insert(E value) {
        if (value == null) throw new IllegalArgumentException("Value cannot be null");
        root = insertRecursive(root, value);
    }

    // Inserción recursiva: baja por el árbol comparando en cada nodo
    // hasta encontrar la posición correcta (null) e inserta ahí.
    protected TreeNode<E> insertRecursive(TreeNode<E> current, E value) {
        if (current == null) {
            size++;
            return new TreeNode<>(value);
        }
        int comparison = value.compareTo(current.value);
        if (comparison < 0) {
            // El valor es menor: ir al subárbol izquierdo
            current.left = insertRecursive(current.left, value);
        } else if (comparison > 0) {
            // El valor es mayor: ir al subárbol derecho
            current.right = insertRecursive(current.right, value);
        }
        // Si comparison == 0, el valor ya existe: no se insertan duplicados
        return current;
    }

    // Elimina el valor del árbol si existe
    public void remove(E value) {
        root = removeRecursive(root, value);
    }

    // Eliminación recursiva: baja por el árbol hasta encontrar el nodo a eliminar.
    // Maneja 3 casos: sin hijos, un hijo, dos hijos.
    protected TreeNode<E> removeRecursive(TreeNode<E> current, E value) {
        if (current == null) return null; // valor no encontrado

        int comparison = value.compareTo(current.value);
        if (comparison < 0) {
            // El valor está en el subárbol izquierdo
            current.left = removeRecursive(current.left, value);
        } else if (comparison > 0) {
            // El valor está en el subárbol derecho
            current.right = removeRecursive(current.right, value);
        } else {
            // Nodo encontrado: eliminar
            if (current.left == null && current.right == null) {
                // Caso 1: hoja (sin hijos) — simplemente eliminarlo
                size--;
                return null;
            } else if (current.left == null) {
                // Caso 2: solo tiene hijo derecho — reemplazar por él
                size--;
                return current.right;
            } else if (current.right == null) {
                // Caso 2: solo tiene hijo izquierdo — reemplazar por él
                size--;
                return current.left;
            } else {
                // Caso 3: tiene dos hijos — reemplazar con el sucesor in-order
                // (el menor valor del subárbol derecho) y eliminar ese sucesor
                size--;
                TreeNode<E> smallest = findSmallest(current.right);
                current.value = smallest.value;
                current.right = removeRecursive(current.right, smallest.value);
            }
        }
        return current;
    }

    // Busca el nodo con el valor mínimo en el subárbol dado (siempre el más a la izquierda)
    protected TreeNode<E> findSmallest(TreeNode<E> current) {
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // Devuelve true si el valor existe en el árbol
    public boolean contains(E value) {
        return containsRecursive(root, value);
    }

    // Búsqueda recursiva: compara en cada nodo y baja por el lado correcto
    private boolean containsRecursive(TreeNode<E> current, E value) {
        if (current == null) return false;
        int comparison = value.compareTo(current.value);
        if (comparison == 0) return true;
        else if (comparison < 0) return containsRecursive(current.left, value);
        else return containsRecursive(current.right, value);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // Recorrido Pre-Order (raíz → izquierda → derecha)
    public SImpleList<E> preOrder() {
        SImpleList<E> result = new SimpleArrayList<E>();
        preOrderDFS(root, result);
        return result;
    }

    private void preOrderDFS(TreeNode<E> current, SImpleList<E> result) {
        if (current == null) return;
        result.add(current.value);           // visitar primero el nodo actual
        preOrderDFS(current.left, result);   // luego el subárbol izquierdo
        preOrderDFS(current.right, result);  // luego el subárbol derecho
    }

    // Recorrido In-Order (izquierda → raíz → derecha)
    // En un BST, esto devuelve los elementos en orden ascendente.
    public SImpleList<E> inOrder() {
        SImpleList<E> result = new SimpleArrayList<E>();
        inOrderDFS(root, result);
        return result;
    }

    private void inOrderDFS(TreeNode<E> current, SImpleList<E> result) {
        if (current == null) return;
        inOrderDFS(current.left, result);    // primero el subárbol izquierdo
        result.add(current.value);           // luego el nodo actual
        inOrderDFS(current.right, result);   // luego el subárbol derecho
    }

    // Recorrido Post-Order (izquierda → derecha → raíz)
    public SImpleList<E> postOrder() {
        SImpleList<E> result = new SimpleArrayList<E>();
        postOrderDFS(root, result);
        return result;
    }

    private void postOrderDFS(TreeNode<E> current, SImpleList<E> result) {
        if (current == null) return;
        postOrderDFS(current.left, result);  // primero el subárbol izquierdo
        postOrderDFS(current.right, result); // luego el subárbol derecho
        result.add(current.value);           // finalmente el nodo actual
    }

    // Recorrido Level-Order (por niveles, de arriba hacia abajo).
    // Usa una cola para procesar los nodos nivel por nivel.
    public SImpleList<E> levelOrder() {
        SImpleList<E> result = new SimpleArrayList<E>();
        if (root == null) return result;

        SimpleQueue<TreeNode<E>> queue = new SimpleArrayQueue<TreeNode<E>>();
        queue.enqueue(root);

        while (!queue.isEmpty()) {
            TreeNode<E> current = queue.dequeue();
            result.add(current.value);
            if (current.left != null) queue.enqueue(current.left);
            if (current.right != null) queue.enqueue(current.right);
        }

        return result;
    }

    // Devuelve la altura del árbol (número de aristas en el camino más largo desde la raíz a una hoja)
    public int height() {
        return heightRecursive(root);
    }

    // La altura de un árbol vacío es -1. La de un nodo hoja es 0.
    protected int heightRecursive(TreeNode<E> current) {
        if (current == null) return -1;
        int leftHeight = heightRecursive(current.left);
        int rightHeight = heightRecursive(current.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }
}
