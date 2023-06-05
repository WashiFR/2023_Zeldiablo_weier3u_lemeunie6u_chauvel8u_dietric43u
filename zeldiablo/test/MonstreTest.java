import gameLaby.laby.Labyrinthe;
import gameLaby.laby.Monstre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test de la classe Monstre
 */
public class MonstreTest {

    /**
     * Attribut Labyrinthe
     */
    public Labyrinthe laby;
    Monstre monstre;

    /**
     * Méthode setUp qui initialise le labyrinthe
     */
    @BeforeEach
    public void setUp() throws IOException {
        laby = new Labyrinthe("labySimple/laby1.txt");
        monstre = laby.getMonstre().get(0);
    }

    /**
     * Test que le monstre est bien placé en (4,2) avec les getters
     */
    @Test
    public void test_00_Placement_avec_Getter() {
        assertEquals(4, monstre.getX(), "Le monstre aurait du être en x = 4");
        assertEquals(2, monstre.getY(), "Le monstre aurait du être en y = 2");
    }

    /**
     * Test que le monstre est bien placé en (4,2) avec etrePresent
     */
    @Test
    public void test_01_Placement_etre_present() {
        assertEquals(true, monstre.etrePresent(4, 2), "Le monstre aurait du être en (4,2)");
    }

    /**
     * Test que le monstre n'est pas placé en (6,4) avec etrePresent
     */
    @Test
    public void test_02_Placement_etre_present_faux() {
        assertEquals(false, laby.etreMonstre(6, 4), "Le monstre n'aurait pas du être en (6,4)");
    }

    /**
     * Test que le monstre est bien placé avec getMonstre (dans Labyrinthe)
     */
    @Test
    public void test_03_Placement_getMonstre() {
        assertEquals(true, laby.etreMonstre(4, 2), "Le monstre aurait du être en (4,2)");
    }

    /**
     * Test de la méthode deplacerMonstre (dans Labyrinthe) on vérifie que le monstre n'est plus en (4,2) car il a un déplacement aléatoire
     */
    @Test
    public void test_04_deplacerMonstre() {
        laby.deplacer(monstre, "aleatoire");
        assertEquals(false, laby.etreMonstre(4, 2), "Le monstre n'aurait pas du être en (4,2)");
    }

    /**
     * Test de la méthode deplacerMonstre (dans Labyrinthe) on vérifie que le monstre est autour de (4,2) car il a un déplacement aléatoire
     */
    @Test
    public void test_05_deplacerMonstre() {
        laby.deplacer(monstre, "aleatoire");
        assertEquals(true, laby.etreMonstre(3, 2) || laby.etreMonstre(5, 2) || laby.etreMonstre(4, 1) || laby.etreMonstre(4, 3), "Le monstre aurait du être autour de (4,2)");
    }

    /**
     * Test de la méthode attaquer qui vérifie que le personnage a bien perdu un point de vie
     */
    @Test
    public void test_06_attaquer() {
        monstre.attaquer(laby.getPJ());
        assertEquals(laby.getPJ().getPv(), 4, "Le personnage aurait du perdre un PV.");
    }

}