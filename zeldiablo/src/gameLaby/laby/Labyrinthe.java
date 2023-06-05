package gameLaby.laby;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * classe labyrinthe represente un labyrinthe avec
 * <ul> des murs </ul>
 * <ul> un personnage (x,y) </ul>
 * <ul> des monstres (x,y) </ul>
 * <ul> des fantomes (x,y) </ul>
 * <ul> des trolls (x,y) </ul>
 * <ul> une amulette (x,y) </ul>
 *
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
    public static final char FANTOME = 'F';
    public static final char TROLL = 'T';
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
    private Perso pj;
    /**
     * les murs du labyrinthe
     */
    private boolean[][] murs;
    /**
     * les monstres du labyrinthe
     */
    private ArrayList<Monstre> monstres;
    /**
     * les fantomes du labyrinthe
     */
    private ArrayList<Fantome> fantomes;
    /**
     * les trolls du labyrinthe
     */
    private ArrayList<Troll> trolls;
    /**
     * toutes les entites du jeu
     */
    private ArrayList<Entite> entites;
    /**
     * l'amulette du labyrinthe
     */
    private Amulette amulette;
    /**
     * indique si l'amulette a été trouvée
     */
    private boolean amuletteTrouvee = false;
    /**
     * case de départ
     */
    private int[] depart;
    /**
     * retourne la case suivante selon une actions
     * @param x case depart
     * @param y case depart
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
        this.monstres = new ArrayList<Monstre>();
        this.pj = null;
        this.fantomes = new ArrayList<Fantome>();
        this.trolls = new ArrayList<Troll>();
        this.entites = new ArrayList<Entite>();

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
                        Monstre m = new Monstre(colonne, numeroLigne);
                        this.monstres.add(m);
                        this.entites.add(m);
                        break;
                    case AMULETTE:
                        this.amulette = new Amulette(colonne, numeroLigne);
                        break;
                    case FANTOME:
                        Fantome f = new Fantome(colonne, numeroLigne);
                        this.fantomes.add(f);
                        this.entites.add(f);
                        break;
                    case TROLL:
                        Troll t = new Troll(colonne, numeroLigne);
                        this.trolls.add(t);
                        this.entites.add(t);
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
     * Le jeu est fini si le joueur a ramassé l'amulette et qu'il est revenu a la case de départ
     * @return fin du jeu
     */
    public boolean etreFini() {
        return amuletteTrouvee && this.pj.getX() == this.depart[0] && this.pj.getY() == this.depart[1];
    }
    /**
     * Le jeu est perdu si le personnage n'a plus de pv
     * @return jeu perdu
     */
    public boolean etrePerdu() {
        return this.pj.getPv() <= 0;
    }

    // ##################################
    // GETTER
    // ##################################
    /**
     * return taille selon Y
     * @return taille selon Y
     */
    public int getLengthY() {
        return murs[0].length;
    }
    /**
     * return taille selon X
     * @return taille selon X
     */
    public int getLength() {
        return murs.length;
    }
    /**
     * return mur en (i,j)
     * @param x colonne
     * @param y ligne
     * @return mur en (i,j)
     */
    public boolean getMur(int x, int y) {
        // utilise le tableau de boolean
        return this.murs[x][y];
    }
    /**
     * return la liste des monstres
     * @return liste des monstres
     */
    public ArrayList<Monstre> getMonstre() {
        return monstres;
    }
    /**
     * return la liste des fantomes
     * @return liste des fantomes
     */
    public ArrayList<Fantome> getFantome() {
        return fantomes;
    }
    /**
     * return la liste des trolls
     * @return liste des trolls
     */
    public ArrayList<Troll> getTrolls() {
        return trolls;
    }
    /**
     * return liste des entites du jeu
     * @return
     */
    public ArrayList<Entite> getEntites() {
        return entites;
    }
    /**
     * return le personnage
     * @return personnage
     */
    public Perso getPJ() {
        return pj;
    }
    /**
     * return l'amulette
     * @return amulette
     */
    public Amulette getAmulette() {
        return amulette;
    }
    /**
     * return la case de depart
     * @return case de depart
     */
    public int[] getDepart() {
        return depart;
    }
    /**
     * return true si un monstre est en (x,y)
     *
     * @param x coordonnee x
     * @param y coordonnee y
     * @return monstre en (x,y)
     */
    public boolean etreMonstre(int x, int y) {
        boolean present = false;
        for (Monstre m : monstres) {
            if (m.etrePresent(x, y))
                present = true;
        }
        return present;
    }
    /**
     * return true si un fantome est en (x,y)
     *
     * @param x coordonnee x
     * @param y coordonnee y
     * @return fantome en (x,y)
     */
    public boolean etreFantome(int x, int y) {
        boolean present = false;
        for (Fantome f : fantomes) {
            if (f.etrePresent(x, y))
                present = true;
        }
        return present;
    }
    /**
     * return true si un troll est en (x,y)
     *
     * @param x coordonnee x
     * @param y coordonnee y
     * @return troll en (x,y)
     */
    public boolean etreTroll(int x, int y) {
        boolean present = false;
        for (Troll t : trolls) {
            if (t.etrePresent(x, y))
                present = true;
        }
        return present;
    }
    /**
     * return true si un mechant est en (x,y)
     *
     * @param x coordonnee x
     * @param y coordonnee y
     * @return mechant en (x,y)
     */
    public boolean etreMechant(int x, int y) {
        return etreMonstre(x, y) || etreFantome(x, y) || etreTroll(x, y);
    }

    /**
     * Méthode deplacer qui permet de déplacer une entité
     * @param e entite a deplacer
     * @param action action a effectuer
     */
    public void deplacer(Entite e, String action) {
        // case courante
        int[] courante = {e.getX(), e.getY()};

        // si l'action est aleatoire, on la genere
        if (action.equals("aleatoire")) {
            action = actionAleatoire();
        }
        // case suivante
        int[] suivante = getSuivant(courante[0], courante[1], action);

        if (e instanceof Monstre) {
            // si c'est pas un mur ou le joueur ou un mechant, on effectue le deplacement
            if (!this.murs[suivante[0]][suivante[1]] && !this.pj.etrePresent(suivante[0], suivante[1]) && !etreMechant(suivante[0], suivante[1])) {
                // on met a jour le monstre
                e.seDeplacer(suivante[0], suivante[1]);
            }
        } else if (e instanceof Troll) {
            // si c'est pas un mur ou le joueur ou un mechant, on effectue le deplacement
            if (!this.murs[suivante[0]][suivante[1]] && !this.pj.etrePresent(suivante[0], suivante[1]) && !etreMechant(suivante[0], suivante[1])) {
                // on met a jour le monstre
                e.seDeplacer(suivante[0], suivante[1]);
            }
        } else if (e instanceof Fantome) {
            // si c'est pas un mechant ou le joueur, on effectue le deplacement
            if (!this.pj.etrePresent(suivante[0], suivante[1]) && !etreMechant(suivante[0], suivante[1]) && suivante[0] >= 0 && suivante[1] >= 0 && suivante[0] < getLength() && suivante[1] < getLengthY()) {
                // on met a jour le monstre
                e.seDeplacer(suivante[0], suivante[1]);
            }
        } else if (e instanceof Perso) {
            // si c'est pas un mur ou un mechant, on effectue le deplacement
            if (!this.murs[suivante[0]][suivante[1]] && !etreMechant(suivante[0], suivante[1])) {
                // on met a jour personnage
                this.pj.seDeplacer(suivante[0], suivante[1]);
                for (Troll tr : this.trolls){
                    tr.setPv(tr.getPv()+1);
                }
            }
            if (!amuletteTrouvee && amulette.etrePresent(this.pj.getX(), this.pj.getY())) {
                amuletteTrouvee = true;
                this.amulette = null;
            }

            //attaque du troll si un personnage est a cote
            if (etreTroll(this.pj.getX(), this.pj.getY() - 1) ||
                    etreTroll(this.pj.getX(), this.pj.getY() + 1) ||
                    etreTroll(this.pj.getX() - 1, this.pj.getY()) ||
                    etreTroll(this.pj.getX() + 1, this.pj.getY())) {
                this.trolls.get(0).attaquer(this.pj);
                System.out.println("Troll attaque");
            }
            // attaque du monstre si un personnage est a cote
            if (etreMonstre(this.pj.getX(), this.pj.getY() - 1) ||
                    etreMonstre(this.pj.getX(), this.pj.getY() + 1) ||
                    etreMonstre(this.pj.getX() - 1, this.pj.getY()) ||
                    etreMonstre(this.pj.getX() + 1, this.pj.getY())) {
                this.monstres.get(0).attaquer(this.pj);
                System.out.println("Monstre attaque");
            }
            // attaque du fantome si un personnage est a cote
            if (etreFantome(this.pj.getX(), this.pj.getY() - 1) ||
                    etreFantome(this.pj.getX(), this.pj.getY() + 1) ||
                    etreFantome(this.pj.getX() - 1, this.pj.getY()) ||
                    etreFantome(this.pj.getX() + 1, this.pj.getY())) {
                this.fantomes.get(0).attaquer(this.pj);
                System.out.println("Fantome attaque");

            }
        }
    }

    /**
     * Permet de generer une action aleatoire
     * @return action aleatoire
     */
    public String actionAleatoire() {
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
        return action;
    }

}

