import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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


    /**
     * Permet d'accéder à la moyenne générale du groupe dans une matière donnée
     *
     * @param m matiere dont on veut connaître la moyenne du groupe
     * @return float correspondant à la moyenne du groupe
     * @throws MatiereInexistanteException
     */
    public float calculerMoyenneParMatiere(Matiere m) throws MatiereInexistanteException{
        if(!this.formation.getMatieres().contains(m)){
            throw new MatiereInexistanteException();
        }else{
            float somme = 0f;
            float effTotal = 0f;
            for(Etudiant etu : this.etudiants){
                // On vérifie que l'étudiant a des notes dans la matière (ex : absent au contrôle)
                if(!(etu.getNotes().get(m).size() == 0)){
                    somme += etu.calculerMoyenneMatiere(m);
                    effTotal += 1;
                }
            }
            // Si aucun n'étudiant n'a de notes dans la matière donnée
            if(effTotal == 0){
                return 0f;
            }else{
                return somme/effTotal;
            }

        }
    }

    /**
     * Permet d'accéder à la moyenne générale du groupe dans toutes matières confondues
     *
     * @return float correspondant à la moyenne générale du groupe
     */
    public float calculerMoyenneGenerale() throws MatiereInexistanteException{
        float somme = 0f;
        float effTotal = 0f;

        for(Matiere m : this.formation.getMatieres()){
            somme += this.calculerMoyenneParMatiere(m) * m.getCoefficient();
            effTotal += m.getCoefficient();
        }
        if(effTotal == 0){
            return 0f;
        }else{
            return somme/effTotal;
        }
    }

    /**
     * Méthode qui permet de trier une liste d'étudiants dans l'ordre alphabétique
     */
    public void triAlpha(){
        Collections.sort(this.etudiants);
    }

    /**
     * Méthode qui permet de trier une liste dans l'ordre antialphabétique
     */
    public void triAntiAlpha() {
        Collections.sort(etudiants, new Comparator<Etudiant>() {
            @Override
            public int compare(Etudiant etudiant1, Etudiant etudiant2) {
                return etudiant2.getIdentite().getNom().compareTo(etudiant1.getIdentite().getNom());
            }
        });
    }


    /**
     * Méthode qui redéfinit un comparateur d'Etudiant afin de les comparer dans l'ordre décroissant selon
     * leur moyenne générale (Ex : Paul 19 sera 1er et Marie 14 sera 2ème)
     * S'ils ont la même moyenne générale, alors il sont trié par ordre alphabétique
     */
    public void triParMerite() throws MatiereInexistanteException{
        Collections.sort(etudiants, new Comparator<Etudiant>(){
            @Override
            public int compare(Etudiant etu1, Etudiant etu2) {
                try {
                    float moyenne_1 = etu1.calculerMoyenneGenerale();
                    float moyenne_2 = etu2.calculerMoyenneGenerale();
                    if(moyenne_1 > moyenne_2){
                        return -1;
                    }else if(moyenne_1 <moyenne_2){
                        return 1;
                    }else{
                        return etu1.getIdentite().getNom().compareTo(etu2.getIdentite().getNom());
                    }
                } catch (MatiereInexistanteException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    /**
     * Renvoie en String la liste des Etudiants
     * @return la liste de tous les élèves avec leur nom leur prénom et leur NIP (+ formation)
     */
    public String toString() {
        String res = "";
        for (Etudiant etu : this.etudiants) {
            res += (etu.getIdentite().getNom() + "-" + etu.getIdentite().getPrenom() + " : " + this.formation.getIdentifiant() + "\n");
        }
        return res;
    }



}
