package cucumber;

import board.Advertisement;
import board.AdvertisementBoard;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.After;
import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Optional;

import static board.AdvertisementBoard.BOARD_OWNER;
import static org.junit.Assert.*;

public class Stepdefs {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    AdvertisementBoard advertisementBoard;
    Advertisement advertisement;

    Optional<Advertisement> aux;

    int size;

    //Feature: Crear tablón
    @Before
    public void setUpStream() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }
    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

        @Given("no hay ningún tablón creado")
    public void no_board_yet(){
        advertisementBoard = null;
    }
    @When("el usuario crea un tablón")
    public void ad_is_created(){
        advertisementBoard = new AdvertisementBoard();
    }
    @Then("se crea un tablón")
    public void a_board_is_created(){
        assertTrue(advertisementBoard != null);
    }
    @And("se publica un anuncio predeterminado en el tablón.")
    public void a_premade_ad_is_published(){
        Optional<Advertisement> premade = advertisementBoard.findByTitle("Welcome");
        assertEquals(premade, new Advertisement(
                "Welcome",
                "This board is intended for your advertisements",
                BOARD_OWNER));

    }
    @And("el numero de anuncios publicados es 1")
    public void size_of_board_is_one(){
        assertEquals(advertisementBoard.numberOfPublishedAdvertisements(), 1);
    }
    @And("el sistema avisa al usuario de que se ha creado el tablón correctamente")
    public void system_notifies_of_board_creation(){
        assertEquals("Se ha creado un tablón de anuncios", outContent.toString());
    }

    @Given("hay un tablón de anuncios creado")
    public void the_board_is_already_created(){
        advertisementBoard = new AdvertisementBoard();
        advertisementBoard.publish(advertisement);
    }

    @Then("el tablón no es creado")
    public void the_board_stays_the_same(){
        assertNotEquals(advertisementBoard, new AdvertisementBoard());
    }

    @And("el sistema avisa al usuario de que no se ha podido crear el tablón")
    public void system_throws_an_exception(){
        assertEquals("Ya hay un tablón creado", errContent.toString());
    }

    //Feature:Buscar anuncio

    @And("el tablón tiene al menos un anuncio del usuario")
    public void adb_has_at_least_one_advertisement() {
        advertisement = new Advertisement("title", "text", "user");
        advertisementBoard.publish(advertisement);
    }
    @When("el usuario busca un anuncio suyo que está en el tablón")
    public void user_searches_for_his_ad_which_is_on_the_board(){
        aux = advertisementBoard.findByTitle("title");
    }
    @Then("el tablón encuentra el anuncio pedido")
    public void ad_is_found(){
        assertEquals(advertisement, aux);
    }

    @And("el sistema avisa al usuario de que se ha encontrado el anuncio")
    public void system_notifies_of_found_ad(){
        assertEquals("Se ha encontrado su anuncio", outContent.toString());
    }
    @And("el usuario no es THE Company")
    public void user_is_not_THE_Company(){
        advertisement = new Advertisement("title", "text", "user");
    }
    @And("el tablón no tiene un anuncio del usuario")
    public void board_does_not_have_an_ad_from_user(){
        advertisementBoard = new AdvertisementBoard();
    }
    @When("el usuario busca un anuncio suyo")
    public void user_searches_for_his_ad(){
        aux = advertisementBoard.findByTitle("title");
    }
    @Then("no se encuentra el anuncio")
    public void ad_is_not_found(){
        assertNotEquals(advertisement, aux);
    }
    @And("el sistema avisa el usuario de que no se ha podido encontrar el anuncio")
    public void system_notifies_of_ad_not_found(){
        assertEquals("No se ha encontrado su anuncio", outContent.toString());
    }

    @Then("el sistema avisa al usuario de que no hay ningún tablón creado")
    public void system_notifies_of_no_board_created(){
        assertEquals("No hay ningún tablón creado", errContent.toString());
    }

    @When("el usuario pide saber el número de anuncios publicados en el tablón")
    public void user_asks_for_size_of_the_board(){
        size = advertisementBoard.numberOfPublishedAdvertisements();
    }

    @Then("el sistema le dice cuantos anuncios hay publicados en ese tablón")
    public void system_shows_number_of_ads_in_the_board(){
        String text = "Hay " + size + " anuncios publicados en el tablón";
        assertEquals(text, outContent.toString());
    }

}
