import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestFormation {


    // ajouter matière ok
    @Test
    public void testAjouterMatiere_OK(){
        //initialisation des objets
        Formation ufr = new Formation("ufr");
        Matiere philo = new Matiere("Philo", 8.0F);

        //vérification
        assertTrue(ufr.ajouterMatiere(philo));
    }

    //ajouter matière deja existante
    @Test
    public void testAjouterMatiere_PasOK(){
        //initialisation des objets
        Formation ufr = new Formation("ufr");
        Matiere philo = new Matiere("Philo", 8.0F);
        ufr.ajouterMatiere(philo);
        Matiere nouveauPhilo = new Matiere("Philo", 8.0F);

        //vérification
        assertFalse(ufr.ajouterMatiere(nouveauPhilo));
    }

    //retirer matière ok
    @Test
    public void testSupprimerMatiere_OK(){
        //initialisation des objets
        Formation ufr = new Formation("ufr");
        Matiere philo = new Matiere("Philo", 8.0F);
        ufr.ajouterMatiere(philo);

        //vérification
        assertTrue(ufr.supprimerMatiere(philo));
    }

    //retirer matière mais liste de matière vide
    @Test
    public void testSupprimerMatiere_listeVide(){
        //initialisation des objets
        Formation ufr = new Formation("ufr");

        //vérification
        assertFalse(ufr.supprimerMatiere(new Matiere("Philo", 8.0F)));
    }


    // récupérer coefficient matière
    @Test
    public void testRecupererCoef_OK() throws MatiereInexistanteException {
        //initialisation des objets
        Formation ufr = new Formation("ufr");
        Matiere philo = new Matiere("Philo", 8.0F);
        ufr.ajouterMatiere(philo);

        //vérification
        assertEquals(ufr.getCoefficient(philo), 8.0F);
    }

    // récupérer coefficient matière inexistante
    @Test
    public void testRecupererCoefMauvaiseMatiere() {
        //initialisation des objets
        Formation ufr = new Formation("ufr");
        Matiere philo = new Matiere("Philo", 8.0F);
        ufr.ajouterMatiere(philo);

        //vérification
        assertThrows(MatiereInexistanteException.class, () -> {
            ufr.getCoefficient(new Matiere("Maths", 16.0F));
        });
    }

    // récupérer coefficient matière inexistante
    @Test
    public void testRecupererCoefListeVide(){
        //initialisation des objets
        Formation ufr = new Formation("ufr");

        //vérification
        assertThrows(MatiereInexistanteException.class, () -> {
            ufr.getCoefficient(new Matiere("Maths", 16.0F));
        });
    }
}
