package treeModule;

// Representa una entrada del scoreboard de un juego.
// Implementa Comparable para poder insertarse en un BST/AVL ordenado por puntaje.
public class ScoreEntry implements Comparable<ScoreEntry> {

    public String name;  // nombre del jugador
    public int score;    // puntaje calculado a partir de enemigos y tiempo

    // El puntaje se calcula como: enemigos derrotados * 100 - tiempo en segundos * 10.
    // Matar más enemigos suma puntos; tardar más tiempo los resta.
    public ScoreEntry(String name, int enemies, int time) {
        this.name = name;
        this.score = enemies * 100 - time * 10;
    }

    // Ordena de mayor a menor puntaje.
    // Si dos jugadores tienen el mismo puntaje, se ordenan alfabéticamente por nombre.
    @Override
    public int compareTo(ScoreEntry other) {
        int cmp = Integer.compare(other.score, this.score); // orden descendente de puntaje
        if (cmp != 0) return cmp;
        return this.name.compareTo(other.name); // desempate alfabético
    }
}
