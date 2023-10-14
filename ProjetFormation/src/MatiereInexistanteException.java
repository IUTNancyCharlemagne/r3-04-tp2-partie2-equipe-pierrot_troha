/**
 * Classe qui gère les cas ou l'on essaye d'utiliser une matière qui n'existe pas
 */
public class MatiereInexistanteException extends Exception {

    /**
     * Constructeur d'exception qui s'execute lorsqu'on essaye d'utiliser une matière qui n'existe pas
     */
    public MatiereInexistanteException(){
        super("La matière renseignée n'existe pas");
    }
}
