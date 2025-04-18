import consoleColors.ConsoleColors;
import java.util.*;

public class Combat {
    public Joueur joueur1;
    public Joueur joueur2;
    public Integer tourDeJeu;
    private final List<String> listeAttaqueLongue = List.of("Ultra-Laser");

    public Combat() {
        new Menu().afficherMenu();
    }

    public Combat(Joueur joueur1, Joueur joueur2){
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
    }

    public void lancementTourDeJeu(){
        for (tourDeJeu = 1; tourDeJeu<= 100; tourDeJeu++){
            new TourDeJeu(joueur1, joueur2, tourDeJeu);
        }
        System.out.println("Bienvenue dans le Menu");
        new Menu().afficherMenu();

    }

    public int sousMenu(TourDeJeu tour, Joueur joueur){
        return new Menu().sousMenu(tour, joueur);
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
            // Affichage de l'attaque seulement si elle est utilisable (A MODIFIER A L'AJOUT D'ITEMS QUI RAJOUTE DES UTILISATION)
            if (attaque.getUtilisation() > 0){
                System.out.println(idAttaque++ + " - " + attaque.getNom() + " degat : " + attaque.getDegat() + " Nombre d'utilisation : " + attaque.getUtilisation());
            }
        }

        System.out.println("Veuillez choisir une Attaque (entrez son numéro) :");
        int choix = scanner.nextInt();

        // Vérification de la validité du choix
        try {
            Attaque attaqueChoisie = listeAttaque.get(choix - 1);
            joueur.getPokemonChoisi().setAttaqueSelectionner(attaqueChoisie);
            if (attaqueChoisie.getUtilisation() > 0){
                attaqueChoisie.utilisationAttaque(attaqueChoisie.getUtilisation());
            } else {
                System.out.println("Vous n'avez plus d'utilisation pour cette Attaque !");
                throw new RuntimeException();
            }
            System.out.print("-----------------------------------------------\n");
            System.out.println(joueur.getPokemonChoisi().getNom() + " attaque " + attaqueChoisie);
            return attaqueChoisie;
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "Choix invalide" + ConsoleColors.RESET);
            joueur.essaiJoueur(listeAttaque, choix);
            choixAttaque(joueur);
        }
        return null;
    }

    public int degatAttaquePokemon(Pokemon pokemonAtt, Pokemon pokemonDef, Integer tour){

        Attaque attaqueSelectionner = pokemonAtt.getAttaqueSelectionner();
        int degat = attaqueSelectionner.getDegat();
        int def = pokemonDef.getDefense().getStatdefense();

        int calculDegat = (((2 * pokemonAtt.getNiveau().getLevel() / 5 + 2) * degat * pokemonAtt.getAttaque().getStatattaque() / def ) + 2);
        double retourFaiblOuRes = pokemonDef.getTypePokemon().calculFaiblesseOuRes(pokemonAtt);
        int retourDegat = (int) (calculDegat *  retourFaiblOuRes);

        if (pendingAttaque(tour, pokemonAtt)) {
            retourDegat = 0;
        }

        degat = Math.max(retourDegat, 0);
        System.out.print("-> Dégats : " + degat + "\n");

        return degat;
    }

    public boolean pendingAttaque(Integer tour, Pokemon compteurTour){
        ArrayList<Integer> compteurTours = compteurTour.getCompteurTour();
        Attaque nomAttaque = compteurTour.getAttaqueSelectionner();
        if ( nomAttaque != null && listeAttaqueLongue.contains(nomAttaque.getNom())) {
            compteurTour.setCompteurTour(tour);
            if (compteurTours.size() == 1){
                return true;
            }
            if (compteurTours.size() >= 2) {
                compteurTours.clear();
            }
        }
        return false;
    }

    public void pointDeVieRestant(Pokemon pokemonAttaque, Pokemon pokemonDefense, Integer tour) {
        int degatAttaque = degatAttaquePokemon(pokemonAttaque, pokemonDefense, tour);
        int pvPokemon = pokemonDefense.getPv().getStatpointDeVie();
        pokemonDefense.setPv(pvPokemon - degatAttaque);
        if (!listeAttaqueLongue.contains(pokemonAttaque.getAttaqueSelectionner().getNom())) {
            pokemonAttaque.setAttaqueSelectionner(null);
        }
    }

    public void pokemonKo(Joueur pokemonKo, Joueur pokemonVainqueur) {
        if (pokemonKo.getPokemonChoisi().estKo() && !pokemonKo.getPokedex().getPokedex().isEmpty() ){
            new Niveau().calculerExperienceGagnee(pokemonVainqueur.getPokemonChoisi(), pokemonKo.getPokemonChoisi());
            pokemonKo.resetPokemonChoisi();
            pokemonKo.getChoixPokemon();
        } else {
            finCombat(pokemonKo, pokemonVainqueur);
        }
    }

    public void finCombat(Joueur joueurPerdant, Joueur joueurGagnant){
        boolean etatPokemon = joueurPerdant.getPokemonChoisi().estKo();
        if (etatPokemon && joueurPerdant.getPokedex().getPokedex().isEmpty()) {
            System.out.println("Fin du combat : Vainqueur - " + joueurGagnant.getNom() + " avec " + joueurGagnant.getPokemonChoisi().getNom());
            new Menu();
        }
    }
}
