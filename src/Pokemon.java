import java.util.HashMap;
import java.util.Map;

public class Pokemon {
    private String name;
    private String type;
    private int pv;
    private Map<String , Integer> liste_attaque;
    private int defense;

    public Pokemon(String nom, String type, int pv, Map<String, Integer> attq, int def){
        this.name = nom;
        this.type = type;
        this.pv = pv;
        this.liste_attaque = new HashMap<>(attq);
        this.defense = def;

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

    @Override
    public String toString() {
        return name;
    }
}
