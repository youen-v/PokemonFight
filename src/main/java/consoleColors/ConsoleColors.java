package consoleColors;

/**
 * La classe {@code ConsoleColors} fournit des constantes de codes d'échappement ANSI
 * pour colorer le texte dans la console.
 *
 * <p>Chaque constante représente une couleur ou une commande pour réinitialiser
 * la couleur du texte. Par exemple, pour afficher un texte en rouge, vous pouvez faire :</p>
 *
 * <pre>
 * System.out.println(ConsoleColors.RED + "Texte en rouge" + ConsoleColors.RESET);
 * </pre>
 *
 * <p>Note : Ces codes fonctionnent dans les terminaux supportant les séquences ANSI.</p>
 */
public class ConsoleColors {

    /**
     * Constructeur par défaut de la classe Combat.
     * Il initialise une instance de Combat.
     */
    public ConsoleColors(){}

    /**
     * Code ANSI pour réinitialiser la couleur (retour à la couleur par défaut).
     */
    public static final String RESET = "\u001B[0m";

    /**
     * Code ANSI pour afficher le texte en rouge.
     */
    public static final String RED = "\u001B[31m";

    /**
     * Code ANSI pour afficher le texte en vert.
     */
    public static final String GREEN = "\u001B[32m";

    /**
     * Code ANSI pour afficher le texte en jaune.
     */
    public static final String YELLOW = "\u001B[33m";

    /**
     * Code ANSI pour afficher le texte en bleu.
     */
    public static final String BLUE = "\u001B[34m";

    /**
     * Code ANSI pour afficher le texte en violet.
     */
    public static final String PURPLE = "\u001B[35m";

    /**
     * Code ANSI pour afficher le texte en cyan.
     */
    public static final String CYAN = "\u001B[36m";

    /**
     * Code ANSI pour afficher le texte en blanc.
     */
    public static final String WHITE = "\u001B[37m";
}
