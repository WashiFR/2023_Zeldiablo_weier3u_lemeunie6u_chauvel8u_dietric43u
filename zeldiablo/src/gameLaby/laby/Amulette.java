package gameLaby.laby;

/**
 * gere une amulette situee en x,y
 */
public class Amulette {

    /**
     * position de l'amulette
     */
    int x, y;


    /**
     * constructeur
     *
     * @param x position selon x
     * @param y position selon y
     */
    public Amulette(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * permet de savoir si l'amulette est en x,y
     *
     * @param dx position testee
     * @param dy position testee
     * @return true si l'amulette est bien en (dx,dy)
     */
    public boolean etrePresent(int dx, int dy) {
        return (this.x == dx && this.y == dy);
    }

    /**
     * Getter de la position de l'amulette selon x
     *
     * @return position x de l'amulette
     */
    public int getX() {
        return this.x;
    }

    /**
     * Getter de la position de l'amulette selon y
     *
     * @return position y de l'amulette
     */
    public int getY() {
        return this.y;
    }

}
