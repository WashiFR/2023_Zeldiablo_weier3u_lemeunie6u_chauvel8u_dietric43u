package gameLaby.laby;

/**
 * classe monstre : représente un monstre situe initialement en x,y
 */
public class Monstre implements Entite {
    /**
     * position du monstre
     */
    private int x, y, pv;

    /**
     * constructeur
     *
     * @param dx position selon x
     * @param dy position selon y
     */
    public Monstre(int dx, int dy) {
        this.x = dx;
        this.y = dy;
        this.pv = 1;
    }

    public boolean etrePresent(int dx, int dy) {

        return (this.x == dx && this.y == dy);
    }

    /**
     * permet de faire attaquer le monstre
     *
     * @param p personnage a attaquer
     */
    public void attaquer(Perso p) {
        p.setPv(-1);
    }

    public void seDeplacer(int dx, int dy) {
        this.x = dx;
        this.y = dy;
    }

    // ############################################
    // GETTER
    // ############################################

    @Override
    public void setPv(int pv) {
        this.pv = pv + this.pv;
    }

    @Override
    public int getPv() {
        return this.pv;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
