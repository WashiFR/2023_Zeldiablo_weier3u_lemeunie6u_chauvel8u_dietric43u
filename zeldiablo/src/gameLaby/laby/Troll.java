package gameLaby.laby;

public class Troll implements Entite{
    private int x,y,vie;
    public Troll(int dx, int dy) {
        this.x = dx;
        this.y = dy;
        this.vie = 3;
    }

    @Override
    public void setPv(int pv) {
        this.vie = pv + this.vie ;
        if(this.vie > 3){
            this.vie = 3;
        }
    }

    @Override
    public int getPv() {
        return this.vie;
    }

    @Override
    public boolean etrePresent(int dx, int dy) {
        return (this.x == dx && this.y == dy);
    }

    public void attaquer(Perso p) {
        p.setPv(-1);
    }
    @Override
    public void seDeplacer(int dx, int dy) {
        this.x = dx;
        this.y = dy;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }
}