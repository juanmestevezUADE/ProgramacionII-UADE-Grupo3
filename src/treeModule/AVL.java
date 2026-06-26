package treeModule;

// Árbol AVL: extensión del BST que se auto-balancea después de cada inserción o eliminación.
// La propiedad AVL garantiza que para cada nodo, la diferencia de alturas entre
// el subárbol izquierdo y derecho (factor de balance) sea -1, 0 o 1.
// Si el factor sale de ese rango, se aplica una rotación para restaurar el balance.
public class AVL<E extends Comparable<E>> extends BST<E> {

    // Inserción con balanceo automático.
    // Después de insertar recursivamente, se balancea el nodo al volver.
    @Override
    protected TreeNode<E> insertRecursive(TreeNode<E> current, E value) {
        if (current == null) {
            size++;
            return new TreeNode<>(value);
        }
        int comparison = value.compareTo(current.value);
        if (comparison < 0) {
            current.left = insertRecursive(current.left, value);
        } else if (comparison > 0) {
            current.right = insertRecursive(current.right, value);
        } else {
            return current; // duplicado: no insertar
        }
        // Al volver de la recursión, balancear el nodo actual si es necesario
        return balanceNode(current);
    }

    // Eliminación con balanceo automático.
    // Después de eliminar recursivamente, se balancea el nodo al volver.
    @Override
    protected TreeNode<E> removeRecursive(TreeNode<E> current, E value) {
        if (current == null) return null;

        int comparison = value.compareTo(current.value);
        if (comparison < 0) {
            current.left = removeRecursive(current.left, value);
        } else if (comparison > 0) {
            current.right = removeRecursive(current.right, value);
        } else {
            // Nodo encontrado: misma lógica de eliminación que en BST
            if (current.left == null && current.right == null) {
                size--;
                return null;
            } else if (current.left == null) {
                size--;
                return current.right;
            } else if (current.right == null) {
                size--;
                return current.left;
            } else {
                // Dos hijos: reemplazar con el sucesor in-order
                size--;
                TreeNode<E> smallest = findSmallest(current.right);
                current.value = smallest.value;
                current.right = removeRecursive(current.right, smallest.value);
            }
        }
        // Balancear al volver de la recursión
        return balanceNode(current);
    }

    @Override
    protected TreeNode<E> findSmallest(TreeNode<E> current) {
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // Determina si el nodo necesita rotación y aplica la correcta.
    // Factor de balance > 1: el subárbol izquierdo es más alto (casos LL o LR)
    // Factor de balance < -1: el subárbol derecho es más alto (casos RR o RL)
    private TreeNode<E> balanceNode(TreeNode<E> node) {
        if (getNodeBalanceFactor(node) > 1) {
            if (getNodeBalanceFactor(node.left) >= 0) {
                return rotateRight(node); // Caso LL: rotación simple a la derecha
            } else {
                return rotateLeftRight(node); // Caso LR: rotación doble izquierda-derecha
            }
        }
        if (getNodeBalanceFactor(node) < -1) {
            if (getNodeBalanceFactor(node.right) <= 0) {
                return rotateLeft(node); // Caso RR: rotación simple a la izquierda
            } else {
                return rotateRightLeft(node); // Caso RL: rotación doble derecha-izquierda
            }
        }
        return node; // nodo ya balanceado, no se necesita rotación
    }

    // El factor de balance es la diferencia de alturas: altura(izquierda) - altura(derecha).
    // Positivo: más cargado a la izquierda. Negativo: más cargado a la derecha.
    private int getNodeBalanceFactor(TreeNode<E> node) {
        if (node == null) return 0;
        return heightRecursive(node.left) - heightRecursive(node.right);
    }

    // Rotación simple a la derecha (caso LL).
    // El hijo izquierdo (x) sube y el nodo actual (y) baja a la derecha de x.
    private TreeNode<E> rotateRight(TreeNode<E> y) {
        TreeNode<E> x = y.left;
        TreeNode<E> T2 = x.right;

        x.right = y;
        y.left = T2;

        return x; // x es la nueva raíz del subárbol
    }

    // Rotación simple a la izquierda (caso RR).
    // El hijo derecho (y) sube y el nodo actual (x) baja a la izquierda de y.
    private TreeNode<E> rotateLeft(TreeNode<E> x) {
        TreeNode<E> y = x.right;
        TreeNode<E> T2 = y.left;

        y.left = x;
        x.right = T2;

        return y; // y es la nueva raíz del subárbol
    }

    // Rotación doble derecha-izquierda (caso RL).
    // Primero rota el hijo derecho a la derecha, luego rota el nodo actual a la izquierda.
    private TreeNode<E> rotateRightLeft(TreeNode<E> z) {
        z.right = rotateRight(z.right);
        return rotateLeft(z);
    }

    // Rotación doble izquierda-derecha (caso LR).
    // Primero rota el hijo izquierdo a la izquierda, luego rota el nodo actual a la derecha.
    private TreeNode<E> rotateLeftRight(TreeNode<E> z) {
        z.left = rotateLeft(z.left);
        return rotateRight(z);
    }
}
