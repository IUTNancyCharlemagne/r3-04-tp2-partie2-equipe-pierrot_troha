import java.util.Objects;

/**
 * Classe qui représente une Matière avec un nom et un coefficient
 */
public class Matiere {

    /**
     * Nom de la matière
     */
    private String nom;

    /**
     * Coefficient de la matière
     */
    private float coefficient;

    /**
     * Constructeur de matière
     *
     * @param n nom de la matière
     * @param coef coefficient de la matière
     */
    public Matiere(String n, float coef){
        this.nom = n;
        this.coefficient = coef;
    }

    /**
     * Getter : Permet de récupérer le nom de la matière
     *
     * @return le nom de la matière
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Setter : Permet de redéfinir le nom de la matière
     *
     * @param nom le nouveau nom de la matière
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter : Permet de récupérer le coefficient de la matière
     *
     * @return le coefficient de la matière
     */
    public float getCoefficient() {
        return this.coefficient;
    }

    /**
     * Setter : Permet de redéfinir le coefficient de la matière
     *
     * @param coefficient le nouveau coefficient de la matière
     */
    public void setCoefficient(float coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Matiere matiere)) return false;

        if (Float.compare(matiere.coefficient, coefficient) != 0) return false;
        return Objects.equals(nom, matiere.nom);
    }

    @Override
    public int hashCode() {
        int result = nom != null ? nom.hashCode() : 0;
        result = 31 * result + (coefficient != +0.0f ? Float.floatToIntBits(coefficient) : 0);
        return result;
    }
}
