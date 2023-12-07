import clavier.In;

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
            System.out.print(coureur.getNom())  ;
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
        try {
            gestion = new GestionDesCoureurs();
            int choix;
            System.out.println(".............Les coureurs.............");
            System.out.println("Tapez 1 pour lire la liste des coureurs dans le fichier");
            System.out.println("Tapez 2 pour afficher par ordre alphabétique de leur nom croissant");
            System.out.println("Tapez 3 pour afficher par ordre alphabétique de leur nom decroissant");
            System.out.println("Tapez 4 pour afficher par ordre alphabétique de leur prenom croissant");
            System.out.println("Tapez 5 pour afficher par ordre alphabétique de leur prenom decroissant");
            System.out.println("Tapez 6 pour afficher par ordre de classement croissant");
            System.out.println("Tapez 7 pour afficher par ordre de classement decroissant");
            System.out.println("Tapez 8 pour afficher par ordre de temps croissant");
            System.out.println("Tapez 9 pour afficher par ordre de temps croissant");
            System.out.println("Tapez 10 pour ajouter un coureur");
            System.out.println("Tapez 11 pour supprimer un coureur");
            System.out.println("Tapez 12 pour modifier un coureur");
            System.out.println("Tapez 13 pour sauvegarder la liste des coureurs");
            System.out.println("Votre choix: ");
            choix = In.readInteger();
            switch (choix) {
                case 1:
                    lister();
                    break;
                case 2:
                    gestion.sortBySurnameIncrease();
                    lister();
                    break;
                case 3:
                    gestion.sortBySurnameDecrease();
                    lister();
                    break;
                case 4:
                    gestion.sortByNameIncrease();
                    break;
                case 5:
                    gestion.sortByNameDecrease();
                    break;
                case 6:
                    gestion.sortByCategoryIncrease();
                    break;
                case 7:
                    gestion.sortByCategoryDecrease();
                    break;
                case 8:
                    gestion.sortByTimeIncrease();
                    break;
                case 9:
                    gestion.sortByTimeDecrease();
                    break;
                case 10:
                    System.out.println("Entrez les donnees pour votre coureur");
                    Coureur coureur = new Coureur();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}