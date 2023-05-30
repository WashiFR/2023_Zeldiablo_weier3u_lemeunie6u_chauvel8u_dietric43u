package gameLaby.laby;

import moteurJeu.Clavier;
import moteurJeu.Jeu;

import java.io.IOException;

public class LabyJeu implements Jeu {

    public void update(double secondes, Clavier clavier) {
        // TODO
    }

    public void init() throws IOException {
        Labyrinthe laby = new Labyrinthe("labySimple/laby1.txt");
    }

    public boolean etreFini() {
        // TODO
        return false;
    }

}
