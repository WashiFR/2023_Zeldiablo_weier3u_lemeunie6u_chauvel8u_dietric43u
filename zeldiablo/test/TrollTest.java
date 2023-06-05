import gameLaby.laby.Troll;
import gameLaby.laby.Labyrinthe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test de la classe Troll
 */
public class TrollTest {

    /**
     * Attribut Labyrinthe
     */
    public Labyrinthe laby;
    Troll troll;

    /**
     * Méthode setUp qui initialise le labyrinthe
     */
    @BeforeEach
    public void setUp() throws IOException {
        laby = new Labyrinthe("labySimple/labyatt.txt");
        troll = laby.getTrolls().get(0);
    }

    /**
     * Test que le troll est bien placé en (3,1) avec les getters
     */
    @Test
    public void test_00_Placement_avec_Getter() {
        assertEquals(3, troll.getX(), "Le troll aurait du être en x = 3");
        assertEquals(1, troll.getY(), "Le troll aurait du être en y = 1");
    }

    /**
     * Test que le troll est bien placé en (3,1) avec etrePresent
     */
    @Test
    public void test_01_Placement_etre_present() {
        assertEquals(true, troll.etrePresent(3, 1), "Le troll aurait du être en (3,1)");
    }

    /**
     * Test que le troll n'est pas placé en (3,1) avec etrePresent
     */
    @Test
    public void test_02_Placement_etre_present_faux() {
        assertEquals(false, laby.etreTroll(6, 2), "Le troll n'aurait pas du être en (6,2)");
    }

    /**
     * Test que le troll est bien placé avec getTroll (dans Labyrinthe)
     */
    @Test
    public void test_03_Placement_getTroll() {
        assertEquals(true, laby.etreTroll(3, 1), "Le troll aurait du être en (3,1)");
    }


    /**
     * Test de la méthode attaquer qui vérifie que le personnage a bien perdu un point de vie
     */
    @Test
    public void test_04_attaquer() {
        troll.attaquer(laby.getPJ());
        assertEquals(laby.getPJ().getPv(), 4, "Le personnage aurait du perdre un PV.");
    }

}