package board;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Board to publish advertisements.
 */
public class AdvertisementBoard {

    public static final String BOARD_OWNER = "THE Company";
    public static final int MAX_BOARD_SIZE = 20;
    private final List<Advertisement> advertisementList = new ArrayList<>();//quitar el new
    private final List<Publisher> publisherList = new ArrayList<>();

    /**
     * Constructs a board containing an initial advertisement published by the {@code BOARD_OWNER}.
     */
    public AdvertisementBoard() {
        /*if(!advertisementList.isEmpty()){
            throw new AdvertisementBoardException("Ya hay un tablón creado");
        }*/
        /*advertisementList = new ArrayList<>();
        Advertisement initialAdvertisement = new Advertisement(
                "Welcome",
                "This board is intended for your advertisements",
                BOARD_OWNER);
        advertisementList.add(initialAdvertisement);

        */
        //System.out.println("Se ha creado un tablón de anuncios");
    }

    /**
     * Returns the number of advertisements published in this board.
     * <p>
     * The number of advertisements is bounded by {@code MAX_BOARD_SIZE}.
     *
     * @return the number of advertisements published in this board
     */
    public int numberOfPublishedAdvertisements() {
        /*System.out.println("Hay " + advertisementList.size() + " anuncios publicados en el tablón" );*/
        return 1;//advertisementList.size();
    }

    /**
     * Publishes an advertisement in this board.
     * <p>
     * For an advertisement to be published, the advertiser must be registered in the database
     * and must have sufficient funds in the payment gateway. The advertiser called <tt>"THE Company"</tt>
     * is the owner of the advertisement board, so it can publish freely, with no constraints.
     */
    public void publish(Advertisement advertisement /*AdvertiserDatabase advertiserDatabase,
                        PaymentGateway paymentGateway*/                        ) {
        /*if (advertisement.advertiser.equals(BOARD_OWNER))
            advertisementList.add(advertisement);
        else {
            if (advertiserDatabase.advertiserIsRegistered(advertisement.advertiser) &&
                    paymentGateway.advertiserHasFunds(advertisement.advertiser)) {
                advertisementList.add(advertisement);
                paymentGateway.chargeAdvertiser(advertisement.advertiser);
            }
        }*/
    }

    /**
     * Returns an {@code Optional} with an advertisement with the given title in this board,
     * or an empty {@code Optional} if there is not such an advertisement in this board.
     *
     * @param title the title to search for
     * @return an {@code Optional} with the appropriate advertisement, or and empty {@code Optional}
     */
    public Optional<Advertisement> findByTitle(String title) {


        /*if(this != null){if(advertisementList.stream().filter(ad -> ad.title.equals(title)).findFirst().isPresent()){
            System.out.println("Se ha encontrado su anuncio");
        }else{
            System.out.println("No se ha encontrado su anuncio");
        }
        }else {
            throw new AdvertisementBoardException("No hay ningún tablón creado");
        }
        */
        //return advertisementList.stream().filter(ad -> ad.title.equals(title)).findFirst();
        return null;
    }

    /**
     * Deletes all the advertisements with both the given title and advertiser name from this board.
     *
     * @param title          the title of the advertisements to be deleted from this board
     * @param advertiserName the name of the advertiser of the advertisements to be deleted from this board
     */
    public void deleteAdvertisement(String title, String advertiserName) {
       //advertisementList.removeIf(ad -> ad.title.equals(title) && ad.advertiser.equals(advertiserName));
    }
}
