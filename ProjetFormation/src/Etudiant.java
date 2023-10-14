import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Etudiant {
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
        for (Matiere matiere : this.formation.getMatieres()) {
            this.notes.put(matiere, new ArrayList<Float>()); // Ajoutez la matière en tant que clé avec une liste vide en valeur
        }
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
            return somme/eff;
        }
    }

    /**
     * Permet de calculer la moyenne générale d'un étudiant tout en prenant en compte les coefficients différents.
     * @return la moyenne générale d'un étudiant
     */
    public float calculerMoyenneGenerale() throws MatiereInexistanteException  {
        float somme = 0;
        float totalCoeff = 0;

        for(Matiere matiere : this.notes.keySet()){
            // On récupère le coefficient de la matiere pour l'ajouter au coeff total
            totalCoeff += matiere.getCoefficient();

            // On utilise la méthode calculerMoyenneMatiere(matiere) pour calculer la moyenne de l'étudiant pour la matière
            somme += this.calculerMoyenneMatiere(matiere) * matiere.getCoefficient();
        }
        // Puis on retourne la moyenne
        return somme/totalCoeff;
    }
}
