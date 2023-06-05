package gameLaby.laby;

/**
 * Fantome
 */
public class Fantome implements Entite{

    /**
     * attributs position et pv
     */
    private int x, y, pv;

    /**
     * constructeur
     * @param dx co X
     * @param dy co Y
     */
    public Fantome(int dx, int dy) {
        this.x = dx;
        this.y = dy;
        this.pv = 1;
    }

    @Override
    public void setPv(int pv) {
        this.pv = pv + this.pv;
    }

    @Override
    public int getPv() {
        return this.pv;
    }

    @Override
    public boolean etrePresent(int dx, int dy) {
        return (this.x == dx && this.y == dy);
    }

    @Override
    public void seDeplacer(int dx, int dy) {
        this.x = dx;
        this.y = dy;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public void attaquer(Entite p) {
        p.setPv(-1);
    }
}

