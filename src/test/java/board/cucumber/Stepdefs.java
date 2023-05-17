package board.cucumber;

import board.Advertisement;
import board.AdvertisementBoard;
import board.AdvertisementBoardException;
import board.Publisher;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



import static board.AdvertisementBoard.*;
import static org.junit.jupiter.api.Assertions.*;


public class Stepdefs {

    AdvertisementBoard advertisementBoard;
    Advertisement advertisement;
    Publisher board_owner = new Publisher(BOARD_OWNER, BOARD_OWNER_FUND);
    Publisher publisher;
    double currentFunds;
    Advertisement aux;
    int size;

    //Feature: Crear tablon
    //scenario: Crear tablón success
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


    //Feature: Tamaño del tablon
    //Scenario: GetSize success
    @Given("hay un tablon de anuncios creado")
    public void the_board_is_already_created(){
        advertisementBoard = new AdvertisementBoard();
        size = advertisementBoard.numberOfPublishedAdvertisements();
    }
    @When("el usuario pide saber el número de anuncios publicados en el tablon")
    public void user_asks_for_size_of_the_board(){
        if(advertisementBoard != null){
            size = advertisementBoard.numberOfPublishedAdvertisements();
        } else{
            size = 0;
        }
    }
    @Then("el sistema devuelve el número de anuncios correcto")
    public  void size_is_correct(){
        int expected = size;
        int actual = advertisementBoard.numberOfPublishedAdvertisements();
        assertEquals(expected, actual);
    }
    //Scenario: GetSize fail
    @Then("el sistema avisa al usuario de que no hay ningún tablon creado y no se puede saber su tamaño")
    public void size_system_notifies_of_no_board_created(){
        assertThrows(NullPointerException.class, () -> advertisementBoard.numberOfPublishedAdvertisements());
    }

    // Feature: Publicar anuncio
    // Publicar anuncio success
    @And("el usuario tiene suficiente saldo para hacer el pago")
    public void user_has_enough_funds_to_make_the_payment() {
        publisher = new Publisher("user", 200.00);
        assertTrue(publisher.getFunds() >= PRIZE);
    }
    @And("el usuario crea un anuncio")
    public void user_creates_a_new_advertisment() {
        publisher = new Publisher("user", 200.0);
        currentFunds = publisher.getFunds();
        advertisement = new Advertisement("title", "text", publisher);
        size = advertisementBoard.numberOfPublishedAdvertisements();
        if(size != MAX_BOARD_SIZE){
            advertisementBoard.publish(advertisement);
        }
    }
    @Then("se cobra al anunciante.")
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

    // Publicar anuncio THE Company
    @And("el usuario es THE Company")
    public void user_is_THE_Company(){
        advertisement = new Advertisement("title", "text", board_owner);
    }

    // Publicar anuncio falla
    @And("el usuario no tiene sufiente saldo")
    public void user_does_not_have_enough_funds_to_make_the_payment() {
        publisher = new Publisher("user", 20.0);
        assertTrue(publisher.getFunds() < PRIZE);
    }
    // Publicar anuncio fail (El tablon esta lleno
    @And("el numero de anuncios publicados es igual al numero máximo de anuncios permitidos en el tablon")
    public void number_of_published_advertisements_is_equal_to_the_max_board_size() {
        int i = 0;
        advertisementBoard = new AdvertisementBoard();
        while(advertisementBoard.numberOfPublishedAdvertisements() <MAX_BOARD_SIZE){
            advertisement = new Advertisement("title" + i + " ", "text", board_owner);
            advertisementBoard.publish(advertisement);
            i++;
        }
    }
    @Then("su anuncio es rechazado por falta de fondos")
    public void advertisement_gets_declined_not_enough_funds() {
        advertisement = new Advertisement("title", "text", publisher);
        size = advertisementBoard.numberOfPublishedAdvertisements();
        assertThrows(AdvertisementBoardException.class, () -> advertisementBoard.publish(advertisement));
    }

    @Then("su anuncio es rechazado porque el tablon esta lleno")
    public void advertisement_gets_declined_board_full(){
        assertThrows(AdvertisementBoardException.class, () -> advertisementBoard.publish(advertisement));
    }

    //Feature:Buscar anuncio
    //Scenario: Buscar anuncio success
    @And("el tablon tiene al menos un anuncio del usuario")
    public void adb_has_at_least_one_advertisement_from_the_user() {
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

    //Scenario: Buscar anuncio fail
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

    //Scenario: Buscar anuncio fail (No hay tablon)
    @Then("el sistema avisa al usuario de que no hay ningún tablon creado y no se puede buscar su anuncio")
    public void find_system_notifies_of_no_board_created(){
        assertThrows(NullPointerException.class, () -> advertisementBoard.findByTitle(advertisement.getTitle()));
    }



    // Feature: Borrar anuncio
    //Scenario: Borrar anuncio success
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
    @And("el numero de anuncios publicados disminuye")
    public void number_of_published_advertisements_decreases() {
        int expectedValue = 1;
        int actualValue = size;

        assertEquals(expectedValue, actualValue);
    }

    //Scenario: Borrar anuncio THE Company
    @And("hay al menos un anuncio en el tablon")
    public void adb_has_at_least_one_advertisement() {
        publisher = new Publisher("user", 200.0);
        advertisement = new Advertisement("title", "text", publisher );
        advertisementBoard.publish(advertisement);
    }
    @And("el usuario borra un anuncio cualquiera")
    public void THE_Company_deletes_an_advertisement() {
        advertisementBoard.deleteAdvertisement("title", "user");
    }

    //Scenario: Borrar anuncio fail (El anuncio no está en el tablon)
    @When("el usuario borra un anuncio suyo que no está en el tablon")
    public void user_deletes_an_advertisement_that_is_not_in_the_board() {
        if(advertisementBoard != null){
        size = advertisementBoard.numberOfPublishedAdvertisements();
        advertisementBoard.deleteAdvertisement("", "user");
        }
    }
    @And("el numero de anuncios publicados no cambia")
    public void size_of_board_remains_the_same(){
        int expected = size;
        int actual = advertisementBoard.numberOfPublishedAdvertisements();
        assertEquals(expected, actual);
    }

    //Scenario: Borrar anuncio fail (No hay tablon creado)
    @Then("el sistema avisa al usuario de que no hay ningún tablon creado y no se puede borrar su anuncio")
    public void delete_system_notifies_of_no_board_created() {
        assertThrows(NullPointerException.class, () -> advertisementBoard.deleteAdvertisement(advertisement.getTitle(), advertisement.getUser().getName()));
    }

    // Borrar anuncio fail (intenta borrar un anuncio que no es suyo)
    @When("el usuario borra un anuncio que no es suyo")
    public void user_deletes_an_advertisement_from_another_user() {
        advertisementBoard.deleteAdvertisement("title", "user2");
    }



}
