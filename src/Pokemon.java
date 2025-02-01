import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Pokemon {
    private final String name;
    private final String type;
    private final int pv;
    private ArrayList<Integer> historyPv;
    private final Map<String , Integer> liste_attaque;
    private final int defense;

    public Pokemon(String nom, String type, int pv, Map<String, Integer> attq, int def){
        this.name = nom;
        this.type = type;
        this.pv = pv;
        this.liste_attaque = new HashMap<>(attq);
        this.defense = def;
        this.historyPv = new ArrayList<>();
        historyPv.add(pv);

    }

    public String getNom() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getPv() {
        return pv;
    }

    public Map<String, Integer> getListe_attaque() {
        return liste_attaque;
    }

    public int getDefense() {
        return defense;
    }

    public ArrayList<Integer> getHistoryPv() {
        return historyPv;
    }

    @Override
    public String toString() {
        return name;
    }
}
