package Game.Server.model;

import Game.Server.word.Word;
import java.util.Arrays;

public class Game {

    //INPUT FORMATTING
    private String stream;
    private String word;
    private char[] output;
    //SCORE FIELDS
    private static final int NOF_TRIES = 2;
    private int currTries = 0;
    private int score=0;
    private static final String WON="you won, type start to play again or exit to exit";
    private static final String LOST="you lost, type start to play again or exit to exit";
    private static final String IN_GAME="keep playing";
    private static final String WELCOME="Welcome buddy))";

    //SETTER
    public void setStream(String str) {
        this.stream = str;
        hangman(str);
    }

    //GETTER
    public String getStream() {
        return this.stream;
    }

    //INITIALIZE ARRAY WHERE IT STORES
    private void initVariables(){
        //INITIALIZE WORD
        this.word =Word.randomWord();
        System.out.println(this.word);
        //INITIALIZE WORD ARRAY
        this.output =new char[word.length()];
        for(int i = 0; i<this.word.length(); i++){
            this.output[i]='_';
        }
    }

    //CALLED UPON START
    private void initValues() {
        initVariables();
        refreshOutput();
    }

    private boolean assertStart(){
        int counter=0;
        for (char anOutput : output) {
            if (anOutput == '_') counter++;
        }
        return counter==output.length;
    }

    //CASE WHERE 1 CHARACTER IS ENTERED
    private void oneChar(){
        char ch=this.stream.charAt(0);
        correctChar(ch);
        wrongChar(ch);
        refreshOutput();
    }

    //IF THE ENTERED CHAR IS CORRECT
    private void correctChar(char ch){
        for(int i = 0; i<this.word.length(); i++){
            if(this.word.charAt(i)==ch) this.output[i]=ch;
        }
        this.stream =Arrays.toString(output);
    }

    //IF THE ENTER CHAR IS WRONG
    private void wrongChar(char ch){
        int counter =0;
        for(int i = 0; i<this.word.length(); i++){
            if(this.word.charAt(i)!=ch)counter++;
        }
        if(counter==word.length()) this.currTries++;
    }

    //IF THE GUESSED WORD IS CORRECT OR WRONG
    private void wholeWord(){
        if(this.stream.equals(this.word)){
            this.stream ="you won";
        }
        else{
            this.stream ="wrong guess, try again";
            this.currTries++;
        }
    }

    //LOST BECAUSE OF TRIES
    private boolean isDone(){
        return NOF_TRIES <this.currTries;
    }

    private void assertRound(){
        if(isDone())this.score--;
    }

    private void exiting(){}

    //WHOLE GAME IS HELD HERE
    private void hangman(String str){
        int len= str.length();
        switch(str){
            case"start":
                initValues();
                break;
            case "exit":
                exiting();
                break;
            default:
                switch(len){
                    case 1:
                        oneChar();
                        break;
                    default:
                        wholeWord();
                        break;

                }
        }assertRound();
    }

    private void refreshOutput(){
        StringBuilder str=new StringBuilder();
        str.append("(");
        str.append("| score is:"+ score);
        str.append(" | mistakes done: " + currTries);
        str.append(" |"+ Arrays.toString(this.output)).append(" |");
        if(isDone())
        {
            str.append(LOST);
            this.score--;
        }
        else if(!Arrays.toString(output).contains("_")) {
            str.append(WON);
            this.score++;
            this.currTries=0;
        }
        else if(assertStart()) str.append(WELCOME);
        else str.append(IN_GAME);
        this.stream =str.toString();
    }
}
