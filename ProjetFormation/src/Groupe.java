import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant de représenter un groupe contenant des étudiants qui participent à la même formation
 */
public class Groupe {

    /**
     * Formation du groupe
     */
    private Formation formation;

    /**
     * Liste des étudiant présents dans le groupe
     */
    private List<Etudiant> etudiants;

    /**
     * Constructeur de groupe vide avec une formation définie
     *
     * @param f formation du groupe
     */
    public Groupe(Formation f) {
        this.formation = f;
        this.etudiants = new ArrayList<Etudiant>();
    }

    /**
     * Constructeur de groupe avec une liste d'étudiant et une formation prédéfinie
     *
     * @param f formation du groupe
     * @param etu liste d'étudiants du groupe
     */
    public Groupe(Formation f, ArrayList etu) {
        this.formation = f;
        this.etudiants = etu;
    }

    /**
     * Méthode qui permet d'ajouter un étudiant au groupe
     *
     * @param etu étudiant à ajouter au groupe
     * @return vrai si l'ajout est réussi et faux sinon
     */
    public boolean ajouterEtudiant(Etudiant etu){
        if(etu.getFormation().equals(this.formation)&&!(this.etudiants.contains(etu))){
            this.etudiants.add(etu);
            return true;
        }
        return false;
    }

    /**
     * Méthode qui permet de supprimer un étudiant au groupe
     *
     * @param etu étudiant à supprimer au groupe
     * @return vrai si la suppression est réussie et faux sinon
     */
    public boolean supprimerEtudiant(Etudiant etu){
        return this.etudiants.remove(etu);
    }

    /**
     * Getter de formation
     *
     * @return la formation du groupe
     */
    public Formation getFormation() {
        return this.formation;
    }

    /**
     * Getter de la liste des étudiants
     *
     * @return la liste des étudiants du groupe
     */
    public List<Etudiant> getEtudiants() {
        return this.etudiants;
    }

}
