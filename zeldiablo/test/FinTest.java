import gameLaby.laby.Fantome;
import gameLaby.laby.Labyrinthe;
import gameLaby.laby.MainLaby;
import gameLaby.laby.Monstre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FinTest {


    private Labyrinthe laby;
    private Fantome fantome;
    private Monstre monstre;

    @BeforeEach
    public void setUp() throws IOException {
        laby = new Labyrinthe("labySimple/labytestfin.txt");
        fantome = laby.getFantome().get(0);
        monstre = laby.getMonstre().get(0);
    }

    // On déplace le PJ sur l'amulette, il retourne à la case départ et on vérifie que le jeu est fini
    @Test
    public void test_00_Fin() {
        laby.deplacer(laby.getPJ(), Labyrinthe.GAUCHE);
        laby.deplacer(laby.getPJ(), Labyrinthe.DROITE);
        assertEquals(true, laby.etreFini(), "Le jeu aurait du être fini.");
    }

    /**
     * Test de la méthode etrePerdu qui vérifie que le personnage a bien perdu avec des dégâts du fantome
     */
    @Test
    public void test_01_etrePerdu_fantome() {
        fantome.attaquer(laby.getPJ());
        fantome.attaquer(laby.getPJ());
        fantome.attaquer(laby.getPJ());
        fantome.attaquer(laby.getPJ());
        fantome.attaquer(laby.getPJ());
        assertEquals(true, laby.etrePerdu(), "Le personnage aurait du perdre.");
    }

    /**
     * Test de la méthode etrePerdu qui vérifie que le personnage a bien perdu avec des dégâts du monstre
     */
    @Test
    public void test_02_etrePerdu_monstre() {
        monstre.attaquer(laby.getPJ());
        monstre.attaquer(laby.getPJ());
        monstre.attaquer(laby.getPJ());
        monstre.attaquer(laby.getPJ());
        monstre.attaquer(laby.getPJ());
        assertEquals(true, laby.etrePerdu(), "Le personnage aurait du perdre.");
    }

}
