package gameLaby.laby;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import moteurJeu.Jeu;
import moteurJeu.DessinJeu;

/**
 * permet d'afficher un jeu de type Labyrinthe
 */
public class LabyDessin implements DessinJeu {

    /**
     * Taille d'une case en pixel
     */
    private static final int TAILLE_CASE = 50;

    /**
     * affichage d'un jeu de type labyrinthe
     *
     * @param canvas dessin dans lequel on affiche le jeu
     */
    @Override
    public void dessinerJeu(Jeu jeu, Canvas canvas) {
        LabyJeu labyJeu = (LabyJeu) jeu;

        //recupere un pinceau pour dessiner
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        // dessin fond
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        //dessin mur
        Labyrinthe laby = labyJeu.getLaby();
        for (int i = 0; i<laby.getLength(); i++) {
            for (int j = 0; j<laby.getLengthY(); j++) {
                if (laby.getMur(i,j)) {
                    gc.setFill(Color.BLACK);
                    gc.fillRect(i * TAILLE_CASE, j * TAILLE_CASE, TAILLE_CASE, TAILLE_CASE);
                }
                else if (laby.getMonstre(i, j)) {
                    gc.setFill(Color.PURPLE);
                    gc.fillOval(i * TAILLE_CASE, j * TAILLE_CASE, TAILLE_CASE, TAILLE_CASE);
                }
            }
        }

        //dessin joueur
        gc.setFill(Color.RED);
        double px = laby.pj.getX();
        double py = laby.pj.getY();
        gc.fillOval(px * TAILLE_CASE, py * TAILLE_CASE, TAILLE_CASE, TAILLE_CASE);

        //dessin Amulette
        gc.setFill(Color.YELLOW);
        double ax = laby.amulette.getX();
        double ay = laby.amulette.getY();
        gc.fillOval(ax * TAILLE_CASE, ay * TAILLE_CASE, TAILLE_CASE, TAILLE_CASE);

    }
}
