import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * La classe {@code Combat} gère le déroulement d'un combat entre des entités de type {@link Joueur}.
 * <p>
 * Elle propose des méthodes permettant à un joueur de choisir une attaque, d'effectuer l'attaque,
 * de mettre à jour les points de vie du défenseur et de vérifier la fin du combat.
 * </p>
 */
public class Combat {
    private String attaqueSelectionner;

    /**
     * Constructeur par défaut de la classe Combat.
     * Il initialise une instance de Combat.
     */
    public Combat(){}
    /**
     * Permet à un joueur de choisir une attaque parmi la liste d'attaques de son Pokémon.
     * <p>
     * La méthode affiche la liste des attaques disponibles, demande à l'utilisateur de choisir
     * une attaque en entrant son numéro, et retourne une {@code Map} contenant l'attaque choisie
     * et sa valeur de dégât. Si le choix est invalide, la méthode se rappelle récursivement.
     * </p>
     *
     * @param joueur Le {@code Joueur} dont le Pokémon effectuera l'attaque.
     * @return Une {@code Map<String, Integer>} contenant l'attaque sélectionnée en clé et le nombre de dégâts associé en valeur.
     */
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

    /**
     * Effectue l'attaque d'un joueur contre un autre en tenant compte des types et de la défense.
     * <p>
     * La méthode récupère l'attaque choisie par le joueur attaquant via {@link #choixAttaque(Joueur)},
     * calcule les dégâts en tenant compte d'éventuelles majorations (par exemple, si le type de l'attaque est avantageux contre le type du défenseur),
     * soustrait la défense du défenseur, affiche les dégâts infligés et retourne la {@code Map} mise à jour.
     * </p>
     *
     * @param joueurAtt Le joueur qui attaque.
     * @param joueurDef Le joueur qui défend.
     * @return Une {@code Map<String, Integer>} contenant l'attaque sélectionnée et le nombre de dégâts final après application de la défense.
     */
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

    /**
     * Met à jour les points de vie du Pokémon défenseur après une attaque.
     * <p>
     * La méthode récupère les dégâts infligés à partir de la {@code Map} d'attaque et
     * soustrait ces dégâts aux points de vie actuels du Pokémon du joueur défenseur.
     * </p>
     *
     * @param attaque La {@code Map<String, Integer>} contenant l'attaque et les dégâts associés.
     * @param pokemon Le joueur dont le Pokémon subit l'attaque.
     */
    public void pointDeVieRestant(Map<String, Integer> attaque, Joueur pokemon) {
        int degatAttaque = attaque.get(attaqueSelectionner);
        int pvPokemon = pokemon.getPokemonChoisi().getPv();
        pokemon.getPokemonChoisi().setPv(pvPokemon - degatAttaque);
    }

    /**
     * Vérifie si le combat doit être terminé en testant l'état KO du Pokémon du joueur.
     * <p>
     * Si le Pokémon est KO (vérifié via {@code estKo()}), la méthode affiche un message de fin
     * de combat et termine l'application.
     * </p>
     *
     * @param pokemon Le joueur dont le Pokémon est à vérifier.
     */
    public void finCombat(Joueur pokemon){
        boolean etatPokemon = pokemon.getPokemonChoisi().estKo();
        if (etatPokemon) {
            System.out.println("Fin du combat");
            System.exit(0);
        }
    }
}
