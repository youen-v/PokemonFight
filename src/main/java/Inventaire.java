import java.util.ArrayList;
import java.util.Scanner;
import consoleColors.ConsoleColors;

public class Inventaire {
    private int stockageMin = 0;
    private int stockageMax = 24;
    private ArrayList<Item> listeItems;

    public Inventaire(ArrayList<Item> listeItems) {
        this.listeItems = listeItems;
    }

    public ArrayList<Item> getListeItems() {
        return listeItems;
    }

    public void printListeItems(Pokemon pokemon) {
        if (!listeItems.isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            for (int i=0; i<listeItems.size(); i++) {
                System.out.println(i + ". " + listeItems.get(i));
            }
            System.out.println("Choix du l'objet d'inventaire: ");
            int choixItem = scanner.nextInt();
            Item item = listeItems.get(choixItem);
            item.useItem(pokemon, item);
        } else {
            System.out.println(ConsoleColors.YELLOW +"Vous n'avez aucun objet de disponible" + ConsoleColors.RESET);
        }

    }

    public void setItem(Item item) {
        getListeItems().add(item);
    }
}
