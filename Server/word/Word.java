package Game.Server.word;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Word {

    private static ArrayList<String> lOfWords = new ArrayList<>();

    public static String randomWord(){
        try( Scanner sc=new Scanner(new File("/Users/Konstantinos/Downloads/Hangman-Game-master/src/Game/words.txt"))) {
            while(sc.hasNext()){
                String str=sc.next();
                lOfWords.add(str);
            }
        } catch (FileNotFoundException e) {
            System.out.println("FILE IO ERROR: " + e.getMessage());
        }
        int index=new Random().nextInt(lOfWords.size());
        return lOfWords.get(index);
    }
}



