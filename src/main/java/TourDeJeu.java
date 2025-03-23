public class TourDeJeu extends Combat{

    public TourDeJeu(Joueur joueur_1, Joueur joueur_2, int tour) {
        super(joueur_1, joueur_2);
        Joueur joueurAtt = (tour % 2 == 0) ? joueur_2 : joueur_1;
        Joueur joueurDef = (tour % 2 == 0) ? joueur_1 : joueur_2;

        System.out.println("------------ TOUR " + tour + " ------------");
        System.out.print("-----------------------------------------------\n");
        choixAttaque(joueurAtt);
        pointDeVieRestant(joueurAtt.getPokemonChoisi(), joueurDef.getPokemonChoisi(), tour);

        System.out.println("\n-----------------------------------------------\n");
        System.out.println(joueurAtt.getPokemonChoisi());
        System.out.println(joueurDef.getPokemonChoisi());
        System.out.println("\n-----------------------------------------------");

        finCombat(joueurDef, joueurAtt);
    }
}
