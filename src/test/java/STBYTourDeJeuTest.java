import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class STBYTourDeJeuTest {
    /*@Test
    void testTourDeJeu(){
        Joueur mockJoueur1 = mock(Joueur.class);
        Joueur mockJoueur2 = mock(Joueur.class);
        Combat mockCombat = mock(Combat.class);

        when(mockJoueur1.getNom()).thenReturn("Joueur 1");
        when(mockJoueur2.getNom()).thenReturn("Joueur 2");

        Pokemon mockPokemon1 = mock(Pokemon.class);
        Pokemon mockPokemon2 = mock(Pokemon.class);
        when(mockPokemon1.getNom()).thenReturn("Pikachu");
        when(mockPokemon2.getNom()).thenReturn("Bulbizarre");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        assertTrue(outContent.toString().contains("Attaque du joueur Pikachu"));
        verify(mockJoueur1, times(1)).getNom();
        verify(mockJoueur2, times(1)).getNom();

        verify(mockJoueur1, atLeastOnce()).getPokemonChoisi();
        verify(mockJoueur2, atLeastOnce()).getPokemonChoisi();

        System.setOut(System.out);
    }*/
}
