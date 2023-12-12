import clavier.In;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Ihm {
    static GestionDesCoureurs gestion;
    static String surname, name;
    static Genre gender;
    static Categorie category;
    static LocalTime time;
    static Random rd = new Random();
    static int genderChoice, categoryChoice;
    static int counter = 0;
    static int position = 1;
    static int sprinterToDelete;
    static int sprinterToModify;
    static boolean isSortedIncreasing = false;
    static boolean isSortedDecreasing = false;
    static boolean isDisplayCategoryChosen = false;

    private static void displaySprinters(Coureur coureur) {
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
    }
    private static void lister() {
        System.out.println("==============================   Les coureurs sont   ==========================");
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
        if (isSortedIncreasing || isSortedDecreasing) {
            System.out.print("     Temps");
            System.out.println("\t\t\tEcart");
        } else {
            System.out.println("     Temps");
        }
        if (isDisplayCategoryChosen) {
            for (Coureur coureur : gestion.getCoureurs()) {
                if (coureur.getCategorie() == category) {
                    displaySprinters(coureur);
                    System.out.println(DateTimeFormatter.ISO_TIME.format(coureur.getDuree()));
                }
            }
        } else {
            for (Coureur coureur : gestion.getCoureurs()) {
                displaySprinters(coureur);
                if (isSortedIncreasing || isSortedDecreasing) {
                    if (isSortedIncreasing) {
                        if (counter >= 1) {
                            printTimeDifference(coureur.getDuree(), gestion.coureurs.get(0).getDuree());
                        }
                        else {
                            System.out.println(DateTimeFormatter.ISO_TIME.format(coureur.getDuree()) + "\t\t" + "00:00:00");
                        }
                    } else {
                        if (counter != gestion.coureurs.size()) {
                            printTimeDifference(coureur.getDuree(), gestion.coureurs.get(gestion.coureurs.size() - 1).getDuree());
                        } else {
                            System.out.println(DateTimeFormatter.ISO_TIME.format(gestion.coureurs.get(gestion.coureurs.size() - 1).getDuree()) + " \t\t" + "00:00:00");
                        }
                    }
                    counter++;
                } else {
                    System.out.println(DateTimeFormatter.ISO_TIME.format(coureur.getDuree()));
                }
            }
        }
        position = 1;
    }
    public static void chooseGender(int genderChoice) {
        switch (genderChoice) {
            case 1:
                gender = Genre.M;
                break;
            case 2:
                gender = Genre.F;
                break;
        }
    }
    public static void chooseCategory(int categoryChoice) {
        switch (categoryChoice) {
            case 1:
                category = Categorie.M1;
                break;
            case 2:
                category = Categorie.M2;
                break;
            case 3:
                category = Categorie.M3;
                break;
            case 4:
                category = Categorie.M4;
                break;
            case 5:
                category = Categorie.M5;
                break;
            case 6:
                category = Categorie.M6;
                break;
            case 7:
                category = Categorie.M7;
                break;
            case 8:
                category = Categorie.M8;
                break;
            case 9:
                category = Categorie.M9;
                break;
            case 10:
                category = Categorie.ELITE_1;
                break;
            case 11:
                category = Categorie.ELITE_2;
                break;
        }
    }
    public static void recognizeStatusOfSorting(int status) {
        switch (status) {
            case 2:
                gestion.sortBySurnameIncrease();
                break;
            case 3:
                gestion.sortBySurnameDecrease();
                break;
            case 4:
                gestion.sortByNameIncrease();
                break;
            case 5:
                gestion.sortByNameDecrease();
                break;
            case 6:
                gestion.sortByTimeIncrease();
                break;
            case 7:
                gestion.sortByTimeDecrease();
                break;
            case 8:
                gestion.addCategoryID();
                gestion.sortByCategoryIncrease();
                break;
            case 9:
                gestion.addCategoryID();
                gestion.sortByCategoryDecrease();
                break;
        }
    }
    public static void hideOffset() {
        isSortedIncreasing = false;
        isSortedDecreasing = false;
    }
    public static void printTimeDifference(LocalTime coureurTime, LocalTime comparedTime) {
        int differenceTimeSeconds = coureurTime.toSecondOfDay() - comparedTime.toSecondOfDay();
        int hours = differenceTimeSeconds / 3600;
        int minutes = (differenceTimeSeconds % 3600) / 60;
        int seconds = differenceTimeSeconds % 60;
        LocalTime timeDifference = LocalTime.of(hours, minutes, seconds);
        System.out.println(DateTimeFormatter.ISO_TIME.format(coureurTime) + " \t\t" + DateTimeFormatter.ISO_TIME.format(timeDifference));
    }
    public static String formattedSurnameName(String string) {
        String formattedString;
        formattedString = string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
        return formattedString;
    }
    public static void main(String[] args) {
        try {
            int choice = 0;
            int status = 0;
            gestion = new GestionDesCoureurs();
            while (choice != 15) {
                boolean isDeleted = false;
                boolean isModified = false;
                time = LocalTime.of(1, 0);
                time = time.plusSeconds(rd.nextInt(2, 7200));

                System.out.println(".............Les coureurs.............");
                System.out.println("Tapez 1 pour lire la liste actuelle des coureurs dans le fichier");
                System.out.println("Tapez 2 pour afficher par ordre alphabétique de nom croissant");
                System.out.println("Tapez 3 pour afficher par ordre alphabétique de nom decroissant");
                System.out.println("Tapez 4 pour afficher par ordre alphabétique de prenom croissant");
                System.out.println("Tapez 5 pour afficher par ordre alphabétique de prenom decroissant");
                System.out.println("Tapez 6 pour afficher par ordre de classement croissant");
                System.out.println("Tapez 7 pour afficher par ordre de classement decroissant");
                System.out.println("Tapez 8 pour afficher par ordre de categorie croissant");
                System.out.println("Tapez 9 pour afficher par ordre de categorie decroissant");
                System.out.println("Tapez 10 pour afficher par categorie choisie");
                System.out.println("Tapez 11 pour ajouter un coureur");
                System.out.println("Tapez 12 pour supprimer un coureur");
                System.out.println("Tapez 13 pour modifier un coureur");
                System.out.println("Tapez 14 pour sauvegarder la liste des coureurs");
                System.out.println("Tapez 15 pour arreter votre session");
                System.out.print("Votre choix: ");
                choice = In.readInteger();
                switch (choice) {
                    case 1:
                        recognizeStatusOfSorting(status);
                        lister();
                        break;
                    case 2:
                        gestion.sortBySurnameIncrease();
                        hideOffset();
                        lister();
                        status = 2;
                        break;
                    case 3:
                        gestion.sortBySurnameDecrease();
                        hideOffset();
                        lister();
                        status = 3;
                        break;
                    case 4:
                        gestion.sortByNameIncrease();
                        hideOffset();
                        lister();
                        status = 4;
                        break;
                    case 5:
                        gestion.sortByNameDecrease();
                        hideOffset();
                        lister();
                        status = 5;
                        break;
                    case 6:
                        gestion.sortByTimeIncrease();
                        isSortedDecreasing = false;
                        isSortedIncreasing = true;
                        lister();
                        status = 6;
                        break;
                    case 7:
                        gestion.sortByTimeDecrease();
                        isSortedIncreasing = false;
                        isSortedDecreasing = true;
                        lister();
                        status = 7;
                        break;
                    case 8:
                        gestion.addCategoryID();
                        gestion.sortByCategoryIncrease();
                        hideOffset();
                        lister();
                        status = 8;
                        break;
                    case 9:
                        gestion.addCategoryID();
                        gestion.sortByCategoryDecrease();
                        hideOffset();
                        lister();
                        status = 9;
                        break;
                    case 10:
                        isDisplayCategoryChosen = true;
                        System.out.println("Choisissez categorie");
                        System.out.println("1 - M1; 2 - M2; 3 - M3; 4 - M4; 5 - M5; 6 - M6; 7 - M7; 8 - M8; 9 - M9; 10 - ELITE_1; 11 - ELITE_2");
                        System.out.print("Votre choix: ");
                        categoryChoice = In.readInteger();
                        chooseCategory(categoryChoice);
                        lister();
                        isDisplayCategoryChosen = false;
                        recognizeStatusOfSorting(status);
                        break;
                    case 11:
                        System.out.println("Entrez les donnees pour votre coureur");

                        System.out.println("Le genre de votre coureur...");
                        System.out.println("1 - masculin; 2 - feminin");
                        System.out.print("Votre choix: ");
                        genderChoice = In.readInteger();
                        chooseGender(genderChoice);

                        System.out.print("Le nom de votre coureur: ");
                        surname = In.readString();
                        assert surname != null;
                        surname = formattedSurnameName(surname);

                        System.out.print("Le prenom de votre coureur: ");
                        name = In.readString();
                        assert name != null;
                        name = formattedSurnameName(name);

                        System.out.println("La categorie de votre coureur...");
                        System.out.println("1 - M1; 2 - M2; 3 - M3; 4 - M4; 5 - M5; 6 - M6; 7 - M7; 8 - M8; 9 - M9; 10 - ELITE_1; 11 - ELITE_2");
                        System.out.print("Votre choix: ");
                        categoryChoice = In.readInteger();
                        chooseCategory(categoryChoice);

                        Coureur coureur = new Coureur(gender, surname, name, category, time);
                        gestion.coureurs.add(coureur);
                        System.out.println("Le coureur " + surname + " " + name + " a ete cree.");
                        recognizeStatusOfSorting(status);
                        break;
                    case 12:
                        while (!isDeleted) {
                            System.out.print("Choisissez un coureur qui vous voulez supprimer en entrant son numero: ");
                            sprinterToDelete = In.readInteger();

                            for (int i = 0; i < gestion.coureurs.size() && !isDeleted; i++) {
                                if (i == sprinterToDelete - 1) {
                                    System.out.println("Le coureur " +  gestion.coureurs.get(i).getNom() + " " + gestion.coureurs.get(i).getPrenom() + " a ete supprime.");
                                    gestion.coureurs.remove(i);
                                    isDeleted = true;
                                }
                            }
                            if (!isDeleted) {
                                System.out.println("Le coureur N° " + sprinterToDelete + " n'a pas ete trouve.");
                                System.out.println("Le coureur n'a pas ete supprime. Veuillez reessayer.");
                            }
                        }
                        recognizeStatusOfSorting(status);
                        break;
                    case 13:
                        while (!isModified) {
                            System.out.print("Choisissez un coureur qui vous voulez modifier en entrant son numero: ");
                            sprinterToModify = In.readInteger();
                            for (int i = 0; i < gestion.coureurs.size() && !isModified; i++) {
                                if (i == sprinterToModify - 1) {
                                    System.out.println("... Modification de coureur " + gestion.coureurs.get(i).getNom() + " " + gestion.coureurs.get(i).getPrenom() + " ...");

                                    System.out.println("Saisissez un nouveau genre de ce coureur...");
                                    System.out.println("1 - masculin; 2 - feminin");
                                    System.out.print("Votre choix: ");
                                    genderChoice = In.readInteger();
                                    chooseGender(genderChoice);

                                    System.out.print("Saisissez un nouveau nom de ce coureur: ");
                                    surname = In.readString();
                                    assert surname != null;
                                    surname = formattedSurnameName(surname);

                                    System.out.print("Saisissez un nouveau prenom de ce coureur: ");
                                    name = In.readString();
                                    assert name != null;
                                    name = formattedSurnameName(name);

                                    System.out.println("Saississez une nouvelle categorie de ce coureur...");
                                    System.out.println("1 - M1; 2 - M2; 3 - M3; 4 - M4; 5 - M5; 6 - M6; 7 - M7; 8 - M8; 9 - M9; 10 - ELITE_1; 11 - ELITE_2");
                                    System.out.print("Votre choix: ");
                                    categoryChoice = In.readInteger();
                                    chooseCategory(categoryChoice);

                                    System.out.println("Le coureur " + gestion.coureurs.get(i).getNom() + " " + gestion.coureurs.get(i).getPrenom() + " a ete modifie.");

                                    gestion.coureurs.get(i).setGender(gender);
                                    gestion.coureurs.get(i).setNom(surname);
                                    gestion.coureurs.get(i).setPrenom(name);
                                    gestion.coureurs.get(i).setCategorie(category);
                                    gestion.coureurs.get(i).setDuree(time);

                                    isModified = true;
                                }
                            }
                            if (!isModified) {
                                System.out.println("Le coureur " + sprinterToModify + " n'a pas ete trouve.");
                                System.out.println("Le coureur n'a pas ete modifie.");
                            }
                        }
                        recognizeStatusOfSorting(status);
                        break;
                    case 14:
                        Path courseSource = Paths.get("course.txt");
                        BufferedWriter bw;
                        bw = Files.newBufferedWriter(courseSource, Charset.defaultCharset());
                        for (Coureur c : gestion.coureurs) {
                            bw.write(c.getGender() + ", " + c.getNom() + ", " + c.getPrenom() + ", " + c.getCategorie() + ", " + c.getDuree().toSecondOfDay());
                            bw.newLine();
                        }
                        bw.close();
                        System.out.println("Votre fichier " + courseSource.getFileName() + " a ete sauvegarde.");
                        break;
                    case 15:
                        System.out.print("Votre session est arretee.");
                        break;
                }
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}