import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * La classe {@code Pokemon} représente un Pokémon avec ses caractéristiques essentielles.
 *
 * <p>Chaque Pokémon possède un nom, un type, un nombre de points de vie (pv),
 * une liste d'attaques associée à des valeurs de dégâts et une valeur de défense.
 * Cette classe est conçue pour être désérialisée à partir d'un JSON via Jackson.</p>
 *
 * <p>Exemple de JSON correspondant :</p>
 * <pre>
 * {
 *   "name": "Pikachu",
 *   "type": "Électrique",
 *   "pv": 100,
 *   "liste-attaque": {
 *       "Éclair": 40,
 *       "Tonnerre": 60
 *   },
 *   "defense": 30
 * }
 * </pre>
 */
public class Pokemon {
    private final String name;
    private final String type;
    private int pv;
    private Map<String , Integer> listeAttaque;
    private int defense;

    /**
     * Constructeur pour la désérialisation JSON avec Jackson.
     *
     * <p>Utilise les annotations {@code @JsonCreator} et {@code @JsonProperty} pour
     * mapper les propriétés du JSON aux attributs du Pokémon.</p>
     *
     * @param name le nom du Pokémon
     * @param type le type du Pokémon
     * @param pv les points de vie du Pokémon
     * @param listeAttaque la map des attaques (nom de l'attaque et dégâts)
     * @param defense la valeur de défense du Pokémon
     */
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

    /**
     * Retourne le nom du Pokémon.
     *
     * @return le nom du Pokémon
     */
    public String getNom() {
        return name;
    }

    /**
     * Retourne le type du Pokémon.
     *
     * @return le type du Pokémon
     */
    public String getType() {
        return type;
    }

    /**
     * Retourne les points de vie actuels du Pokémon.
     *
     * @return les points de vie du Pokémon
     */
    public int getPv() {
        return pv;
    }

    /**
     * Met à jour les points de vie du Pokémon.
     * <p>
     * Si la valeur passée est inférieure à zéro, elle est remplacée par zéro pour éviter des points de vie négatifs.
     * </p>
     *
     * @param pv la nouvelle valeur des points de vie
     */
    public void setPv(int pv) {
        pv = Math.max(pv, 0);
        this.pv = pv;
    }

    /**
     * Retourne la liste des attaques du Pokémon.
     *
     * @return une {@code Map} associant le nom des attaques aux dégâts correspondants
     */
    public Map<String, Integer> getListeAttaque() {
        return listeAttaque;
    }

    /**
     * Met à jour la liste des attaques du Pokémon.
     *
     * @param listeAttaque la nouvelle map des attaques
     */
    public void setListeAttaque(Map<String, Integer> listeAttaque) {
        this.listeAttaque = listeAttaque;
    }

    /**
     * Retourne la valeur de défense du Pokémon.
     *
     * @return la défense du Pokémon
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Met à jour la valeur de défense du Pokémon.
     *
     * @param defense la nouvelle valeur de défense
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * Indique si le Pokémon est KO.
     * <p>
     * Un Pokémon est considéré comme KO si ses points de vie sont inférieurs ou égaux à zéro.
     * </p>
     *
     * @return {@code true} si le Pokémon est KO, sinon {@code false}
     */
    public boolean estKo() {
        return getPv() <= 0;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères du Pokémon.
     *
     * @return une chaîne contenant le nom du Pokémon et ses points de vie
     */
    @Override
    public String toString() {
        return name + " PV : " + pv;
    }
}
