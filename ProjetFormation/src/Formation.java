import java.util.*;

/**
 * Classe qui représente des formations avec un identifiant et une liste de matières
 */
public class Formation {

    /**
     * identifiant unique pour chaque formation
     */
    private String identifiant;

    /**
     * liste des matières de la formation
     */
    private Set<Matiere> matieres; // a changer en set

    /**
     * Constructeur de formation
     *
     * @param id identifiant de la formation
     */
    public Formation(String id){
        this.identifiant = id;
        this.matieres = new HashSet<Matiere>();
    }

    /**
     * Fonction qui permet d'ajouter une matière à la liste de matières de la formation
     *
     * @param matiere matière à ajouter à la formation
     * @return Vrai si la matière a bien été ajoutée et faux sinon
     */
    public boolean ajouterMatiere(Matiere matiere){
        return this.matieres.add(matiere);
    }

    /**
     * fonction qui permet de retirer une matière de la liste de matières de la formation
     *
     * @param matiere nom de la matière à retirer
     * @return Vrai si la matière a bien été retirée et faux sinon
     */
    public boolean supprimerMatiere(Matiere matiere){
        if(!this.matieres.isEmpty()) return this.matieres.remove(matiere);
        return false;
    }

    /**
     * founction qui permet de récupérer le coefficient d'une matière de la formation
     *
     * @param matiere matière dont on veut le coefficient
     * @return le coefficient de la matière s'il existe et -1 sinon
     */
    public float getCoefficient(Matiere matiere) throws MatiereInexistanteException {
        if (this.matieres.contains(matiere)){
            return matiere.getCoefficient();
        }else{
            throw new MatiereInexistanteException();
        }
    }

    /**
     * Getter : Permet de récupérer l'identifiant de la formation
     *
     * @return l'identifiant de la formation
     */
    public String getIdentifiant() {
        return this.identifiant;
    }

    /**
     * Setter : Permet de redéfinir l'identifiant de la formation
     *
     * @param identifiant nouvel identifiant
     */
    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Getter : Permet de récupérer la liste de matières de la formation
     *
     * @return la liste de matières de la formation
     */
    public Set<Matiere> getMatieres() {
        return this.matieres;
    }

    /**
     * Setter : Permet de redéfinir liste des matières de la formation
     *
     * @param matieres liste des nouvelles matières de la formation
     */
    public void setMatieres(Set<Matiere> matieres) {
        this.matieres = matieres;
    }
}
