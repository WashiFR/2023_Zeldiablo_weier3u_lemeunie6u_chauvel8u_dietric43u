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
        int x = 0;
        int y = 0;
        gc.setFill(Color.BLACK);
        Labyrinthe laby = labyJeu.getLaby();
        for (int i = 0; i<laby.getLength(); i++) {
            for (int j = 0; j<laby.getLengthY(); j++) {
                if (laby.getMur(i,j)) {
                    gc.fillRect(x, y, 20, 20);
                }
                x = x+20;
                if (x<=canvas.getWidth()) {
                    y = y+20;
                }
            }
        }

        //dessin joueur
        gc.setFill(Color.RED);
        double px = laby.pj.getX();
        double py = laby.pj.getY();
        gc.fillOval(px - 10, py - 10, 20, 20);
    }
}
