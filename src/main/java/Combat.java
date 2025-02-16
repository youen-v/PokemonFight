import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Combat {

    public Map<String, Integer> choixAttaque(Joueur joueur){
        Scanner scanner = new Scanner(System.in);
        Map<String , Integer> listeAttaque = joueur.getPokemonChoisi().getListeAttaque();
        ArrayList<String> listeCleAttaque = new ArrayList<>();
        String attaqueSelectionner = joueur.getPokemonChoisi().getAttaqueSelectionner();
        ArrayList<Integer> compteurTour = joueur.getPokemonChoisi().getCompteurTour();
        int idAttaque = 1;

        // Attaque automatique si attaque sur deux tours choisie
        if (compteurTour.size() == 1) {
            Map<String , Integer> choixAuto = new HashMap<>();
            choixAuto.put(attaqueSelectionner, listeAttaque.get(attaqueSelectionner));
            return choixAuto;
        }

        System.out.println("Liste des attaques : ");
        for ( Map.Entry<String , Integer> attaque : listeAttaque.entrySet()) {
            System.out.println(idAttaque++ + " - " + attaque.getKey() + " degat : " + attaque.getValue());
            listeCleAttaque.add(attaque.getKey());
        }

        System.out.println("Veuillez choisir une Attaque (entrez son numéro) :");
        int choix = scanner.nextInt();
        joueur.getPokemonChoisi().setAttaqueSelectionner(listeCleAttaque.get(choix - 1));
        attaqueSelectionner = joueur.getPokemonChoisi().getAttaqueSelectionner();

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

    public Map<String, Integer> attaquePokemon(Joueur joueurAtt, Joueur joueurDef, Integer tour){

        Map<String, Integer> attaque = choixAttaque(joueurAtt);
        String attaqueSelectionner = joueurAtt.getPokemonChoisi().getAttaqueSelectionner();
        int degat = attaque.get(attaqueSelectionner);
        int def = joueurDef.getPokemonChoisi().getDefense();

        String typeAtt = joueurAtt.getPokemonChoisi().getType();
        String typeDef = joueurDef.getPokemonChoisi().getType();

        if ((typeAtt.equals("Feu") && typeDef.equals("Plante")) || (typeAtt.equals("Plante") && typeDef.equals("Eau")) || (typeAtt.equals("Eau") && typeDef.equals("Feu"))){
            degat = degat * 2;
        }

        if (pendingAttaque(tour, joueurAtt)) {
            degat = 0;
        }

        System.out.print("-> Dégats : " + degat + "\n");
        degat = Math.max(degat - def, 0);

        attaque.put(attaqueSelectionner, degat);
        return attaque;
    }

    public boolean pendingAttaque(Integer tour, Joueur compteurTour){
        ArrayList<Integer> compteurTours = compteurTour.getPokemonChoisi().getCompteurTour();

        if (compteurTour.getPokemonChoisi().getAttaqueSelectionner().contains("Ultra-Laser")) {
            compteurTour.getPokemonChoisi().setCompteurTour(tour);
            if (compteurTours.size() == 1){
                return true;
            }
            if (compteurTours.size() >= 2) {
                compteurTours.clear();
            }
        }
        return false;
    }

    public void pointDeVieRestant(Joueur pokemonAttaque, Joueur pokemonDefense, Integer tour) {
        Map<String, Integer> attaqueSelectionner = attaquePokemon(pokemonAttaque, pokemonDefense, tour);
        int degatAttaque = attaqueSelectionner.get(pokemonAttaque.getPokemonChoisi().getAttaqueSelectionner());
        int pvPokemon = pokemonDefense.getPokemonChoisi().getPv();
        pokemonDefense.getPokemonChoisi().setPv(pvPokemon - degatAttaque);
        if (!pokemonAttaque.getPokemonChoisi().getAttaqueSelectionner().equals("Ultra-Laser")) {
            pokemonAttaque.getPokemonChoisi().setAttaqueSelectionner("");
        }
    }

    public void finCombat(Joueur pokemon){
        boolean etatPokemon = pokemon.getPokemonChoisi().estKo();
        if (etatPokemon) {
            System.out.println("Fin du combat");
            System.exit(0);
        }
    }
}
