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
        laby = new Labyrinthe("labySimple/laby3.txt");
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
            this.laby.deplacer(laby.getPJ(), Labyrinthe.HAUT);
        if (clavier.bas)
            this.laby.deplacer(laby.getPJ(), Labyrinthe.BAS);
        if (clavier.gauche)
            this.laby.deplacer(laby.getPJ(), Labyrinthe.GAUCHE);
        if (clavier.droite)
            this.laby.deplacer(laby.getPJ(), Labyrinthe.DROITE);

        // attaque du joueur
        if (clavier.espace){
            for (Entite e : laby.getEntites()){
                laby.getPJ().attquer(e);
            }
        }

        // enlève les entités mortes
        for (Entite e : laby.getEntites()){
            if (e.getPv() <= 0){
                laby.getEntites().remove(e);
                if (e instanceof Monstre)
                    laby.getMonstre().remove(e);
                else if (e instanceof Fantome)
                    laby.getFantome().remove(e);
                else if (e instanceof Troll)
                    laby.getTrolls().remove(e);
            }

        }

        // deplace les monstres avec une proba de 5%
        for (Monstre m : laby.getMonstre()) {
            double randMonstre = Math.random();
            if (randMonstre <= 0.05)
                this.laby.deplacer(m, "aleatoire");
        }

        // deplace les fantomes avec une proba de 5%
        for (Fantome f : laby.getFantome()) {
            double randFantome = Math.random();
            if (randFantome <= 0.05)
                this.laby.deplacer(f, "aleatoire");
        }

        //deplace les trolls avec une proba de 5%
        for (Troll t : laby.getTrolls()) {
            double randTroll = Math.random();
            if (randTroll <= 0.05)
                this.laby.deplacer(t, "aleatoire");
        }

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
