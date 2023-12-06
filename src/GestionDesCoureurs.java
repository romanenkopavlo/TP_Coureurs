import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

public class GestionDesCoureurs {
    ArrayList<Coureur> coureurs = new ArrayList<>();
    public ArrayList<Coureur> getCoureurs() {
        return coureurs;
    }

    public void setCoureurs() throws IOException {
        Random rd = new Random();
        LocalTime time;
        Path fileSource = Paths.get("lescoureurs.txt");
        Path fileSourceFinal = Paths.get("lescoureursfinal.txt");
        BufferedReader bd;
        BufferedWriter bw;
        int linesCounter = 0;
        String s;

        if (!Files.exists(fileSource)) {
            throw new RuntimeException("Votre fichier n'existe pas");
        }

        bd = Files.newBufferedReader(fileSource, Charset.defaultCharset());
        bw = Files.newBufferedWriter(fileSourceFinal, Charset.defaultCharset());

        while ((s = bd.readLine()) != null) {
            time = LocalTime.of(1,0);
            String substring = s.substring(s.indexOf("'"), s.length() - 2);
            String [] split = substring.split(",");
            time = time.plusSeconds(rd.nextInt(2, 7200));
            Categorie categorie = switch (Integer.parseInt(split[2].trim())) {
                case 1 -> Categorie.M1;
                case 2 -> Categorie.M2;
                case 3 -> Categorie.M3;
                case 4 -> Categorie.M4;
                case 5 -> Categorie.M5;
                case 6 -> Categorie.M6;
                case 7 -> Categorie.M7;
                case 8 -> Categorie.M8;
                case 9 -> Categorie.M9;
                case 10 -> Categorie.ELITE_1;
                case 11 -> Categorie.ELITE_2;
                default -> null;
            };
            if (linesCounter < 500) {
                coureurs.add(new Coureur(Genre.M, split[0].trim().substring(split[0].indexOf("'") + 1, split[0].length() - 1), split[1].trim().substring(split[1].indexOf("'"), split[1].length() - 2), categorie, time));
                linesCounter++;
            } else {
                coureurs.add(new Coureur(Genre.F, split[0].trim().substring(split[0].indexOf("'")+ 1, split[0].length() - 1), split[1].trim().substring(split[1].indexOf("'"), split[1].length() - 2), categorie, time));
            }
        }

        for (Coureur coureur : coureurs) {
            bw.write(coureur.getGender() + ", " + coureur.getNom() + ", " + coureur.getPrenom() + ", " + coureur.getCategorie() + ", " + coureur.getDuree());
            bw.newLine();
        }
        bw.close();
        bd.close();
    }
}