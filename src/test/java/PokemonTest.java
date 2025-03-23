import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class PokemonTest {
    @Test
    void testCreationPokemon() {
        ArrayList<Attaque> attaques = new ArrayList<>();
        Pokemon pikachu = new Pokemon("Pikachu", new TypePokemon(), 100, attaques, new Niveau(), 10);

        assertEquals("Pikachu", pikachu.getNom());
        assertEquals(100, pikachu.getPv());
        assertEquals(10, pikachu.getDefense());
        assertNotNull(pikachu.getListeAttaque());
    }

    @Test
    void testSetPv() {
        Pokemon pikachu = new Pokemon("Pikachu", new TypePokemon(), 100, new ArrayList<>(), new Niveau(), 10);

        pikachu.setPv(50);
        assertEquals(50, pikachu.getPv());

        pikachu.setPv(-10); // Ne doit pas être négatif
        assertEquals(0, pikachu.getPv());
    }

    @Test
    void testEstKo() {
        Pokemon pikachu = new Pokemon("Pikachu", new TypePokemon(), 100, new ArrayList<>(), new Niveau(), 10);

        assertFalse(pikachu.estKo());

        pikachu.setPv(0);
        assertTrue(pikachu.estKo());
    }

    @Test
    void testSetCompteurTour() {
        Pokemon pikachu = new Pokemon("Pikachu", new TypePokemon(), 100, new ArrayList<>(), new Niveau(), 10);

        pikachu.setCompteurTour(1);
        pikachu.setCompteurTour(2);

        assertEquals(2, pikachu.getCompteurTour().size());
        assertTrue(pikachu.getCompteurTour().contains(1));
        assertTrue(pikachu.getCompteurTour().contains(2));
    }

    @Test
    void testSetAttaqueSelectionner() {
        Pokemon pikachu = new Pokemon("Pikachu", new TypePokemon(), 100, new ArrayList<>(), new Niveau(), 10);

        Attaque tonnerre = new Attaque("Tonnerre", 40, "Electrique", 2);
        pikachu.setAttaqueSelectionner(tonnerre);

        assertEquals(tonnerre, pikachu.getAttaqueSelectionner());
    }

    @Test
    void testToString() {
        Pokemon pikachu = new Pokemon("Pikachu", new TypePokemon(), 100, new ArrayList<>(), new Niveau(), 10);
        assertEquals("Pikachu PV : 100", pikachu.toString());
    }
}
