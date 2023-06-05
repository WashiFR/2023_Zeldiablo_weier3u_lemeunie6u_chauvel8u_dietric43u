package gameLaby.laby;

/**
 * gere un personnage situe en x,y
 */
public class Perso implements Entite{

    /**
     * position du personnage et pv
     */
    private int x, y, pv;

    /**
     * constructeur
     *
     * @param dx position selon x
     * @param dy position selon y
     */
    public Perso(int dx, int dy) {
        this.x = dx;
        this.y = dy;
        this.pv = 5;
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
    public void attaquer(Entite e){
        if((e.getX() == this.getX() && e.getY() - 1 == this.getY()) ||
                (e.getX() == this.getX() && e.getY() + 1 == this.getY()) ||
                (e.getX() - 1 == this.getX() && e.getY() == this.getY()) ||
                (e.getX() + 1 == this.getX() && e.getY() == this.getY())){
            e.setPv(-1);
            System.out.println("Le joueur à attaqué " + e.getClass().getName());
            System.out.println(e.getPv());
        }
    }

    // ############################################
    // GETTER
    // ############################################

    @Override
    public int getX() {
        // getter
        return this.x;
    }

    @Override
    public int getY() {
        //getter
        return this.y;
    }

    @Override
    public int getPv() {
        return this.pv;
    }

    // ############################################
    // SETTER
    // ############################################

    @Override
    public void setPv(int pv) {
        this.pv = this.pv + pv;
    }

}
