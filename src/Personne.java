public class Personne {
    private Genre gender;
    private String nom;
    private String prenom;

    public Genre getGender() {
        return gender;
    }

    public void setGender(Genre gender) {
        this.gender = gender;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Personne(Genre genre, String nom, String prenom) {
        this.setGender(genre);
        this.setNom(nom);
        this.setPrenom(prenom);
    }
}