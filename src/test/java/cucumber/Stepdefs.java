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
    AdvertisementBoard board;
    Advertisement ad;

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
        board = null;
    }
    @When("el usuario crea un tablón")
    public void ad_is_created(){
        board = new AdvertisementBoard();
    }
    @Then("se crea un tablón")
    public void a_board_is_created(){
        assertTrue(board != null);
    }
    @And("se publica un anuncio predeterminado en el tablón.")
    public void a_premade_ad_is_published(){
        Optional<Advertisement> premade = board.findByTitle("Welcome");
        assertEquals(premade, new Advertisement(
                "Welcome",
                "This board is intended for your advertisements",
                BOARD_OWNER));

    }
    @And("el numero de anuncios publicados es 1")
    public void size_of_board_is_one(){
        assertEquals(board.numberOfPublishedAdvertisements(), 1);
    }
    @And("el sistema avisa al usuario de que se ha creado el tablón correctamente")
    public void system_notifies_of_board_creation(){
        assertEquals("Se ha creado un tablón de anuncios", outContent.toString());
    }

    @Given("hay un tablón de anuncios creado")
    public void the_board_is_already_created(){
        board = new AdvertisementBoard();
        board.publish(ad);
    }

    @Then("el tablón no es creado")
    public void the_board_stays_the_same(){
        assertNotEquals(board, new AdvertisementBoard());
    }

    @And("el sistema avisa al usuario de que no se ha podido crear el tablón")
    public void system_throws_an_exception(){
        assertEquals("Ya hay un tablón creado", errContent.toString());
    }



}
