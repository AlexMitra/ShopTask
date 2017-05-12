package by.kalilaska.data;

import by.kalilaska.entities.SportEquipment;
import by.kalilaska.model.RentUnit;
import by.kalilaska.model.Shop;


/**
 * Created by lovco on 10.05.2017.
 */
public interface DataAccess {
    void readData();

    Shop getData();

    RentUnit getRentedEquipment();

    void rentEquipment(SportEquipment se);

    int getMaxNumberOfRents();

    int getCurrentNumberOfRents();

    void removeEquipmentFromShop(SportEquipment se);
}
