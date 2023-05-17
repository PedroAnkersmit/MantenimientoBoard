package board;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementBoard {

    public static final String BOARD_OWNER = "THE Company";
    public static final Double BOARD_OWNER_FUND = 0.0;
    public static final int MAX_BOARD_SIZE = 20;
    public static final double PRIZE = 110.00;
    private final List<Advertisement> advertisementList;

    public AdvertisementBoard() {

        advertisementList = new ArrayList<>();
        Advertisement initialAdvertisement = new Advertisement(
                "Welcome",
                "This board is intended for your advertisements", new Publisher(BOARD_OWNER, BOARD_OWNER_FUND));
        advertisementList.add(initialAdvertisement);


        System.out.println("Se ha creado un tablon de anuncios");
    }

    public int numberOfPublishedAdvertisements() {
        System.out.println("Hay " + advertisementList.size() + " anuncios publicados en el tablon" );
        return advertisementList.size();
    }

    public void publish(Advertisement advertisement ) {
        if(this.numberOfPublishedAdvertisements() < MAX_BOARD_SIZE){
        if (advertisement.user.name.equals(BOARD_OWNER))
            advertisementList.add(advertisement);
        else {
            double funds = advertisement.user.getFunds();
            if (funds >= PRIZE ){
                    funds -= PRIZE;
                    advertisement.user.setFunds(funds);
                    advertisementList.add(advertisement);
                } else {
                throw new AdvertisementBoardException("Su anuncio ha sido rechazado");
            }
            }
        } else {
            throw new AdvertisementBoardException("El tablon esta lleno");
        }
    }

    public Advertisement findByTitle(String title) {
            Advertisement aux = null;
            if(advertisementList.stream().anyMatch(ad -> ad.title.equals(title))){
            System.out.println("Se ha encontrado su anuncio");
            while (aux == null){
            for(Advertisement a : advertisementList){
                if(a.getTitle().equals(title)){
                aux = a;
                }
            }
            }
            return aux;
        }else{
            System.out.println("No se ha encontrado su anuncio");
                return aux;
        }
    }

    public void deleteAdvertisement(String title, String advertiserName) {
        System.out.println("Se ha borrado el anuncio del tablon");
        Advertisement aux = findByTitle(title);
        if(advertisementList.contains(aux) && aux.getUser().getName().equals(advertiserName)){
            advertisementList.remove(aux);
        }
    }
}
