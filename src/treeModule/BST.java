package treeModule;

import ListModule.SImpleList;
import ListModule.SimpleArrayList;
import QueueModule.SimpleArrayQueue;
import QueueModule.SimpleQueue;

public class BST<E extends Comparable<E>> {
    private TreeNode<E> root = null;
    int size = 0;

    public void insert(E value){
        if(value == null) throw new IllegalArgumentException("Value cannot be null");
        root = insertRecursive(root, value);
    }

    protected TreeNode<E> insertRecursive(TreeNode<E> current, E value){
        if(current == null) {
            size++;
            return new TreeNode<>(value);
        }
        int comparison = value.compareTo(current.value); //Comparar el valor a insertar con el valor del nodo actual
        if(comparison < 0) { //-1: el valor es menor que el valor del nodo actual
            current.left = insertRecursive(current.left, value); //Insertar en el subárbol izquierdo
        } else if(comparison > 0) { //1: el valor es mayor que el valor del nodo actual
            current.right = insertRecursive(current.right, value); //Insertar en el subárbol derecho
        }
        //Si comparison == 0, el valor ya existe en el árbol, no se insertan duplicados
        return current;
    }

    public void remove(E value){
        root = removeRecursive(root, value);
    }

    protected TreeNode<E> removeRecursive(TreeNode<E> current, E value){
        if(current == null) return null; //Valor no encontrado, retornar null

        int comparison = value.compareTo(current.value);
        if(comparison < 0) { //Valor a eliminar es menor que el valor del nodo actual, buscar en el subárbol izquierdo
            current.left = removeRecursive(current.left, value);
        } else if(comparison > 0) { //Valor a eliminar es mayor que el valor del nodo actual, buscar en el subárbol derecho
            current.right = removeRecursive(current.right, value);
        } else { //Valor encontrado, eliminar el nodo
            if(current.left == null && current.right == null) { //Caso 1: nodo sin hijos
                size--;
                return null;
            } else if(current.left == null) { //Caso 2: nodo con un hijo derecho
                size--;
                return current.right;
            } else if(current.right == null) { //Caso 2: nodo con un hijo izquierdo
                size--;
                return current.left;
            } else { //Caso 3: nodo con dos hijos
                size--;
                TreeNode<E> smallest = findSmallest(current.right); //Encontrar el nodo más pequeño en el subárbol derecho
                current.value = smallest.value; //Reemplazar el valor del nodo actual con el valor del nodo más pequeño
                current.right = removeRecursive(current.right, smallest.value); //Eliminar el nodo más pequeño del subárbol derecho
            }
        }
        return current;
    }

    protected TreeNode<E> findSmallest(TreeNode<E> current){
        while(current.left != null) {
            current = current.left; //El nodo más pequeño siempre estará en el extremo izquierdo
        }
        return current;
    }

    public boolean contains(E value){
        return containsRecursive(root, value);
    }

    private boolean containsRecursive(TreeNode<E> current, E value){
        if(current == null) return false; //Valor no encontrado
        int comparison = value.compareTo(current.value);
        if(comparison == 0) return true; //Valor encontrado
        else if(comparison < 0) return containsRecursive(current.left, value); //Buscar en el subárbol izquierdo
        else return containsRecursive(current.right, value); //Buscar en el subárbol derecho
    }
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }

    public SImpleList<E> preOrder(){
        SImpleList<E> result = new SimpleArrayList<E>();
        preOrderDFS(root, result);
        return result;
    }

    private void preOrderDFS(TreeNode<E> current, SImpleList<E> result){
        if(current == null) return;
        result.add(current.value); //Visitar el nodo actual
        preOrderDFS(current.left, result); //Recorrer el subárbol izquierdo
        preOrderDFS(current.right, result); //Recorrer el subárbol derecho
    }

    public SImpleList<E> inOrder(){
        SImpleList<E> result = new SimpleArrayList<E>();
        inOrderDFS(root, result);
        return result;
    }

    private void inOrderDFS(TreeNode<E> current, SImpleList<E> result){
        if(current == null) return;
        inOrderDFS(current.left, result); //Recorrer el subárbol izquierdo
        result.add(current.value); //Visitar el nodo actual
        inOrderDFS(current.right, result); //Recorrer el subárbol derecho
    }

    public SImpleList<E> postOrder(){
        SImpleList<E> result = new SimpleArrayList<E>();
        postOrderDFS(root, result);
        return result;
    }

    private void postOrderDFS(TreeNode<E> current, SImpleList<E> result){
        if(current == null) return;
        postOrderDFS(current.left, result); //Recorrer el subárbol izquierdo
        postOrderDFS(current.right, result); //Recorrer el subárbol derecho
        result.add(current.value); //Visitar el nodo actual
    }

    public SImpleList<E> levelOrder(){
        SImpleList<E> result = new SimpleArrayList<E>();
        if(root == null) return result; //Árbol vacío
        SimpleQueue<TreeNode<E>> queue = new SimpleArrayQueue<TreeNode<E>>();

        queue.enqueue(root); //Iniciar con la raíz
        while(!queue.isEmpty()) {
            TreeNode<E> current = queue.dequeue(); //Obtener el nodo actual de la cola
            result.add(current.value); //Visitar el nodo actual
            if(current.left != null) queue.enqueue(current.left); //Agregar el hijo izquierdo a la cola si existe
            if(current.right != null) queue.enqueue(current.right); //Agregar el hijo derecho a la cola si existe
        }
        
        return result;
    }

    public int height() {
        return heightRecursive(root);
    }
    
    protected int heightRecursive(TreeNode<E> current) {
        if(current == null) return -1; //La altura de un árbol vacío es -1
        int leftHeight = heightRecursive(current.left); //Altura del subárbol izquierdo 
        int rightHeight = heightRecursive(current.right); //Altura del subárbol derecho
        return 1 + Math.max(leftHeight, rightHeight); //La altura del nodo actual es 1 + la altura máxima de sus hijos7
    }
}
