package gameLaby.laby;

/**
 * interface des entitées
 */
public interface Entite {

    /**
     * Modifie les pv
     * @param pv nombre de pv a ajouter
     */
    public void setPv(int pv);

    /**
     * Récupère les pv
     * @return pv
     */
    public int getPv();

    /**
     * Retourne true si l'entité est présente au co indiqués
     * @param dx co X
     * @param dy co Y
     * @return
     */
    public boolean etrePresent(int dx, int dy);

    /**
     * Deplace l'entité
     * @param dx co X
     * @param dy co Y
     */
    public void seDeplacer(int dx, int dy);

    /**
     * Récupère les co X
     * @return
     */
    public int getX();

    /**
     * Récupère lex co Y
     * @return
     */
    public int getY();
}
