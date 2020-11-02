package Game.Server.controller;

import Game.Server.model.Game;

public class Controller {
    private Game game = new Game();

    // send data to model
    public void setInput (String receivedData){
        game.setStream(receivedData);
    }

    // get data from model
    public String getOutput (){
        return game.getStream();
    }
}
