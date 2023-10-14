import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
public class TestEtudiant {

    // On prépare les objets dont on aura besoin
    private Etudiant etudiant;
    private Matiere matiere1;
    private Matiere matiere2;



    @BeforeEach
    public void setUp(){
        // Crée un étudiant avec une liste de matières
        Identite identite = new Identite("ETU@1","Messi", "Lionel");
        Formation formation = new Formation("FORM@1");
        matiere1 = new Matiere("Philo", 1.5f);
        matiere2 = new Matiere("Mathématiques", 2.0f);
        formation.ajouterMatiere(matiere1);
        formation.ajouterMatiere(matiere2);
        etudiant = new Etudiant(identite, formation);
    }


    @Test
    public void testAjouterNoteMatiere() throws MatiereInexistanteException {
        etudiant.ajouterNoteMatiere(matiere1, 15.0f);
        List<Float> notesMatiere1 = etudiant.getNotes().get(matiere1);
        assertEquals(1, notesMatiere1.size());
        assertEquals(15.0f, notesMatiere1.get(0));
    }

    @Test
    public void testAjouterMatiereInexistante() throws MatiereInexistanteException {
        assertThrows(MatiereInexistanteException.class, () -> {
            etudiant.ajouterNoteMatiere(new Matiere("MatiereInexistante", 1.0f), 10.0f);
        });
    }


    @Test
    public void testCalculerMoyenneMatiere() throws MatiereInexistanteException {
        etudiant.ajouterNoteMatiere(matiere1, 15.0f);
        etudiant.ajouterNoteMatiere(matiere1, 18.0f);
        float moyenneMatiere1 = etudiant.calculerMoyenneMatiere(matiere1);
        assertEquals(16.5f, moyenneMatiere1, 0.01);
    }

    @Test
    public void testCalculerMoyenneGenerale() throws MatiereInexistanteException {
        etudiant.ajouterNoteMatiere(matiere1, 15.0f);
        etudiant.ajouterNoteMatiere(matiere1, 18.0f);
        etudiant.ajouterNoteMatiere(matiere2, 12.0f);
        float moyenneGenerale = etudiant.calculerMoyenneGenerale();
        assertEquals(13.28f, moyenneGenerale, 1);
    }

    @Test
    public void testAjouterNoteMatiereNoteInvalideInferieure() throws MatiereInexistanteException {
            etudiant.ajouterNoteMatiere(matiere1, -5.0f);
            assertEquals(0.0f, etudiant.getNotes().get(matiere1).get(0));
    }

    @Test
    public void testAjouterNoteMatiereNoteInvalideSuperieure() throws MatiereInexistanteException {
        etudiant.ajouterNoteMatiere(matiere1, 21.0f);
        assertEquals(20.0f, etudiant.getNotes().get(matiere1).get(0));
    }

    @Test
    public void testCalculerMoyenneGeneralePasDeNotes() throws MatiereInexistanteException {
        float moyenneGenerale = etudiant.calculerMoyenneGenerale();
        assertEquals(0.0f, moyenneGenerale, 1);
    }
}
