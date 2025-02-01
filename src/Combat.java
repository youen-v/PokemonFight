import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Combat {
    private Joueur joueur1;
    private Joueur joueur2;

    public Combat(Joueur joueur1, Joueur joueur2){
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
    }

    public int affichePv(Joueur joueur) {
        int pvpokemon = joueur.getPokemonChoisi().getPv();
        if (pvpokemon < 0 ){
            pvpokemon = 0;
        }
        return pvpokemon;
    }

    public int choiceAttq(Joueur joueur){
        Scanner scanner = new Scanner(System.in);
        int degat;
        ArrayList<String> attNom = new ArrayList<>();
        Map<String , Integer> listAtt = joueur.getPokemonChoisi().getListe_attaque();
        int idAttq = 1;

        System.out.println("Liste des attaques : ");
        for ( Map.Entry<String , Integer> attChoi : listAtt.entrySet()) {
            System.out.println(idAttq++ + " - " + attChoi.getKey() + " degat : " + attChoi.getValue());
            attNom.add(attChoi.getKey());
        }

        System.out.println("Veuillez choisir une Attaque (entrez son numéro) :");
        int choix = scanner.nextInt();
        String attName = attNom.get((choix - 1));
        // Vérification de la validité du choix
        if (listAtt.containsKey(attName)) {
            degat = listAtt.get(attName);
            System.out.println(joueur.getPokemonChoisi() + " attaque " + attName);
        } else {
            System.out.println("Choix invalide. Réessayez.");
            return choiceAttq(joueur);
        }

        return degat;
    }

    public int attaquePokemon(Joueur joueurAtt, Joueur joueurDef){

        int attq = choiceAttq(joueurAtt);
        int def = joueurDef.getPokemonChoisi().getDefense();
        ArrayList<Integer> history = joueurDef.getPokemonChoisi().getHistoryPv();
        int pvRest = history.getLast();

        String typeAtt = joueurAtt.getPokemonChoisi().getType();
        String typeDef = joueurDef.getPokemonChoisi().getType();

        if ((typeAtt.equals("Feu") && typeDef.equals("Plante")) || (typeAtt.equals("Plante") && typeDef.equals("Eau")) || (typeAtt.equals("Eau") && typeDef.equals("Feu"))){
            attq = attq * 2;
        }

        System.out.print("-> Dégats : " + attq + "\n");
        pvRest = pvRest - (attq - def);
        history.add(pvRest);
        return pvRest;
    }

    public void finCombat(int pvpoke){
        if (pvpoke <= 0) {
            System.out.println("Fin du combat");
            System.exit(1);
        }
    }
}
