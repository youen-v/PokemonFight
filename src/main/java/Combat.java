import consoleColors.ConsoleColors;

import java.util.*;

public class Combat {
    public Joueur joueur1;
    public Joueur joueur2;
    public Integer tourDeJeu;
    private final List<String> listeAttaqueLongue = List.of("Ultra-Laser", "Charge");

    public Combat(Joueur joueur1, Joueur joueur2){
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
    }

    public void lancementTourDeJeu(){
        for (tourDeJeu = 1; tourDeJeu<= 50; tourDeJeu++){
            new TourDeJeu(joueur1, joueur2, tourDeJeu);
        }
    }

    public Attaque choixAttaque(Joueur joueur){
        Scanner scanner = new Scanner(System.in);
        ArrayList<Attaque> listeAttaque = joueur.getPokemonChoisi().getListeAttaque();
        ArrayList<Integer> compteurTour = joueur.getPokemonChoisi().getCompteurTour();
        int idAttaque = 1;

        // Attaque automatique si attaque sur deux tours
        if (compteurTour.size() == 1) {
            System.out.println("Attaque en chargement ... ");
            try {
                Thread.sleep(1800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Attaque choixAuto = joueur.getPokemonChoisi().getAttaqueSelectionner();
            return choixAuto;
        }

        System.out.println("Liste des attaques : ");
        for ( Attaque attaque : listeAttaque) {
            System.out.println(idAttaque++ + " - " + attaque.getNom() + " degat : " + attaque.getDegat());
        }

        System.out.println("Veuillez choisir une Attaque (entrez son numéro) :");
        int choix = scanner.nextInt();
       
        // Vérification de la validité du choix
        try {
            Attaque attaqueChoisie = listeAttaque.get(choix - 1);
            joueur.getPokemonChoisi().setAttaqueSelectionner(attaqueChoisie);
            System.out.println(joueur.getPokemonChoisi().getNom() + " attaque " + attaqueChoisie);
            return attaqueChoisie;
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "Choix invalide" + ConsoleColors.RESET);
            joueur.essaiJoueur(listeAttaque, choix);
            choixAttaque(joueur);
        }
        return null;
    }

    public int degatAttaquePokemon(Joueur joueurAtt, Joueur joueurDef, Integer tour){

        Attaque attaqueSelectionner = joueurAtt.getPokemonChoisi().getAttaqueSelectionner();
        int degat = attaqueSelectionner.getDegat();
        int def = joueurDef.getPokemonChoisi().getDefense();

        String typeAtt = joueurAtt.getPokemonChoisi().getType();
        String typeDef = joueurDef.getPokemonChoisi().getType();

        if ((typeAtt.equals("Feu") && typeDef.equals("Plante")) || (typeAtt.equals("Plante") && typeDef.equals("Eau")) || (typeAtt.equals("Eau") && typeDef.equals("Feu"))){
            degat = degat * 2;
        }

        if (pendingAttaque(tour, joueurAtt)) {
            degat = 0;
        }

        degat = Math.max(degat - def, 0);
        System.out.print("-> Dégats : " + degat + "\n");

        return degat;
    }

    public boolean pendingAttaque(Integer tour, Joueur compteurTour){
        ArrayList<Integer> compteurTours = compteurTour.getPokemonChoisi().getCompteurTour();
        Attaque nomAttaque = compteurTour.getPokemonChoisi().getAttaqueSelectionner();
        if ( nomAttaque != null && listeAttaqueLongue.contains(nomAttaque.getNom())) {
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
        int degatAttaque = degatAttaquePokemon(pokemonAttaque, pokemonDefense, tour);
        int pvPokemon = pokemonDefense.getPokemonChoisi().getPv();
        pokemonDefense.getPokemonChoisi().setPv(pvPokemon - degatAttaque);
        if (!listeAttaqueLongue.contains(pokemonAttaque.getPokemonChoisi().getAttaqueSelectionner().getNom())) {
            pokemonAttaque.getPokemonChoisi().setAttaqueSelectionner(null);
        }
    }

    public void finCombat(Joueur joueurPerdant, Joueur joueurGagnant){
        boolean etatPokemon = joueurPerdant.getPokemonChoisi().estKo();
        if (etatPokemon) {
            System.out.println("Fin du combat : Vainqueur - " + joueurGagnant.getNom() + " avec " + joueurGagnant.getPokemonChoisi().getNom());
            System.exit(0);
        }
    }
}
