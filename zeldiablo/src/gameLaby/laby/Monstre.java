package gameLaby.laby;

/**
 * classe monstre : représente un monstre situe initialement en x,y
 */
public class Monstre implements Entite {
    /**
     * position du monstre et pv
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
    @Override
    public boolean etrePresent(int dx, int dy) {

        return (this.x == dx && this.y == dy);
    }

    @Override
    public void attaquer(Entite p) {
        p.setPv(-1);
    }

    @Override
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

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    /**
     * setter de x
     *
     * @param x position selon x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * setter de y
     *
     * @param y position selon y
     */
    public void setY(int y) {
        this.y = y;
    }
}
