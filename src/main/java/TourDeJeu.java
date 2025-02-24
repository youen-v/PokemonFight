public class TourDeJeu extends Combat{

    public TourDeJeu(Joueur joueur_1, Joueur joueur_2, int tour) {
        super(joueur_1, joueur_2);
        try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Joueur joueurAtt = (tour % 2 == 0) ? joueur_2 : joueur_1;
            Joueur joueurDef = (tour % 2 == 0) ? joueur_1 : joueur_2;

            System.out.println("------------ TOUR " + tour + " ------------");
            choixAttaque(joueurAtt);
            System.out.print("Attaque du joueur " + joueurAtt.getNom() + "\n");
            pointDeVieRestant(joueurAtt, joueurDef, tour);

            System.out.println("\n-----------------------------------------------\n");
            System.out.println(joueurAtt.getPokemonChoisi());
            System.out.println(joueurDef.getPokemonChoisi());
            System.out.println("\n-----------------------------------------------");

            finCombat(joueurDef, joueurAtt);
    }
}
