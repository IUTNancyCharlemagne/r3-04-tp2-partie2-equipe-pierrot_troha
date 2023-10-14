public class Identite {

    /**
     * Attributs décrivant l'identité d'un étudiant.
     */
    private String nip, nom, prenom;


    /**
     *  Constructeur recevant en paramètres le NIP, nom et prenom d'un étudiant pour lui construire son identité
     * @param nip numéro d'identification personnel
     * @param n nom
     * @param p prénom
     */
    public Identite(String nip, String n,String p){
        this.nip = nip;
        this.nom = n;
        this.prenom = p;
    }

}
