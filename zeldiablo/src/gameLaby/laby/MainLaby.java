package gameLaby.laby;

import moteurJeu.MoteurJeu;

import java.io.IOException;

/**
 * classe qui lance le jeu du labyrinthe
 */
public class MainLaby {

    public static void main(String[] args) throws IOException {
        int width = 800;
        int height = 600;
        int pFPS = 60;

        // creation des objets
        LabyJeu jeuLaby = new LabyJeu();
//        LabyDessin dessinLaby = new LabyDessin();
        LabyImage labyImage = new LabyImage();

        // parametrage du moteur de jeu
        MoteurJeu.setTaille(width, height);
        MoteurJeu.setFPS(pFPS);

        // lancement du jeu
//        MoteurJeu.launch(jeuLaby, dessinLaby);
        MoteurJeu.launch(jeuLaby, labyImage);
    }
}
