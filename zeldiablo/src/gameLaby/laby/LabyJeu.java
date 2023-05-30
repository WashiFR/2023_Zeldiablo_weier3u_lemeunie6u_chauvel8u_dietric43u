package gameLaby.laby;

import moteurJeu.Clavier;
import moteurJeu.Jeu;

import java.io.IOException;

/**
 * Représente un jeu de labyrinthe
 */
public class LabyJeu implements Jeu {

    /**
     * Attribut laby de type Labyrinthe
     */
    private Labyrinthe laby;

    /**
     * Constructeur de LabyJeu
     */
    public LabyJeu() throws IOException {
        laby = new Labyrinthe("labySimple/laby1.txt");
    }

    @Override
    public void update(double secondes, Clavier clavier) {
        if (clavier.haut)
            this.laby.deplacerPerso(Labyrinthe.HAUT);
        if (clavier.bas)
            this.laby.deplacerPerso(Labyrinthe.BAS);
        if (clavier.gauche)
            this.laby.deplacerPerso(Labyrinthe.GAUCHE);
        if (clavier.droite)
            this.laby.deplacerPerso(Labyrinthe.DROITE);

        // TODO : update secondes
    }

    @Override
    public void init() {
        // TODO
    }

    @Override
    public boolean etreFini() {
        // TODO
        return false;
    }

    /**
     * Retourne le labyrinthe
     * @return labyrinthe
     */
    public Labyrinthe getLaby() {
        return this.laby;
    }

}
