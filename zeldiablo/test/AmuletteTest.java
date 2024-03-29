import gameLaby.laby.Labyrinthe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test de la classe Amulette
 */
public class AmuletteTest {

    /**
     * Attribut Labyrinthe
     */
    private Labyrinthe laby;

    /**
     * Méthode setUp qui initialise le labyrinthe
     */
    @BeforeEach
    public void setUp() throws IOException {
        laby = new Labyrinthe("labySimple/laby1.txt");
    }

    /**
     * Test que l'amulette est bien placée en (2,1) avec les getters
     */
    @Test
    public void test_00_Placement_Amulette_Getter() {
        assertEquals(2, laby.getAmulette().getX(), "L'amulette aurait du être en x = 2");
        assertEquals(1, laby.getAmulette().getY(), "L'amulette aurait du être en y = 1");
    }
    
    /**
     * Test que l'amulette est bien placée en (2,1) avec etrePresent
     */
    @Test
    public void test_01_Placement_etre_present() {
        assertEquals(true, laby.getAmulette().etrePresent(2, 1), "L'amulette aurait du être en (2,1)");
    }

    /**
     * Test que l'amulette n'est pas placée en (6,4) avec etrePresent
     */
    @Test
    public void test_02_Placement_etre_present_faux() {
        assertEquals(false, laby.getAmulette().etrePresent(6, 4), "L'amulette n'aurait pas du être en (6,4)");
    }



}
