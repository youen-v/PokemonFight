import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Combat {
    private String attaqueSelectionner;

    public Map<String, Integer> choixAttaque(Joueur joueur){
        Scanner scanner = new Scanner(System.in);
        Map<String , Integer> listeAttaque = joueur.getPokemonChoisi().getListeAttaque();
        ArrayList<String> listeCleAttaque = new ArrayList<>();
        int idAttaque = 1;

        System.out.println("Liste des attaques : ");
        for ( Map.Entry<String , Integer> attaque : listeAttaque.entrySet()) {
            System.out.println(idAttaque++ + " - " + attaque.getKey() + " degat : " + attaque.getValue());
            listeCleAttaque.add(attaque.getKey());
        }

        System.out.println("Veuillez choisir une Attaque (entrez son numéro) :");
        int choix = scanner.nextInt();
        this.attaqueSelectionner = listeCleAttaque.get(choix - 1);
        // Vérification de la validité du choix
        if (listeAttaque.containsKey(attaqueSelectionner)) {
            Map<String,Integer> attaqueChoisie = new HashMap<>();
            attaqueChoisie.put(attaqueSelectionner, listeAttaque.get(attaqueSelectionner));
            System.out.println(joueur.getPokemonChoisi().getNom() + " attaque " + attaqueSelectionner);
            return attaqueChoisie;
       } else {
            System.out.println("Choix invalide. Réessayez.");
            return choixAttaque(joueur);
       }
    }

    public Map<String, Integer> attaquePokemon(Joueur joueurAtt, Joueur joueurDef){

        Map<String, Integer> attaque = choixAttaque(joueurAtt);
        int degat = attaque.get(attaqueSelectionner);
        int def = joueurDef.getPokemonChoisi().getDefense();

        String typeAtt = joueurAtt.getPokemonChoisi().getType();
        String typeDef = joueurDef.getPokemonChoisi().getType();

       if ((typeAtt.equals("Feu") && typeDef.equals("Plante")) || (typeAtt.equals("Plante") && typeDef.equals("Eau")) || (typeAtt.equals("Eau") && typeDef.equals("Feu"))){
           degat = degat * 2;
       }

       System.out.print("-> Dégats : " + degat + "\n");
       degat = (degat - def);
       attaque.put(attaqueSelectionner, degat);
       return attaque;
    }

    public void pointDeVieRestant(Map<String, Integer> attaque, Joueur pokemon) {
        int degatAttaque = attaque.get(attaqueSelectionner);
        int pvPokemon = pokemon.getPokemonChoisi().getPv();
        pokemon.getPokemonChoisi().setPv(pvPokemon - degatAttaque);
    }

    public void finCombat(Joueur pokemon){
        boolean etatPokemon = pokemon.getPokemonChoisi().estKo();
        if (etatPokemon) {
            System.out.println("Fin du combat");
            System.exit(0);
        }
    }
}
