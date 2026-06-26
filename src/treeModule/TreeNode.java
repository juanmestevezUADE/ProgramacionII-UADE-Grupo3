package treeModule;

// Nodo genérico para árboles binarios.
// Cada nodo guarda un valor y referencias a su hijo izquierdo y derecho.
// E es el tipo de dato del valor almacenado.
public class TreeNode<E> {
    public TreeNode<E> left = null;  // hijo izquierdo (valores menores en BST/AVL)
    public TreeNode<E> right = null; // hijo derecho (valores mayores en BST/AVL)
    public E value;                  // dato almacenado en el nodo

    public TreeNode(E value) {
        this.value = value;
    }
}
