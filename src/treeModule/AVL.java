package treeModule;

public class AVL<E extends Comparable<E>> extends BST<E> {
    
    @Override
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
        else return current;
        //Si comparison == 0, el valor ya existe en el árbol, no se insertan duplicados
        return balanceNode(current); // Balancear el nodo después de la inserción
    }

    @Override
    protected TreeNode<E> removeRecursive(TreeNode<E> current, E value){
        if(current == null) return null; //Valor no encontrado, retornar null

        int comparison = value.compareTo(current.value);
        if(comparison < 0) { //Valor a eliminar es menor que el valor del nodo actual, buscar en el subárbol izquierdo
            current.left = removeRecursive(current.left, value);
        } else if(comparison > 0) { //Valor a eliminar es mayor que el valor del nodo actual, buscar en el subárbol derecho
            current.right = removeRecursive(current.right, value);
        } else { //Valor encontrado, eliminar el nodo
            if(current.left == null && current.right == null) { //Caso 1: nodo sin hijos
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
        return balanceNode(current); // Balancear el nodo después de la eliminación
    }

    @Override
    protected TreeNode<E> findSmallest(TreeNode<E> current){
        while(current.left != null) {
            current = current.left; //El nodo más pequeño siempre estará en el extremo izquierdo
        }
        return current;
    }

    private TreeNode<E> balanceNode(TreeNode<E> node) {
        //casos lx
        if (getNodeBalanceFactor(node) > 1){
            //caso LL
            if (getNodeBalanceFactor(node.left) >= 0) {
                return rotateRight(node);
            } else { //caso LR
                return rotateLeftRight(node);
            }
        }
        //casos rx
        if (getNodeBalanceFactor(node) < -1){
            //caso RR
            if (getNodeBalanceFactor(node.right) <= 0) {
                return rotateLeft(node);
            } else { //caso RL
                return rotateRightLeft(node);
            }
        }
        return node; // Nodo balanceado, no se necesita rotación
    }
    
    private int getNodeBalanceFactor(TreeNode<E> node) {
        if (node == null) return 0;
        return heightRecursive(node.left) - heightRecursive(node.right);
    }
    
    private TreeNode<E> rotateRight(TreeNode<E> y) {
        TreeNode<E> x = y.left;
        TreeNode<E> T2 = x.right;

        // Realizar rotación
        x.right = y;
        y.left = T2;

        return x; // Nueva raíz después de la rotación
    }

    private TreeNode<E> rotateLeft(TreeNode<E> x) {
        TreeNode<E> y = x.right;
        TreeNode<E> T2 = y.left;

        // Realizar rotación
        y.left = x;
        x.right = T2;

        return y; // Nueva raíz después de la rotación
    }

    private TreeNode<E> rotateRightLeft(TreeNode<E> z) {
        z.right = rotateRight(z.right);
        return rotateLeft(z);
    }

    private TreeNode<E> rotateLeftRight(TreeNode<E> z) {
        z.left = rotateLeft(z.left);
        return rotateRight(z);
    }
}
