package treeModule;

public class ScoreEntry implements Comparable<ScoreEntry> {
    public String name;
    public int score;

    public ScoreEntry(String name, int enemies, int time) {
        this.name = name;
        this.score = enemies * 100 - time * 10; //Puntaje basado en enemigos derrotados y tiempo
    }


    @Override
    public int compareTo(ScoreEntry other) 
    {
        int cmp = Integer.compare(other.score, this.score); //Ordenar de mayor a menor puntaje
        if (cmp != 0) return cmp;
        return this.name.compareTo(other.name); //Si los puntajes son iguales, ordenar alfabeticamente por nombre
    }

}
