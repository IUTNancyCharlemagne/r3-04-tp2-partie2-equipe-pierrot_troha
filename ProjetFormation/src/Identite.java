import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Identite identite)) return false;

        if (!Objects.equals(nip, identite.nip)) return false;
        if (!Objects.equals(nom, identite.nom)) return false;
        return Objects.equals(prenom, identite.prenom);
    }

    @Override
    public int hashCode() {
        int result = nip != null ? nip.hashCode() : 0;
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (prenom != null ? prenom.hashCode() : 0);
        return result;
    }
}
