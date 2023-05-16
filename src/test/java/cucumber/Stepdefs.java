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

import static board.AdvertisementBoard.*;
import static org.junit.jupiter.api.Assertions.*;


public class Stepdefs {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

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

    AdvertisementBoard advertisementBoard;
    Advertisement advertisement;
    Publisher board_owner = new Publisher(BOARD_OWNER, BOARD_OWNER_FUND);
    Publisher publisher;
    double currentFunds;
    Advertisement aux;
    int size;

    //Feature: Crear tablon
    @Given("no hay ningún tablon creado")
    public void no_board_yet(){

        advertisementBoard = null;
    }
    @When("el usuario crea un tablon")
    public void ad_is_created(){
        advertisementBoard = new AdvertisementBoard();
    }
    @Then("se crea un tablon")
    public void a_board_is_created(){
        assertTrue(advertisementBoard != null);
    }
    @And("se publica un anuncio predeterminado en el tablon.")
    public void a_premade_ad_is_published(){
       Advertisement expected = new Advertisement(
               "Welcome",
               "This board is intended for your advertisements",
               board_owner);
        Advertisement actual = advertisementBoard.findByTitle("Welcome");
        assertTrue(expected.equals( actual));

    }
    @And("el numero de anuncios publicados es 1")
    public void size_of_board_is_one(){
        assertEquals(advertisementBoard.numberOfPublishedAdvertisements(), 1);
    }
    @And("el sistema avisa al usuario de que se ha creado el tablon correctamente")
    public void system_notifies_of_board_creation(){
        assertEquals("Se ha creado un tablon de anuncios", outContent.toString());
    }

    @Given("hay un tablon de anuncios creado")
    public void the_board_is_already_created(){
        publisher = new Publisher("user", 200.0);
        advertisement = new Advertisement("title", "text", publisher );
        advertisementBoard = new AdvertisementBoard();
        advertisementBoard.publish(advertisement);
        size = advertisementBoard.numberOfPublishedAdvertisements();
    }

    @Then("el tablon no es creado")
    public void the_board_stays_the_same(){
        assertNotEquals(advertisementBoard, new AdvertisementBoard());
    }

    @And("el sistema avisa al usuario de que no se ha podido crear el tablon")
    public void system_throws_an_exception(){
        assertEquals("Ya hay un tablon creado", errContent.toString());

    }

    //Feature:Buscar anuncio

    @And("el tablon tiene al menos un anuncio del usuario")
    public void adb_has_at_least_one_advertisement() {
        publisher = new Publisher("user", 200.0);
        advertisement = new Advertisement("title", "text", publisher);
        advertisementBoard.publish(advertisement);
    }
    @When("el usuario busca un anuncio suyo que está en el tablon")
    public void user_searches_for_his_ad_which_is_on_the_board(){
        aux = advertisementBoard.findByTitle("title");
    }
    @Then("el tablon encuentra el anuncio pedido")
    public void ad_is_found(){
        assertEquals(advertisement, aux);
    }

    @And("el sistema avisa al usuario de que se ha encontrado el anuncio")
    public void system_notifies_of_found_ad(){
        assertEquals("Se ha encontrado su anuncio", outContent.toString());

    }
    @And("el usuario no es THE Company")
    public void user_is_not_THE_Company(){
        publisher = new Publisher("user", 200.0);
        advertisement = new Advertisement("title", "text", publisher);
    }
    @And("el tablon no tiene un anuncio del usuario")
    public void board_does_not_have_an_ad_from_user(){
        advertisementBoard = new AdvertisementBoard();
    }
    @When("el usuario busca un anuncio suyo")
    public void user_searches_for_his_ad(){
        if(advertisementBoard != null){
        aux = advertisementBoard.findByTitle("title");
    }
    }
    @Then("no se encuentra el anuncio")
    public void ad_is_not_found(){
        assertNotEquals(advertisement, aux);
    }
    @And("el sistema avisa el usuario de que no se ha podido encontrar el anuncio")
    public void system_notifies_of_ad_not_found(){
        assertEquals("No se ha encontrado su anuncio", outContent.toString());

    }

    @Then("el sistema avisa al usuario de que no hay ningún tablon creado")
    public void system_notifies_of_no_board_created(){
        assertThrows(NullPointerException.class, () -> advertisementBoard.numberOfPublishedAdvertisements());

    }

    @When("el usuario pide saber el número de anuncios publicados en el tablon")
    public void user_asks_for_size_of_the_board(){
        if(advertisementBoard != null){
        size = advertisementBoard.numberOfPublishedAdvertisements();
        } else{
            size = 0;
        }
    }

    @Then("el sistema le dice cuantos anuncios hay publicados en ese tablon")
    public void system_shows_number_of_ads_in_the_board(){
        String text = "Hay " + size + " anuncios publicados en el tablon";
        assertEquals(text, outContent.toString());

    }


    // Feature: Borrar anuncio


    @When("el usuario borra un anuncio suyo que está en el tablon")
    public void user_deletes_advertisement() {
        advertisementBoard.deleteAdvertisement("title", "user");
    }

    @Then("el anuncio es borrado del tablon")
    public void advertisement_is_deleted() {
        Advertisement expected = null;
        Advertisement actual = advertisementBoard.findByTitle("title");
        size = advertisementBoard.numberOfPublishedAdvertisements();
        assertEquals(expected, actual);
    }
    @Then("el numero de anuncios publicados disminuye")
    public void number_of_published_advertisements_decreases() {
        int expectedValue = 1;
        int actualValue = size;

        assertEquals(expectedValue, actualValue);
    }

    @Then("el sistema avisa al usuario de que se ha borrado el anuncio")
    public void system_notifies_of_advertisement_removal(){
        assertEquals("Se ha borrado el anuncio del tablon", outContent.toString());
    }


    // Borrar de anuncio de THE COMPANY
    @And("el usuario es THE Company")
    public void user_is_THE_Company(){
        advertisement = new Advertisement("title", "text", board_owner);
    }

    @And("el usuario borra un anuncio cualquiera")
    public void THE_Company_deletes_an_advertisement() {
        advertisementBoard.deleteAdvertisement("title", "user");
    }


    // Borrar anuncio THE Company
    @And("hay al menos un anuncio en el tablon")
    public void adb_has_at_least_one_advertisement_from_THE_Company() {
        advertisement = new Advertisement("title", "text", board_owner);
        advertisementBoard.publish(advertisement);
    }


    // Borrar anuncio fail (El anuncio no está en el tablon)

    @When("el usuario borra un anuncio suyo que no está en el tablon")
    public void user_deletes_an_advertisement_that_is_not_in_the_board() {
        size = advertisementBoard.numberOfPublishedAdvertisements();
        advertisementBoard.deleteAdvertisement("", "user");
    }

    @And("el numero de anuncios publicados no cambia")
    public void size_of_board_remains_the_same(){
        int actualValue = advertisementBoard.numberOfPublishedAdvertisements();

        assertEquals(size, actualValue);
    }


    // Borrar anuncio fail (No hay tablon creado)
    @When("el usuario borra un anuncio suyo")
    public void user_deletes_one_of_his_advertisements() {
        if(advertisementBoard != null){
        advertisementBoard.deleteAdvertisement("title", "user");
    }
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
        publisher = new Publisher("user", 200.00);

        assertTrue(publisher.getFunds() >= PRIZE);
    }

    @And("el usuario crea un anuncio")
    @And("se crea el anuncio.")
    public void user_creates_a_new_advertisment() {
        publisher = new Publisher("user", 200.0);
        currentFunds = publisher.getFunds();
        advertisement = new Advertisement("title", "text", publisher);
        size = advertisementBoard.numberOfPublishedAdvertisements();
        advertisementBoard.publish(advertisement);
    }

    @And("se cobra al anunciante.")
    public void user_gets_charged() {
        assertEquals(currentFunds-PRIZE,publisher.getFunds(), 0);
    }

    @And("se publica el anuncio en el tablon.")
    public void advertisement_gets_published() {
        advertisementBoard.findByTitle("title");
    }

    @And("crece el número de anuncios publicados en el tablon")
    public void number_of_published_advertisements_increases() {
        int expectedValue = size +1;
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
        publisher = new Publisher("user", 20.0);
        assertTrue(publisher.getFunds() < PRIZE);
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
    @And("el numero de anuncios publicados es igual al numero máximo de anuncios permitidos en el tablon")
    public void number_of_published_advertisements_is_equal_to_the_max_board_size() {

    }

    @Then("el sistema avisa el usuario de que no se ha podido agregar el anuncio")
    public void system_notifies_the_advertisement_could_not_be_added() {
        assertEquals("El anuncio ha podido ser añadido", outContent.toString());
    }

}
