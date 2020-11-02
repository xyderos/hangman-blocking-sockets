package Game.Client.view;


import Game.Client.controller.Controller;

import java.io.IOException;
import java.util.Scanner;

public class UserInterface {
    private Controller contr;

    public void start(){
        contr = new Controller();
        runProgram();
    }

    public void runProgram() {
        System.out.println("Type 'start' to play...");
        while (true) {
            Scanner userEntry = new Scanner(System.in);

            String s = userEntry.nextLine();
            new Thread(new Listener(s)).start();

        }
    }
    private class Listener implements Runnable{

        private String str;
        public Listener(String str){
            this.str=str;
        }

        @Override
        public void run() {
            if (this.str.equals("exit")) {
                System.out.println("exiting, thanks for playing");
                System.exit(1);
            }
            try {
                contr.sendToController(this.str);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                System.out.println(contr.receiveFromController());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
