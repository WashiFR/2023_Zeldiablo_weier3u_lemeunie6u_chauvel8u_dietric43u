package gameLaby.laby;

public interface Entite {

    public void setPv(int pv);

    public int getPv();

    public boolean etrePresent(int dx, int dy);

    public void seDeplacer(int dx, int dy);

    public int getX();

    public int getY();


}
