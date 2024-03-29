import java.util.*;

public class Etudiant implements Comparable<Etudiant> {
    /**
     * Identite de l'étudiant
     */
    private Identite identite;

    /**
     * Formation que suit l'étudiant (représentée par un identifiant
     * et un Set de Matière (car chaque matière est unique))
     */
    private Formation formation;

    /**
     * Correspond à la collection de notes par matière de l'étudiant
     * On part du principe que toutes les notes d'une matière possèdent le même coefficient.
     */
    private Map<Matiere, List<Float>> notes;

    /**
     *
     * @param id
     * @param f
     */
    public Etudiant(Identite id, Formation f){
        this.identite = id;
        this.formation = f;
        // Puis, on part du principe que les Matières que l'étudiant possède dans sa formation comportent toutes des notes.
        // Ainsi, l'ensemble de clés (Matière) des résultats de l'élève correspond au Set<Matiere> dans Formation
        this.notes = new HashMap<Matiere, List<Float>>();
        for (Matiere matiere : this.formation.getMatieres()) {
            this.notes.put(matiere, new ArrayList<Float>()); // Ajoutez la matière en tant que clé avec une liste vide en valeur
        }
    }

    /**
     *
     * @return l'identité de l'étudiant
     */
    public Identite getIdentite() {
        return identite;
    }

    /**
     *
     * @return la formation de l'étudiant
     */
    public Formation getFormation() {
        return formation;
    }

    /**
     *
     * @return notes de l'étudiant
     */
    public Map<Matiere, List<Float>> getNotes() {
        return notes;
    }

    /**
     * Permet d'ajouter une note à une matière donnée.
     * Si la matière n'existe pas, une exception est levée, si la note est inférieure à 0 alors elle est arrondie à 0
     * Si elle est supérieure à 20, elle est arrondie à 20
     * @param m Matiere à laquelle on veut ajouter une note
     * @param note qu'on veut ajouter à telle matière
     */
    public void ajouterNoteMatiere(Matiere m, float note) throws MatiereInexistanteException {
        if(note<0) note = 0;
        if(note>20) note = 20;
        // Implique de redéfinir equals et hashcode
        if(!this.notes.containsKey(m)){
            throw new MatiereInexistanteException();
        }else{
            // On récupère la liste de notes pour la matière
            List<Float> listeNotes = this.notes.get(m);
            // On lui ajoute la note
            listeNotes.add(note);
            // Puis on remet la liste modifiée associée à la matière
            this.notes.put(m, listeNotes);
        }
    }

    /**
     * Permet de calculer la moyenne d'un étudiant dans une matière donnée, lève une exception si la matière n'existe pas
     * @param m Matiere dont on veut la moyenne
     * @return un float correspondant à la moyenne pour la matière donnée
     */
    public float calculerMoyenneMatiere(Matiere m) throws MatiereInexistanteException {
        if(!this.notes.containsKey(m)){
            throw new MatiereInexistanteException();
        }else{
            List<Float> listeNotes = this.notes.get(m);
            float somme = 0f;
            int eff = listeNotes.size();
            for(Float f : listeNotes){
                somme += f;
            }
            // On vérifie que s'il n'y a pas de notes alors on retourne 0
            if(eff == 0){
                return 0;
            }
            return somme/eff;
        }
    }

    /**
     * Permet de calculer la moyenne générale d'un étudiant tout en prenant en compte les coefficients différents.
     * @return la moyenne générale d'un étudiant
     */
    public float calculerMoyenneGenerale() throws MatiereInexistanteException  {
        float somme = 0f;
        float totalCoeff = 0f;

        for(Matiere matiere : this.notes.keySet()){
            // On récupère le coefficient de la matiere pour l'ajouter au coeff total
            totalCoeff += matiere.getCoefficient();

            // On utilise la méthode calculerMoyenneMatiere(matiere) pour calculer la moyenne de l'étudiant pour la matière
            somme += this.calculerMoyenneMatiere(matiere) * matiere.getCoefficient();
        }
        // On vérifie que s'il n'y a pas de notes alors on retourne 0
        if(totalCoeff == 0){
            return 0;
        }
        // Puis on retourne la moyenne
        return somme/totalCoeff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Etudiant etudiant)) return false;

        if (!Objects.equals(identite, etudiant.identite)) return false;
        if (!Objects.equals(formation, etudiant.formation)) return false;
        return Objects.equals(notes, etudiant.notes);
    }

    @Override
    public int hashCode() {
        int result = identite != null ? identite.hashCode() : 0;
        result = 31 * result + (formation != null ? formation.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Etudiant o) {
        return this.identite.getNom().compareTo(o.getIdentite().getNom());
    }

}
