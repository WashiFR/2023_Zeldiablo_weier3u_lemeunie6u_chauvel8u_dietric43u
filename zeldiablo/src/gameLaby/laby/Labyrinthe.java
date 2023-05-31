package gameLaby.laby;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * classe labyrinthe. represente un labyrinthe avec
 * <ul> des murs </ul>
 * <ul> un personnage (x,y) </ul>
 */
public class Labyrinthe {

    /**
     * Constantes char
     */
    public static final char MUR = 'X';
    public static final char PJ = 'P';
    public static final char VIDE = '.';

    // Constante ajoutée
    public static final char MONSTRE = 'M';

    public static final char AMULETTE = 'A';

    /**
     * constantes actions possibles
     */
    public static final String HAUT = "Haut";
    public static final String BAS = "Bas";
    public static final String GAUCHE = "Gauche";
    public static final String DROITE = "Droite";

    /**
     * attribut du personnage
     */
    public Perso pj;

    /**
     * les murs du labyrinthe
     */
    public boolean[][] murs;

    /**
     * le monstre du labyrinthe
     */
    public Monstre monstre;

    /**
     * l'amulette du labyrinthe
     */
    public Amulette amulette;
    /**
     * indique si l'amulette a été trouvée
     */
    boolean amuletteTrouvee = false;

    /**
     * case de départ
     */
    public int[] depart;

    /**
     * retourne la case suivante selon une actions
     *
     * @param x      case depart
     * @param y      case depart
     * @param action action effectuee
     * @return case suivante
     */
    static int[] getSuivant(int x, int y, String action) {
        switch (action) {
            case HAUT:
                // on monte une ligne
                y--;
                break;
            case BAS:
                // on descend une ligne
                y++;
                break;
            case DROITE:
                // on augmente colonne
                x++;
                break;
            case GAUCHE:
                // on augmente colonne
                x--;
                break;
            default:
                throw new Error("action inconnue");
        }
        int[] res = {x, y};
        return res;
    }

    /**
     * charge le labyrinthe
     *
     * @param nom nom du fichier de labyrinthe
     * @return labyrinthe cree
     * @throws IOException probleme a la lecture / ouverture
     */
    public Labyrinthe(String nom) throws IOException {
        // ouvrir fichier
        FileReader fichier = new FileReader(nom);
        BufferedReader bfRead = new BufferedReader(fichier);

        int nbLignes, nbColonnes;
        // lecture nblignes
        nbLignes = Integer.parseInt(bfRead.readLine());
        // lecture nbcolonnes
        nbColonnes = Integer.parseInt(bfRead.readLine());

        // creation labyrinthe vide
        this.murs = new boolean[nbColonnes][nbLignes];
        this.monstre = null;
        this.pj = null;

        // lecture des cases
        String ligne = bfRead.readLine();

        // stocke les indices courants
        int numeroLigne = 0;

        // parcours le fichier
        while (ligne != null) {

            // parcours de la ligne
            for (int colonne = 0; colonne < ligne.length(); colonne++) {
                char c = ligne.charAt(colonne);
                switch (c) {
                    case MUR:
                        this.murs[colonne][numeroLigne] = true;
                        break;
                    case VIDE:
                        this.murs[colonne][numeroLigne] = false;
                        break;
                    case PJ:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        // ajoute PJ
                        this.pj = new Perso(colonne, numeroLigne);
                        // case de depart
                        this.depart = new int[]{colonne, numeroLigne};
                        break;
                    case MONSTRE:
                        this.monstre = new Monstre(colonne, numeroLigne);
                        break;
                    case AMULETTE:
                        this.amulette = new Amulette(colonne, numeroLigne);
                        break;

                    default:
                        throw new Error("caractere inconnu " + c);
                }
            }

            // lecture
            ligne = bfRead.readLine();
            numeroLigne++;
        }

        // ferme fichier
        bfRead.close();
    }


    /**
     * deplace le personnage en fonction de l'action.
     * gere la collision avec les murs
     *
     * @param action une des actions possibles
     */
    public void deplacerPerso(String action) {
        // case courante
        int[] courante = {this.pj.x, this.pj.y};

        // calcule case suivante
        int[] suivante = getSuivant(courante[0], courante[1], action);

        // si c'est pas un mur ou un monstre, on effectue le deplacement
        if (!this.murs[suivante[0]][suivante[1]] && !getMonstre(suivante[0], suivante[1])) {
            // on met a jour personnage
            this.pj.x = suivante[0];
            this.pj.y = suivante[1];
        }
        if (!amuletteTrouvee && amulette.etrePresent(this.pj.x, this.pj.y)) {
            amuletteTrouvee = true;
            this.amulette = null;
        }


        // deplace le monstre avec une proba de 30%
        double rand = Math.random();
        if (rand <= 0.3)
            deplacerMonstre();
    }


    /**
     * Le jeu est fini si le personnage n'a plus de pv ou s'il a ramassé l'amulette et qu'il est revenu a la case de départ
     *
     * @return fin du jeu
     */
    public boolean etreFini() {
        return amuletteTrouvee && this.pj.x == this.depart[0] && this.pj.y == this.depart[1];
    }

    public boolean etrePerdu() {
        return this.pj.getPV() <= 0;
    }

    // ##################################
    // GETTER
    // ##################################

    /**
     * return taille selon Y
     *
     * @return
     */
    public int getLengthY() {
        return murs[0].length;
    }

    /**
     * return taille selon X
     *
     * @return
     */
    public int getLength() {
        return murs.length;
    }

    /**
     * return mur en (i,j)
     *
     * @param x
     * @param y
     * @return
     */
    public boolean getMur(int x, int y) {
        // utilise le tableau de boolean
        return this.murs[x][y];
    }

    /**
     * deplace le monstre aléatoirement
     * gere la collision avec les murs et avec le personnage
     */
    public void deplacerMonstre() {
        // case courante
        int[] courante = {this.monstre.x, this.monstre.y};

        // choix aléatoire de l'action
        int choix = (int) (Math.random() * 4);
        String action = "";

        switch (choix) {
            case 0:
                action = HAUT;
                break;
            case 1:
                action = BAS;
                break;
            case 2:
                action = DROITE;
                break;
            case 3:
                action = GAUCHE;
                break;
        }

        // calcule case suivante
        int[] suivante = getSuivant(courante[0], courante[1], action);

        // si c'est pas un mur ou le joueur, on effectue le deplacement
        if (!this.murs[suivante[0]][suivante[1]] && !this.pj.etrePresent(suivante[0], suivante[1])) {
            // on met a jour le monstre
            this.monstre.x = suivante[0];
            this.monstre.y = suivante[1];
        }
    }

    /**
     * @param x
     * @param y
     * @return
     */
    public boolean getMonstre(int x, int y) {
        return this.monstre.etrePresent(x, y);
    }
}
