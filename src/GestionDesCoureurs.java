import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;

public class GestionDesCoureurs {
    ArrayList<Coureur> coureurs = new ArrayList<>();
    public ArrayList<Coureur> getCoureurs() {
        return coureurs;
    }

    public GestionDesCoureurs() throws IOException {
        Path fileSource = Paths.get("course.txt");
        BufferedReader bd;
        String s;
        Genre gender = null;
        Categorie category = null;
        int seconds;
        int hours;
        int minutes;
        int remainingSeconds;

        if (!Files.exists(fileSource)) {
            throw new RuntimeException("Votre fichier n'existe pas");
        }

        bd = Files.newBufferedReader(fileSource, Charset.defaultCharset());

        while ((s = bd.readLine()) != null) {
            String [] split = s.split(",");
            gender = switch (split[0].trim()) {
                case "M" -> Genre.M;
                case "F" -> Genre.F;
                default -> gender;
            };
            category = switch (split[3].trim()) {
                case "M1" -> Categorie.M1;
                case "M2" -> Categorie.M2;
                case "M3" -> Categorie.M3;
                case "M4" -> Categorie.M4;
                case "M5" -> Categorie.M5;
                case "M6" -> Categorie.M6;
                case "M7" -> Categorie.M7;
                case "M8" -> Categorie.M8;
                case "M9" -> Categorie.M9;
                case "ELITE_1" -> Categorie.ELITE_1;
                case "ELITE_2" -> Categorie.ELITE_2;
                default -> category;
            };

            seconds = Integer.parseInt(split[4].trim());
            hours = seconds / 3600;
            minutes = (seconds % 3600) / 60;
            remainingSeconds = seconds % 60;
            LocalTime time = LocalTime.of(hours, minutes, remainingSeconds);

            coureurs.add(new Coureur(gender, split[1].trim(), split[2].trim(), category, time));
        }
        bd.close();
    }

    public void sortByNameIncrease() {
        coureurs.sort(Comparator.comparing(Coureur::getPrenom));
    }
    public void sortBySurnameIncrease() {
        coureurs.sort(Comparator.comparing(Coureur::getNom));
    }
    public void sortByTimeIncrease() {
        coureurs.sort(Comparator.comparing(Coureur::getDuree));
    }
    public void sortByNameDecrease() {
        coureurs.sort(Comparator.comparing(Coureur::getPrenom).reversed());
    }
    public void sortBySurnameDecrease() {
        coureurs.sort(Comparator.comparing(Coureur::getNom).reversed());
    }
    public void sortByTimeDecrease() {
        coureurs.sort(Comparator.comparing(Coureur::getDuree).reversed());
    }
}