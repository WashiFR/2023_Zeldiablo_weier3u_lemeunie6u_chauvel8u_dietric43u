import static org.junit.jupiter.api.Assertions.assertEquals;

import gameLaby.laby.Labyrinthe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class monstreTest {

    public Labyrinthe laby;

    @BeforeEach
    public void setUp() throws IOException {
         laby = new Labyrinthe("labySimple/laby1.txt");
    }

    /**
     * Test que le monstre est bien placé en (4,2) avec les getters
     */
    @Test
    public void test_00_Placement_avec_Getter() {
        assertEquals(4, laby.monstre.getX());
        assertEquals(2, laby.monstre.getY());
    }

    /**
     * Test que le monstre est bien placé en (4,2) avec etrePresent
     */
    @Test
    public void test_01_Placement_etre_present() {
        assertEquals(true, laby.monstre.etrePresent(4, 2));
    }

    /**
     * Test que le monstre n'est pas placé en (6,4) avec etrePresent
     */
    @Test
    public void test_02_Placement_etre_present_faux() {
        assertEquals(false, laby.monstre.etrePresent(6, 4));
    }

    /**
     * Test que le monstre est bien placé avec getMonstre (dans Labyrinthe)
     */
    @Test
    public void test_03_Placement_getMonstre() {
        assertEquals(true, laby.getMonstre(4, 2));
    }

    /**
     * Test de la méthode deplacerMonstre (dans Labyrinthe) on vérifie que le monstre n'est plus en (4,2) car il a un déplacement aléatoire
     */
    @Test
    public void test_04_deplacerMonstre() {
        laby.deplacerMonstre();
        assertEquals(false, laby.getMonstre(4, 2));
    }

    /**
     * Test de la méthode deplacerMonstre (dans Labyrinthe) on vérifie que le monstre est autour de (4,2) car il a un déplacement aléatoire
     */
    @Test
    public void test_05_deplacerMonstre() {
        laby.deplacerMonstre();
        assertEquals(true, laby.getMonstre(3, 2) || laby.getMonstre(5, 2) || laby.getMonstre(4, 1) || laby.getMonstre(4, 3));
    }
}