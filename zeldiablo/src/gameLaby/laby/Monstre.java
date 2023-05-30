package gameLaby.laby;

/**
 * classe monstre : représente un monstre situe initialement en x,y
 */
public class Monstre {
    /**
     * position du monstre
     */
    int x,y;

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
}
