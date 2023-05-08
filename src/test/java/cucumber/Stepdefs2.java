package cucumber;

import board.Advertisement;
import board.AdvertisementBoard;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Optional;

import static org.junit.Assert.*;

public class Stepdefs2 {
    Advertisement advertisement;
    AdvertisementBoard advertisementBoard;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStream() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }


    @Given("hay un tablón de anuncios creado")
    public void adb_created() {
        advertisementBoard = new AdvertisementBoard();
    }

    @And("el tablón tiene al menos un anuncio del usuario")
    public void adb_has_at_least_one_advertisement() {
        advertisement = new Advertisement("title", "text", "user");
        advertisementBoard.publish(advertisement);
    }

    @When("el usuario borra un anuncio suyo que está en el tablón")
    public void user_deletes_advertisement() {
        advertisementBoard.deleteAdvertisement("title", "user");
    }

    @Then("el anuncio es borrado del tablón")
    public void advertisement_is_deleted() {
        Optional<Advertisement> expectedValue = Optional.empty();
        Optional<Advertisement> actualValue = advertisementBoard.findByTitle("title");
        assertEquals(expectedValue, actualValue);
    }
    @Then("el numero de anuncios publicados disminuye")
    public void number_of_advertisements_published_decreases() {
        int expectedValue = advertisementBoard.numberOfPublishedAdvertisements()-1;
        int actualValue = advertisementBoard.numberOfPublishedAdvertisements();

        assertEquals(expectedValue, actualValue);

    }

    @Then("el sistema avisa al usuario de que se ha borrado el anuncio")
    public void system_notifies_of_advertisement_removal(){
        assertEquals("Se ha borrado el anuncio del tablón", outContent.toString());
    }
}
