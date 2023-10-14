import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestGroupe {

    Groupe groupe;
    Etudiant mickeyMouse;

    @BeforeEach
    public void setUp() {
        groupe = new Groupe(new Formation("ufr"));
        mickeyMouse = new Etudiant(new Identite("etu@1", "Mouse", "Mickey"), groupe.getFormation());
    }

    // test d'ajout correct d'Etudiant dans le groupe
    @Test
    public void testAjouterEtudiant_ok(){
        assertTrue(groupe.ajouterEtudiant(new Etudiant(new Identite("etu@1", "Mouse", "Mickey"), groupe.getFormation())));
    }

    // test d'ajout incorrect d'Etudiant dans le groupe (mauvaise Formation)
    @Test
    public void testAjouterEtudiant_MauvaiseFormation(){
        assertFalse(groupe.ajouterEtudiant(new Etudiant(new Identite("etu@1", "Mouse", "Mickey"), new Formation("MPSI"))));
    }

    // test d'ajout incorrect d'Etudiant dans le groupe (étudiant déjà présent)
    @Test
    public void testAjouterEtudiant_DejaPresent(){
        //initialisation
        groupe.ajouterEtudiant(mickeyMouse);

        //vérification
        assertFalse(groupe.ajouterEtudiant(mickeyMouse));
    }

    @Test
    public void supprimerEtudiant_Ok(){
        //initialisation
        groupe.ajouterEtudiant(mickeyMouse);

        //vérification
        assertTrue(groupe.supprimerEtudiant(mickeyMouse));
    }

    @Test
    public void supprimerEtudiant_PasDansLeGroupe(){
        assertFalse(groupe.supprimerEtudiant(mickeyMouse));
    }

}
