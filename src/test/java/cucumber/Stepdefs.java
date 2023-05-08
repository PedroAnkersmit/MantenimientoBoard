package cucumber;

import board.Advertisement;
import board.AdvertisementBoard;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class Stepdefs {
    AdvertisementBoard board;
    Advertisement ad;
    @Given("no hay ningún tablón creado")
    public void no_board_yet(){
        board = null;
    }
    @And("el usuario es THE Company")
    public void user_is_THE_Company(){

    }


}
