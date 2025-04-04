public class Item {
    private String nom;
    private String type;
    private int capacite;
    private String description;

    public Item(String nom, String type, int capacite, String description) {
        this.nom = nom;
        this.type = type;
        this.capacite = capacite;
        this.description = description;
    }

    public void useItem(Pokemon pokemon, Item item) {
        String typeItem = item.type;
        int capaciteItem = item.capacite;
        int stat;

        switch (typeItem) {
            case "pv": {
                stat = pokemon.getPv().getStatpointDeVie();
                pokemon.getPv().setStatpointDeVie((stat + capaciteItem));
            }
        }
    }
}
