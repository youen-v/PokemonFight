import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Pokemon {
    private final String name;
    private final String type;
    private int pv;
    private ArrayList<Attaque> listeAttaque;
    private Attaque attaqueSelectionner;
    private int defense;
    private ArrayList<Integer> compteurTour = new ArrayList<>();

    @JsonCreator
    public Pokemon(
            @JsonProperty("name") String name,
            @JsonProperty("type") String type,
            @JsonProperty("pv") int pv,
            @JsonProperty("liste-attaque") ArrayList<Attaque> listeAttaque,
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

    public ArrayList<Attaque> getListeAttaque() {
        return listeAttaque;
    }


    public Attaque getAttaqueSelectionner() {
        return attaqueSelectionner;
    }

    public void setAttaqueSelectionner(Attaque attaqueSelectionner) {
        this.attaqueSelectionner = attaqueSelectionner;
    }

    public int getDefense() {
        return defense;
    }

    public boolean estKo() {
        return pv <= 0;
    }

    public ArrayList<Integer> getCompteurTour() {
        return compteurTour;
    }

    public void setCompteurTour(Integer tour) {
        this.compteurTour.add(tour);
    }

    @Override
    public String toString() {
        return name + " PV : " + pv;
    }
}
