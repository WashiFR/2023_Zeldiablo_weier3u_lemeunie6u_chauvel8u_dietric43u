package gameLaby.laby;

/**
 * classe monstre : représente un monstre situe initialement en x,y
 */
public class Monstre implements Entite {
    /**
     * position du monstre
     */
    private int x, y;

    /**
     * constructeur
     *
     * @param dx position selon x
     * @param dy position selon y
     */
    public Monstre(int dx, int dy) {
        this.x = dx;
        this.y = dy;
    }

    /**
     * permet de savoir si le monstre est en x,y
     *
     * @param dx position testee
     * @param dy position testee
     * @return true si le monstre est bien en (dx,dy)
     */
    public boolean etrePresent(int dx, int dy) {

        return (this.x == dx && this.y == dy);
    }

    // ############################################
    // GETTER
    // ############################################


    /**
     * Getter de la position du monstre selon x
     *
     * @return position x du monstre
     */
    public int getX() {
        return this.x;
    }

    /**
     * Getter de la position du monstre selon y
     *
     * @return position y du monstre
     */
    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * permet de faire attaquer le monstre
     *
     * @param p personnage a attaquer
     */
    public void attaquer(Perso p) {
        p.setPV(-1);
    }

    /**
     * permet de faire se deplacer le monstre
     *
     * @param dx deplacement selon x
     * @param dy deplacement selon y
     */
    public void seDeplacer(int dx, int dy) {
        this.x = dx;
        this.y = dy;
    }
}
