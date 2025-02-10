import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Pokemon {
    private final String name;
    private final String type;
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

    public void setPv(int pv) {
        pv = Math.max(pv, 0);
        this.pv = pv;
    }

    public Map<String, Integer> getListeAttaque() {
        return liste_attaque;
    }

    public void setListeAttaque(Map<String, Integer> liste_attaque) {
        this.liste_attaque = liste_attaque;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public boolean estKo() {
        return getPv() <= 0;
    }

    @Override
    public String toString() {
        return name + " PV : " + pv;
    }
}
