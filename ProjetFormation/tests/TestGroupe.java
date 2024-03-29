import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestGroupe {

    Groupe groupe;
    Etudiant mickeyMouse, donaldDuck, minnieMouse, patHibulaire, ririDuck;
    Matiere matiere1, matiere2, matiere3;

    Formation formation;

    @BeforeEach
    public void setUp() {
        formation = new Formation("ufr");

        groupe = new Groupe(formation);


        matiere1 = new Matiere("Mathématiques", 2.0f);
        matiere2 = new Matiere("Physique", 3.0f);
        matiere3 = new Matiere("SVT", 1.0f);

        formation.ajouterMatiere(matiere1);
        formation.ajouterMatiere(matiere2);
        formation.ajouterMatiere(matiere3);

        mickeyMouse = new Etudiant(new Identite("etu@1", "Mouse", "Mickey"), groupe.getFormation());
        donaldDuck = new Etudiant(new Identite("etu@2", "Duck", "Donald"), groupe.getFormation());
        minnieMouse = new Etudiant(new Identite("etu@3", "Mouse", "Minnie"), groupe.getFormation());
        patHibulaire = new Etudiant(new Identite("etu@4", "Hibulaire", "Pat"), groupe.getFormation());
        ririDuck = new Etudiant(new Identite("etu@5", "Duck", "Riri"), groupe.getFormation());
        try{
            mickeyMouse.ajouterNoteMatiere(matiere1, 15.0f);
            mickeyMouse.ajouterNoteMatiere(matiere2, 12.0f);
            mickeyMouse.ajouterNoteMatiere(matiere3, 19.0f);

            donaldDuck.ajouterNoteMatiere(matiere1, 5.0f);
            donaldDuck.ajouterNoteMatiere(matiere2, 9.0f);
            donaldDuck.ajouterNoteMatiere(matiere3, 11.0f);

            minnieMouse.ajouterNoteMatiere(matiere1, 18.0f);
            minnieMouse.ajouterNoteMatiere(matiere2, 16.0f);
            minnieMouse.ajouterNoteMatiere(matiere3, 17.0f);

            patHibulaire.ajouterNoteMatiere(matiere1, 19.0f);
            patHibulaire.ajouterNoteMatiere(matiere2, 10.0f);
            patHibulaire.ajouterNoteMatiere(matiere3, 10.0f);

            ririDuck.ajouterNoteMatiere(matiere1, 19.0f);
            ririDuck.ajouterNoteMatiere(matiere2, 10.0f);
            ririDuck.ajouterNoteMatiere(matiere3, 10.0f);
        }catch(MatiereInexistanteException e){
            e.printStackTrace();
        }

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

    @Test
    public void trierListeParMerite_Ok() throws MatiereInexistanteException{
        /*
        * Moyenne de chaque élève :
        *   Minnie : 17,17
        *   Mickey : 14,67
        *   Pat    : 14,5
        *   Donald : 7,33
        */
        //initialisation
        groupe.ajouterEtudiant(mickeyMouse);
        groupe.ajouterEtudiant(donaldDuck);
        groupe.ajouterEtudiant(patHibulaire);
        groupe.ajouterEtudiant(minnieMouse);

        //test
        groupe.triParMerite();

        assertEquals("Mouse-Minnie : ufr\nMouse-Mickey : ufr\nHibulaire-Pat : ufr\nDuck-Donald : ufr\n", groupe.toString());
    }

    @Test
    public void trierListeParMeriteEtMemeMoyenneOk() throws MatiereInexistanteException{
        /*
         * Moyenne de chaque élève :
         *   Pat    : 14,5
         *   Riri : 14,5
         */
        //initialisation
        groupe.ajouterEtudiant(patHibulaire);
        groupe.ajouterEtudiant(ririDuck);

        //test riri doit être avant pat car cela tri par ordre alphabéitque
        groupe.triParMerite();

        assertEquals("Duck-Riri : ufr\nHibulaire-Pat : ufr\n", groupe.toString());
    }
}
