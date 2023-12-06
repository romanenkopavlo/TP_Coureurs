import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class Ihm {
    static GestionDesCoureurs gestion;
    private static void lister () {
        int position = 1;
        System.out.println("==============================   les coureurs sont   ==========================");
        System.out.print("            Nom" );
        for (int i = 0; i < 11 - "Nom".length(); i++) {
            System.out.print("  ");
        }
        System.out.print(" Prénom" );
        for (int i = 0; i < 12 - "Prénom".length(); i++) {
            System.out.print("  ");
        }
        System.out.print("  Catégorie" );
        for (int i = 0; i < 12 - "Catégorie".length(); i++) {
            System.out.print("  ");
        }
        System.out.println("     Temps");
        for (Coureur coureur : gestion.getCoureurs()) {
            //System.out.print(position++ + "   " + coureur.getGenre() + "  ");

            for(int i = 0; i < (4 - Integer.toString(position).length()); i++) {
                System.out.print(" ");
            }
            System.out.print(position++ + "   " + coureur.getGender() + "  ");
            if(position <= 10) {
                System.out.print("  ");
            }
            System.out.print(coureur.getNom());
            for (int i = 0; i < 20 - coureur.getNom().length(); i++) {
                System.out.print(" ");
            }
            System.out.print(coureur.getPrenom());
            for (int i = 0; i < 20 - coureur.getPrenom().length(); i++) {
                System.out.print(" ");
            }

            System.out.print(coureur.getCategorie());
            for (int i = 0; i < 20 - coureur.getCategorie().toString().length(); i++) {
                System.out.print(" ");
            }

            System.out.println(DateTimeFormatter.ISO_TIME.format(coureur.getDuree()));
        }
    }
    public static void main(String[] args) {
        gestion = new GestionDesCoureurs();
        try {
            gestion.setCoureurs();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        lister();
    }
}