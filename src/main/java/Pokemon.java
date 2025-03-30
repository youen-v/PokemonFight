import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Pokemon {
    private final String name;
    private TypePokemon type;
    private PointDeVieStat pv;
    private ArrayList<Attaque> listeAttaque;
    private Attaque attaqueSelectionner;
    private DefenseStat defense;
    private AttaqueStat attaque;
    private Niveau niveau;
    private ArrayList<Integer> compteurTour = new ArrayList<>();

    @JsonCreator
    public Pokemon(
            @JsonProperty("name") String name,
            @JsonProperty("type") TypePokemon type,
            @JsonProperty("pv") PointDeVieStat pv,
            @JsonProperty("liste-attaque") ArrayList<Attaque> listeAttaque,
            @JsonProperty("niveau") Niveau niveau,
            @JsonProperty("defense") DefenseStat defense,
            @JsonProperty("attaque") AttaqueStat attaque){
        this.name = name;
        this.type = type;
        this.pv = pv;
        this.listeAttaque = listeAttaque;
        this.niveau = niveau;
        this.defense = defense;
        this.attaque = attaque;
        getAttaque().initStatattaque(this);
        getDefense().initStatdefense(this);
        getPv().initStatpointDeVie(this);

    }

    public String getNom() {
        return name;
    }

    public TypePokemon getTypePokemon() {
        return type;
    }

    public PointDeVieStat getPv() {
        return pv;
    }

    public void setPv(int pv) {
        pv = Math.max(pv, 0);
        this.pv.setStatpointDeVie(pv);
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

    public AttaqueStat getAttaque() {
        return attaque;
    }

    public DefenseStat getDefense() {
        return defense;
    }

    public boolean estKo() {

        return pv.getStatpointDeVie() <= 0;
    }

    public ArrayList<Integer> getCompteurTour() {
        return compteurTour;
    }

    public void setCompteurTour(Integer tour) {
        this.compteurTour.add(tour);
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    @Override
    public String toString() {
        return name + " PV : " + pv;
    }
}
