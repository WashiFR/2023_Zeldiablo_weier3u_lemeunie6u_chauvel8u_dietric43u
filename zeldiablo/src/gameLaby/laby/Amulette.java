package gameLaby.laby;

public class Amulette {

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

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

}
