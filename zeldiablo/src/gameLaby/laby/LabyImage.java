package gameLaby.laby;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import moteurJeu.DessinJeu;
import moteurJeu.Jeu;
import javafx.scene.image.Image;

/**
 * Affiche un labyrinthe avec des images
 */
public class LabyImage implements DessinJeu {

    public static final String URL = "C:\\Users\\loris\\OneDrive\\Documents\\GitHub\\2023_Zeldiablo_weier3u_lemeunie6u_chauvel8u_dietric43u\\zeldiablo\\";

    public static final String MUR = URL + "img\\wall.png";

    public static final String PJ = URL + "img\\player.png";

    public static final String MONSTRE = URL + "img\\monster.png";

    public static final String SOL = URL + "img\\floor.png";

    private static final int TAILLE_CASE = 50;

    public void dessinerJeu(Jeu jeu, Canvas canvas){
        LabyJeu labyJeu = (LabyJeu) jeu;

        final GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        Labyrinthe laby = labyJeu.getLaby();
        for (int i = 0; i<laby.getLength(); i++) {
            for (int j = 0; j<laby.getLengthY(); j++) {
                Image sol = new Image(SOL, TAILLE_CASE, TAILLE_CASE, false, false);
                gc.drawImage(sol, i * TAILLE_CASE, j * TAILLE_CASE);
                if (laby.getMur(i,j)) {
                    // Affiche l'image du mur
                    Image mur = new Image(MUR, TAILLE_CASE, TAILLE_CASE, false, false);
                    gc.drawImage(mur, i * TAILLE_CASE, j * TAILLE_CASE);
                }
                else if (laby.getMonstre(i, j)) {
                    // Affiche l'image du monstre
                    Image monstre = new Image(MONSTRE, TAILLE_CASE, TAILLE_CASE, false, false);
                    gc.drawImage(monstre, i * TAILLE_CASE, j * TAILLE_CASE);
                }
            }
        }

        // Affiche l'image du joueur
        Image joueur = new Image(PJ, TAILLE_CASE, TAILLE_CASE, false, false);
        double px = laby.pj.getX();
        double py = laby.pj.getY();
        gc.drawImage(joueur, px * TAILLE_CASE, py * TAILLE_CASE);
    }
}
