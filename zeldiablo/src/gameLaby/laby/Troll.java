package gameLaby.laby;

/**
 * classe troll : représente un troll situe initialement en x,y
 */
public class Troll implements Entite{

    /**
     * position du troll et pv
     */
    private int x, y, pv;

    /**
     * constructeur
     *
     * @param dx position selon x
     * @param dy position selon y
     */
    public Troll(int dx, int dy) {
        this.x = dx;
        this.y = dy;
        this.pv = 3;
    }

    @Override
    public void setPv(int pv) {
        this.pv += pv;
        if(this.pv > 3){
            this.pv = 3;
        }
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
    public void attaquer(Entite p) {
        p.setPv(-1);
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}