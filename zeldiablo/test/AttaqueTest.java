import gameLaby.laby.*;
import moteurJeu.Clavier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttaqueTest {


    /**
     * Attribut Labyrinthe
     */
    public Labyrinthe laby;
    /**
     * Attribut Troll
     */
    public Entite troll;
    /**
     * Attribut Monstre
     */
    public Entite monstre;

    /**
     * Méthode setUp qui initialise le labyrinthe
     */
    @BeforeEach
    public void setUp() throws IOException {
        laby = new Labyrinthe("labySimple/labyatt.txt");
        troll = laby.getTrolls().get(0);
        monstre = laby.getMonstre().get(0);
    }

    /**
     * Test de la méthode attaquer qui vérifie que le personnage a bien perdu un point de vie
     */
    @Test
    public void test_00_attaquer() {
        laby.getPJ().attaquer(troll);
        laby.getPJ().attaquer(monstre);
        assertEquals(troll.getPv(), 2, "Le troll aurait du perdre un point de vie.");
        assertEquals(monstre.getPv(), 0, "Le monstre aurait du perdre un point de vie.");
    }

}
