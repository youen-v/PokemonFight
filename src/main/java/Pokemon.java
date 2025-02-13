import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Pokemon {
    private final String name;
    private final String type;
    private int pv;
    private Map<String , Integer> listeAttaque;
    private int defense;

    @JsonCreator
    public Pokemon(
            @JsonProperty("name") String name,
            @JsonProperty("type") String type,
            @JsonProperty("pv") int pv,
            @JsonProperty("liste-attaque") Map<String, Integer> listeAttaque,
            @JsonProperty("defense") int defense) {
        this.name = name;
        this.type = type;
        this.pv = pv;
        this.listeAttaque = listeAttaque;
        this.defense = defense;
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
        return listeAttaque;
    }

    public void setListeAttaque(Map<String, Integer> listeAttaque) {
        this.listeAttaque = listeAttaque;
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
