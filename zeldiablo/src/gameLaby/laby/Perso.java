package gameLaby.laby;

/**
 * gere un personnage situe en x,y
 */
public class Perso implements Entite{

    /**
     * position du personnage
     */
    private int x, y;

    /**
     * points de vie du personnage
     */
    private int pv;

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
    public void setPv(int pv) {
        this.pv = pv + this.pv;
    }

    @Override
    public int getPv() {
        return this.pv;
    }

    /**
     * permet de savoir si le personnage est en x,y
     *
     * @param dx position testee
     * @param dy position testee
     * @return true si le personnage est bien en (dx,dy)
     */
    public boolean etrePresent(int dx, int dy) {

        return (this.x == dx && this.y == dy);
    }

    /**
     * Attque l'entité si elle est proche du joueur
     * @param e entité
     */
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


    /**
     * Getter de la position du personnage selon x
     *
     * @return position x du personnage
     */
    public int getX() {
        // getter
        return this.x;
    }

    /**
     * Getter de la position du personnage selon y
     *
     * @return position y du personnage
     */
    public int getY() {
        //getter
        return this.y;
    }

    /**
     * Getter du nombre de points de vie du personnage
     *
     * @return nombre de points de vie du personnage
     */
    public int getPV() {
        return this.pv;
    }

    // ############################################
    // SETTER
    // ############################################

    /**
     * permet de changer le nombre de points de vie du personnage
     * (utile pour ne pas appeler directement l'attribut pv)
     *
     * @param ajout nombre de points de vie a ajouter
     */
    public void setPV(int ajout) {
        this.pv = this.pv + ajout;
    }


    public void seDeplacer(int dx, int dy) {
        this.x = dx;
        this.y = dy;
    }
}
