import gameLaby.laby.Fantome;
import gameLaby.laby.Labyrinthe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test de la classe Fantome
 */
public class FantomeTest {

    /**
     * Attribut Labyrinthe
     */
    public Labyrinthe laby;
    Fantome fantome;

    /**
     * Méthode setUp qui initialise le labyrinthe
     */
    @BeforeEach
    public void setUp() throws IOException {
        laby = new Labyrinthe("labySimple/laby1.txt");
        fantome = laby.getFantome().get(0);
    }

    /**
     * Test que le fantome est bien placé en (4,4) avec les getters
     */
    @Test
    public void test_00_Placement_avec_Getter() {
        System.out.println(fantome.getX() + " " + fantome.getY());
        assertEquals(4, fantome.getX(), "Le fantome aurait du être en x = 4");
        assertEquals(4, fantome.getY(), "Le fantome aurait du être en y = 4");
    }

    /**
     * Test que le fantome est bien placé en (4,4) avec etrePresent
     */
    @Test
    public void test_01_Placement_etre_present() {
        assertEquals(true, fantome.etrePresent(4, 4), "Le fantome aurait du être en (4,4)");
    }

    /**
     * Test que le fantome n'est pas placé en (4,4) avec etrePresent
     */
    @Test
    public void test_02_Placement_etre_present_faux() {
        assertEquals(false, laby.etreFantome(6, 2), "Le fantome n'aurait pas du être en (6,2)");
    }

    /**
     * Test que le fantome est bien placé avec getFantome (dans Labyrinthe)
     */
    @Test
    public void test_03_Placement_getFantome() {
        assertEquals(true, laby.etreFantome(4, 4), "Le fantome aurait du être en (4,4)");
    }

    /**
     * Test de la méthode deplacer (dans Labyrinthe) on vérifie que le fantome n'est plus en (4,4) car il a un déplacement aléatoire
     */
    @Test
    public void test_04_deplacerFantome() {
        laby.deplacer(fantome, "aleatoire");
        assertEquals(false, laby.etreFantome(4, 4), "Le fantome aurait du se déplacer autour de (4,4)");
    }

    /**
     * Test de la méthode deplacer (dans Labyrinthe) on vérifie que le fantome est autour de (4,4) car il a un déplacement aléatoire
     */
    @Test
    public void test_05_deplacerFantome() {
        laby.deplacer(fantome, "aleatoire");
        assertEquals(true, laby.etreFantome(3, 4) || laby.etreFantome(5, 4) || laby.etreFantome(4, 3) || laby.etreFantome(4, 5), "Le fantome aurait du se déplacer autour de (4,4)");
    }

    /**
     * Test de la méthode attaquer qui vérifie que le personnage a bien perdu un point de vie
     */
    @Test
    public void test_06_attaquer() {
        fantome.attaquer(laby.getPJ());
        assertEquals(laby.getPJ().getPV(), 4, "Le personnage aurait du perdre un PV.");
    }

}