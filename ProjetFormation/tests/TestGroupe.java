import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestGroupe {

    Groupe groupe;
    Etudiant mickeyMouse, donaldDuck, minnieMouse, patHibulaire;

    @BeforeEach
    public void setUp() {
        groupe = new Groupe(new Formation("ufr"));
        mickeyMouse = new Etudiant(new Identite("etu@1", "Mouse", "Mickey"), groupe.getFormation());
        donaldDuck = new Etudiant(new Identite("etu@2", "Duck", "Donald"), groupe.getFormation());
        minnieMouse = new Etudiant(new Identite("etu@3", "Mouse", "Minnie"), groupe.getFormation());
        patHibulaire = new Etudiant(new Identite("etu@4", "Hibulaire", "Pat"), groupe.getFormation());
    }

    // test d'ajout correct d'Etudiant dans le groupe
    @Test
    public void testAjouterEtudiant_ok(){
        assertTrue(groupe.ajouterEtudiant(mickeyMouse));
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

    @Test
    public void trierListe(){
        //initialisation
        groupe.ajouterEtudiant(mickeyMouse);
        groupe.ajouterEtudiant(donaldDuck);
        groupe.ajouterEtudiant(patHibulaire);

        //test
        groupe.triAlpha();

        //vérification
        assertEquals("Duck-Donald : ufr\nHibulaire-Pat : ufr\nMouse-Mickey : ufr\n", groupe.toString());
    }
    
    @Test
    public void trierListeAntiAlpha(){
        //initialisation
        groupe.ajouterEtudiant(mickeyMouse);
        groupe.ajouterEtudiant(donaldDuck);
        groupe.ajouterEtudiant(patHibulaire);

        //test
        groupe.triAntiAlpha();

        //vérification
        assertEquals("Mouse-Mickey : ufr\nHibulaire-Pat : ufr\nDuck-Donald : ufr\n", groupe.toString());
    }
}
