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

    /**
     * Méthode update de LabyJeu qui permet de mettre à jour le jeu
     *
     * @param secondes secondes
     * @param clavier  clavier
     */
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
        // deplace le monstre avec une proba de 5%
        double randMonstre = Math.random();
        if (randMonstre <= 0.05)
            this.laby.deplacerMonstre();
        // deplace le fantome avec une proba de 5%
        double randFantome = Math.random();
        if (randFantome <= 0.05)
            this.laby.deplacerFantome();
        // TODO : update secondes
        // Vérifier si le joueur a gagné
        if (this.etreFini()) {
            System.out.println("Vous avez gagné !");
            System.exit(0);
            // Vérifier si le joueur a perdu
        } else if (this.etrePerdu()) {
            System.out.println("Vous avez perdu !");
            System.exit(0);
        }


    }

    @Override
    public void init() {
        // TODO
    }

    /**
     * Méthode etreFini de LabyJeu qui permet de savoir si le jeu est fini
     *
     * @return true si le jeu est fini, false sinon
     */
    @Override
    public boolean etreFini() {
        return this.laby.etreFini();
    }

    /**
     * Méthode etrePerdu de LabyJeu qui permet de savoir si le jeu est perdu
     *
     * @return true si le jeu est perdu, false sinon
     */
    public boolean etrePerdu() {
        return this.laby.etrePerdu();
    }

    /**
     * Retourne le labyrinthe
     *
     * @return labyrinthe
     */
    public Labyrinthe getLaby() {
        return this.laby;
    }

}
