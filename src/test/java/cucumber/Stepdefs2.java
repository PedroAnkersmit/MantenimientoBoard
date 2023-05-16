package cucumber;

import board.Advertisement;
import board.AdvertisementBoard;
import board.Publisher;
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
    Publisher publisher;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    int currentNumberOfBoards;
    int advertisementPrice;
    int currentNumberOfPublishedAdvertisements;

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

    // Feature: Borrar anuncio
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
    public void number_of_published_advertisements_decreases() {
        int expectedValue = advertisementBoard.numberOfPublishedAdvertisements()-1;
        int actualValue = advertisementBoard.numberOfPublishedAdvertisements();

        assertEquals(expectedValue, actualValue);
    }

    @Then("el sistema avisa al usuario de que se ha borrado el anuncio")
    public void system_notifies_of_advertisement_removal(){
        assertEquals("Se ha borrado el anuncio del tablón", outContent.toString());
    }


    // Borrar de anuncio de THE COMPANY
    @And("el usuario es THE Company")
    public void user_is_THE_Company(){
        advertisement = new Advertisement("title", "text", "THE Company");
    }

    @And("el usuario borra un anuncio cualquiera")
    public void THE_Company_deletes_an_advertisement() {
        advertisementBoard.deleteAdvertisement("title", "THE_Company");
    }


    // Borrar anuncio THE Company
    @And("hay al menos un anuncio en el tablón")
    public void adb_has_at_least_one_advertisement_from_THE_Company() {
        advertisement = new Advertisement("title", "text", "THE Company");
        advertisementBoard.publish(advertisement);
    }


    // Borrar anuncio fail (El anuncio no está en el tablón)
    @And("el tablón tiene al menos un anuncio")
    public void adb_has_at_least_one_advertisement2() {
        advertisement = new Advertisement("title", "text", "user");
        advertisementBoard.publish(advertisement);
    }

    @When("el usuario borra un anuncio suyo que no está en el tablón")
    public void user_deletes_an_advertisement_that_is_not_in_the_board() {
        currentNumberOfBoards = advertisementBoard.numberOfPublishedAdvertisements();
        advertisementBoard.deleteAdvertisement("", "user");
    }

    @And("el numero de anuncios publicados no cambia")
    public void size_of_board_remains_the_same(){
        int actualValue = advertisementBoard.numberOfPublishedAdvertisements();

        assertEquals(currentNumberOfBoards, actualValue);
    }


    // Borrar anuncio fail (No hay tablón creado)
    @When("el usuario borra un anuncio suyo")
    public void user_deletes_one_of_his_advertisements() {
        advertisementBoard.deleteAdvertisement("title", "user");
    }


    // Borrar anuncio fail (intenta borrar un anuncio que no es suyo)
    @When("el usuario borra un anuncio que no es suyo")
    public void user_deletes_an_advertisement_from_another_user() {
        advertisementBoard.deleteAdvertisement("title", "user2");
    }


    // -----------------------------------------------------------------------------------------
    // Feature: Publicar anuncio
    // Publicar anuncio success
    @And("el usuario tiene suficiente saldo para hacer el pago")
    public void user_has_enough_funds_to_make_the_payment() {
        assertTrue(publisher.getFunds() >= advertisementPrice);
    }

    @And("el usuario crea un anuncio")
    @And("se crea el anuncio.")
    public void user_creates_a_new_advertisment() {
        advertisement = new Advertisement("title", "text", "user");
    }

    @And("se cobra al anunciante.")
    public void user_gets_charged() {
        assertTrue(publisher.getFunds() < publisher.getFunds()+advertisementPrice);
    }

    @And("se publica el anuncio en el tablón.")
    public void advertisement_gets_published() {
        advertisementBoard.findByTitle("title");
    }

    @And("crece el número de anuncios publicados en el tablón")
    public void number_of_published_advertisements_increases() {
        int expectedValue = currentNumberOfPublishedAdvertisements;
        int actualValue = advertisementBoard.numberOfPublishedAdvertisements();

        assertEquals(expectedValue, actualValue);

    }

    @And("el sistema avisa al usuario de que se ha publicado el anuncio")
    public void system_notifies_the_advertisements_has_been_published() {
        assertEquals("Se ha publicado el anuncio", outContent.toString());
    }

    // Publicar anuncio THE Company

    // Publicar anuncio falla
    @And("el usuario no tiene sufiente saldo")
    public void user_does_not_have_enough_funds_to_make_the_payment() {
        assertTrue(publisher.getFunds() < advertisementPrice);
    }

    @Then("su anuncio es rechazado")
    public void advertisement_gets_declined() {
        assertEquals("Anuncio rechazado", errContent.toString());
    }

    @And("el sistema avisa al usuario de que no se ha publicado el anuncio")
    public void system_notifies_the_advertisement_could_not_be_published() {
        assertEquals("El anuncio no se ha podido publicar", outContent.toString());
    }


    // Publicar anuncio fail
    @And("el numero de anuncios publicados es igual al numero máximo de anuncios permitidos en el tablón")
    public void number_of_published_advertisements_is_equal_to_the_max_board_size() {

    }

    @Then("el sistema avisa el usuario de que no se ha podido agregar el anuncio")
    public void system_notifies_the_advertisement_could_not_be_added() {
        assertEquals("El anuncio ha podido ser añadido", outContent.toString());
    }

}
