package gameLaby.laby;

public class Fantome implements  Entite{
    private int x,y;

    public Fantome(int dx, int dy) {
        this.x = dx;
        this.y = dy;
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
    public void attaquer(Perso p) {
        p.setPV(-1);
    }
}

