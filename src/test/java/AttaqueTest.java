import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AttaqueTest {
    @Test
    void testCreationAttaque() {
        Attaque tonnerre = new Attaque("Tonnerre", 40, "Electrique", 2);
        assertEquals("Tonnerre", tonnerre.getNom());
        assertEquals(40, tonnerre.getDegat());
        assertEquals("Electrique", tonnerre.getType());
        assertEquals(2, tonnerre.getUtilisation());
        assertNotNull(tonnerre.getNom());
        assertNotNull(tonnerre.getType());
    }

    @Test
    void testSetUtilisation() {
        Attaque tonnerre = new Attaque("Tonnerre", 40, "Electrique", 2);
        tonnerre.utilisationAttaque(tonnerre.getUtilisation());
        assertEquals(1, tonnerre.getUtilisation());
    }

    @Test
    void testToString() {
        Attaque tonnerre = new Attaque("Tonnerre", 40, "Electrique", 2);
        assertEquals("Tonnerre", tonnerre.toString());
    }

}
