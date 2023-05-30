package gameLaby.laby;

import moteurJeu.Clavier;
import moteurJeu.Jeu;

import java.io.IOException;

public class LabyJeu implements Jeu {

    private Labyrinthe laby;

    public LabyJeu() throws IOException {
        try {
            Labyrinthe laby = new Labyrinthe("labySimple/laby1.txt");
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement du labyrinthe");
        }
    }

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

    public void init() {
        // TODO
    }

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
