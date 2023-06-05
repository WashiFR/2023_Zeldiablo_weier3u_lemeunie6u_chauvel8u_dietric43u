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

    /**
     * Chemin vers les images
     */
    public static final String PATH = "file:./src/gameLaby/img/";

    /**
     * Constantes pour les images
     */
    public static final String MUR = PATH + "wall.png";

    public static final String PJ = PATH + "player.png";

    public static final String MONSTRE = PATH + "monster.png";

    public static final String SOL = PATH + "floor.png";

    public static final String AMULETTE = PATH + "amulet.png";

    public static final String DEPART = PATH + "start.png";

    public static final String FANTOME = PATH + "fantome.png";

    public static final String TROLL = PATH + "troll.png";

    private static final int TAILLE_CASE = 40;

    public void dessinerJeu(Jeu jeu, Canvas canvas) {
        LabyJeu labyJeu = (LabyJeu) jeu;

        final GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        Labyrinthe laby = labyJeu.getLaby();
        for (int i = 0; i < laby.getLength(); i++) {
            for (int j = 0; j < laby.getLengthY(); j++) {
                Image sol = new Image(SOL, TAILLE_CASE, TAILLE_CASE, false, false);
                gc.drawImage(sol, i * TAILLE_CASE, j * TAILLE_CASE);
                if (laby.getMur(i, j)) {
                    // Affiche l'image du mur
                    Image mur = new Image(MUR, TAILLE_CASE, TAILLE_CASE, false, false);
                    gc.drawImage(mur, i * TAILLE_CASE, j * TAILLE_CASE);
                }
                if (laby.etreMonstre(i, j)) {
                    // Affiche l'image du monstre
                    Image monstre = new Image(MONSTRE, TAILLE_CASE - TAILLE_CASE * 0.20, TAILLE_CASE, false, false);
                    gc.drawImage(monstre, i * TAILLE_CASE + TAILLE_CASE * 0.05, j * TAILLE_CASE - TAILLE_CASE * 0.05);
                }
                if (laby.etreTroll(i, j)) {
                    // Affiche l'image du troll
                    Image troll = new Image(TROLL, TAILLE_CASE - TAILLE_CASE * 0.20, TAILLE_CASE, false, false);
                    gc.drawImage(troll, i * TAILLE_CASE + TAILLE_CASE * 0.05, j * TAILLE_CASE - TAILLE_CASE * 0.05);
                }
                if (laby.etreFantome(i, j)) {
                    // Affiche l'image du fantome
                    Image fantome = new Image(FANTOME, TAILLE_CASE - TAILLE_CASE * 0.40, TAILLE_CASE, false, false);
                    gc.drawImage(fantome, i * TAILLE_CASE + TAILLE_CASE * 0.20, j * TAILLE_CASE - TAILLE_CASE * 0.20);
                }
                if (laby.getDepart()[0] == i && laby.getDepart()[1] == j){
                    // Affiche l'image du depart
                    Image depart = new Image(DEPART, TAILLE_CASE, TAILLE_CASE, false, false);
                    gc.drawImage(depart, i * TAILLE_CASE, j * TAILLE_CASE);
                }
            }
        }

        // Affichage amulette
        if (laby.getAmulette() != null) {
            Image amulette = new Image(AMULETTE, TAILLE_CASE / 2, TAILLE_CASE / 2, false, false);
            double ax = laby.getAmulette().getX();
            double ay = laby.getAmulette().getY();
            gc.drawImage(amulette, ax * TAILLE_CASE + TAILLE_CASE / 4, ay * TAILLE_CASE + TAILLE_CASE / 4);
        }

        // Affiche l'image du joueur
        Image joueur = new Image(PJ, TAILLE_CASE - TAILLE_CASE * 0.40, TAILLE_CASE, false, false);
        double px = laby.getPJ().getX();
        double py = laby.getPJ().getY();
        gc.drawImage(joueur, px * TAILLE_CASE + TAILLE_CASE * 0.20, py * TAILLE_CASE - TAILLE_CASE * 0.20);
    }
}
