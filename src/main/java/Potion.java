public class Potion extends Item {
    public Potion() {
        super("potion", "pv", 20, "Potion qui rend un peu de PV");
    }

    @Override
    public String toString() {
        return "Petite Potion";
    }
}
