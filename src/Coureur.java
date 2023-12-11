import java.time.LocalTime;

public class Coureur extends Personne {
    private Categorie categorie;
    private LocalTime duree;
    private int categorieID;

    public int getCategorieID() {
        return categorieID;
    }

    public void setCategorieID(int categorieID) {
        this.categorieID = categorieID;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public LocalTime getDuree() {
        return duree;
    }

    public void setDuree(LocalTime duree) {
        this.duree = duree;
    }

    public Coureur() {
    }
    public Coureur(Genre genre, String nom, String prenom, Categorie categorie, LocalTime time) {
        super(genre, nom, prenom);
        this.setCategorie(categorie);
        this.setDuree(time);
    }
}