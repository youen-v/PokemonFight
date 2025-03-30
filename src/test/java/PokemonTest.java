import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class PokemonTest {
    @Test
    void testCreationPokemon() {
        ArrayList<Attaque> attaques = new ArrayList<>();
        Pokemon pikachu = new Pokemon("Pikachu", new TypePokemon(), new PointDeVieStat(), attaques, new Niveau(), new DefenseStat(), new AttaqueStat());

        assertEquals("Pikachu", pikachu.getNom());
        assertNotNull(pikachu.getListeAttaque());
    }

    @Test
    void testSetPv() {
        Pokemon pikachu = new Pokemon("Pikachu", new TypePokemon(), new PointDeVieStat(), new ArrayList<>(), new Niveau(), new DefenseStat(), new AttaqueStat());

        pikachu.setPv(50);
        assertEquals(50, pikachu.getPv());

        pikachu.setPv(-10); // Ne doit pas être négatif
        assertEquals(0, pikachu.getPv());
    }

    @Test
    void testEstKo() {
        Pokemon pikachu = new Pokemon("Pikachu", new TypePokemon(), new PointDeVieStat(), new ArrayList<>(), new Niveau(), new DefenseStat(), new AttaqueStat());

        assertFalse(pikachu.estKo());

        pikachu.setPv(0);
        assertTrue(pikachu.estKo());
    }

    @Test
    void testSetCompteurTour() {
        Pokemon pikachu = new Pokemon("Pikachu", new TypePokemon(), new PointDeVieStat(), new ArrayList<>(), new Niveau(), new DefenseStat(), new AttaqueStat());

        pikachu.setCompteurTour(1);
        pikachu.setCompteurTour(2);

        assertEquals(2, pikachu.getCompteurTour().size());
        assertTrue(pikachu.getCompteurTour().contains(1));
        assertTrue(pikachu.getCompteurTour().contains(2));
    }

    @Test
    void testSetAttaqueSelectionner() {
        Pokemon pikachu = new Pokemon("Pikachu", new TypePokemon(), new PointDeVieStat(), new ArrayList<>(), new Niveau(), new DefenseStat(), new AttaqueStat());

        Attaque tonnerre = new Attaque("Tonnerre", 40, "Electrique", 2);
        pikachu.setAttaqueSelectionner(tonnerre);

        assertEquals(tonnerre, pikachu.getAttaqueSelectionner());
    }

    @Test
    void testToString() {
        Pokemon pikachu = new Pokemon("Pikachu", new TypePokemon(), new PointDeVieStat(), new ArrayList<>(), new Niveau(), new DefenseStat(), new AttaqueStat());
        assertEquals("Pikachu PV : 100", pikachu.toString());
    }
}
