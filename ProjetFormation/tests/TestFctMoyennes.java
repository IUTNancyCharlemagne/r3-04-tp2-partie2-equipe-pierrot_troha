import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestFctMoyennes {

    private Formation formation;
    private Groupe groupe;
    private Matiere matiere1;
    private Matiere matiere2;
    private Etudiant etudiant1;
    private Etudiant etudiant2;

    @BeforeEach
    public void setUp() {
        formation = new Formation("Informatique");
        groupe = new Groupe(formation);
        matiere1 = new Matiere("Mathématiques", 2.0f);
        matiere2 = new Matiere("Physique", 3.0f);
        formation.ajouterMatiere(matiere1);
        formation.ajouterMatiere(matiere2);
        etudiant1 = new Etudiant(new Identite("12345", "John", "Doe"), formation);
        etudiant2 = new Etudiant(new Identite("67890", "Jane", "Smith"), formation);
    }

    @Test
    public void testCalculerMoyenneParMatiere() throws MatiereInexistanteException {
        etudiant1.ajouterNoteMatiere(matiere1, 15.0f);
        etudiant2.ajouterNoteMatiere(matiere1, 18.0f);
        etudiant1.ajouterNoteMatiere(matiere2, 12.0f);
        groupe.ajouterEtudiant(etudiant1);
        groupe.ajouterEtudiant(etudiant2);

        float moyenneMatiere1 = groupe.calculerMoyenneParMatiere(matiere1);
        float moyenneMatiere2 = groupe.calculerMoyenneParMatiere(matiere2);

        assertEquals(16.5f, moyenneMatiere1, 0.01);
        assertEquals(12.0f, moyenneMatiere2, 0.01);
    }

    @Test
    public void testCalculerMoyenneGenerale() throws MatiereInexistanteException {
        etudiant1.ajouterNoteMatiere(matiere1, 15.0f);
        etudiant2.ajouterNoteMatiere(matiere1, 18.0f);
        etudiant1.ajouterNoteMatiere(matiere2, 12.0f);
        groupe.ajouterEtudiant(etudiant1);
        groupe.ajouterEtudiant(etudiant2);

        float moyenneGenerale = groupe.calculerMoyenneGenerale();

        assertEquals(13.8f, moyenneGenerale, 0.01f);
    }

    @Test
    public void testCalculerMoyenneGeneraleSansNotes() throws MatiereInexistanteException {
        float moyenneGenerale = groupe.calculerMoyenneGenerale();
        assertEquals(0.0f, moyenneGenerale, 0.01);
    }

    @Test
    public void testCalculerMoyenneParMatiereMatiereInexistante() {
        assertThrows(MatiereInexistanteException.class, () -> {
            groupe.calculerMoyenneParMatiere(new Matiere("MatiereInexistante", 1.0f));
        });
    }
}
