import gameLaby.laby.Labyrinthe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class AmuletteTest {

    private Labyrinthe laby;

    @BeforeEach
    public void setUp() throws IOException {
         laby = new Labyrinthe("labySimple/laby1.txt");
    }

    /**
     * Test que l'amulette est bien placée en (2,1) avec les getters
     */
    @Test
    public void test_00_Placement_Amulette_Getter() {
        assertEquals(2, laby.amulette.getX());
        assertEquals(1, laby.amulette.getY());
    }

    /**
     * Test que l'amulette est bien placée en (2,1) avec etrePresent
     */
    @Test
    public void test_01_Placement_etre_present() {
        assertEquals(true, laby.amulette.etrePresent(2, 1));
    }

    /**
     * Test que l'amulette n'est pas placée en (6,4) avec etrePresent
     */
    @Test
    public void test_02_Placement_etre_present_faux() {
        assertEquals(false, laby.amulette.etrePresent(6, 4));
    }

    /**
     * Test de la méthode etreFini
     */
    @Test
    public void test_03_etreFini() throws IOException {
        laby = new Labyrinthe("labySimple/laby0.txt");
        laby.deplacerPerso(Labyrinthe.HAUT);
        laby.deplacerPerso(Labyrinthe.BAS);
        assertEquals(true, laby.etreFini());

    }

}
