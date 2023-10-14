import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestFormation {

    Formation ufr;
    Matiere philo;

    @BeforeEach
    public void setUp() {
        //initialisation de la formation
        Formation ufr = new Formation("ufr");

        //initialisation d'une matière
        Matiere philo = new Matiere("Philo", 8.0F);
    }

    // ajouter matière ok
    @Test
    public void testAjouterMatiere_OK(){
        //vérification
        assertTrue(ufr.ajouterMatiere(philo));
    }

    //ajouter matière deja existante
    @Test
    public void testAjouterMatiere_PasOK(){
        //initialisation
        ufr.ajouterMatiere(philo);
        Matiere nouveauPhilo = new Matiere("Philo", 8.0F);

        //vérification
        assertFalse(ufr.ajouterMatiere(nouveauPhilo));
    }

    //retirer matière ok
    @Test
    public void testSupprimerMatiere_OK(){
        //initialisation
        ufr.ajouterMatiere(philo);

        //vérification
        assertTrue(ufr.supprimerMatiere(philo));
    }

    //retirer matière mais liste de matière vide
    @Test
    public void testSupprimerMatiere_listeVide(){
        //vérification
        assertFalse(ufr.supprimerMatiere(new Matiere("Philo", 8.0F)));
    }


    // récupérer coefficient matière
    @Test
    public void testRecupererCoef_OK() throws MatiereInexistanteException {
        //initialisation
        ufr.ajouterMatiere(philo);

        //vérification
        assertEquals(ufr.getCoefficient(philo), 8.0F);
    }

    @Test
    public void testRecupererCoefMauvaiseMatiere() {
        //initialisation
        ufr.ajouterMatiere(philo);

        //vérification
        assertThrows(MatiereInexistanteException.class, () -> {
            ufr.getCoefficient(new Matiere("Maths", 16.0F));
        });
    }

    @Test
    public void testRecupererCoefListeVide(){
        //vérification
        assertThrows(MatiereInexistanteException.class, () -> {
            ufr.getCoefficient(new Matiere("Maths", 16.0F));
        });
    }
}
