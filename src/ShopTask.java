import by.kalilaska.controller.ShopController;
import by.kalilaska.controller.speakers.Speakers;
import by.kalilaska.data.DataAccess;
import by.kalilaska.data.impl.DataAccessImpl;
import by.kalilaska.model.RentUnit;
import by.kalilaska.model.Shop;
import by.kalilaska.service.ShopService;
import by.kalilaska.service.impl.ShopServiceImpl;
import by.kalilaska.view.Viewer;
import by.kalilaska.view.impl.ViewerImpl;

/**
 * Created by lovcov on 10.05.2017.
 */
public class ShopTask {
    public static void main(String[] args) {

        Viewer viewer = new ViewerImpl();
        DataAccess da = new DataAccessImpl(new Shop(), new RentUnit());

        try{
            da.readData();
        }catch (Exception e){
            viewer.view(Speakers.SHOP_CLOSED.speak());
            System.exit(0);
        }

        ShopService shopService = new ShopServiceImpl(da);

        ShopController shopController = new ShopController(shopService, viewer);
        shopController.showStartPage();

    }
}
