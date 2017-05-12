import by.kalilaska.controller.ShopController;
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

        DataAccess da = new DataAccessImpl(new Shop(), new RentUnit());
        da.readData();

        ShopService shopService = new ShopServiceImpl(da);
        Viewer viewer = new ViewerImpl();

        ShopController shopController = new ShopController(shopService, viewer);
        shopController.showStartPage();

    }
}
