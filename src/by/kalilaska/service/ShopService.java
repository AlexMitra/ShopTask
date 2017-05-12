package by.kalilaska.service;

import by.kalilaska.entities.SportEquipment;
import by.kalilaska.model.RentUnit;
import by.kalilaska.model.Shop;

import java.util.List;

/**
 * Created by lovcov on 10.05.2017.
 */
public interface ShopService {
    Shop getData();

    RentUnit getRentedEquipment();

    void rentEquipment(SportEquipment se);

    int getMaxNumberOfRents();

    int getCurrentNumberOfRents();

    List<SportEquipment> searchEquipment(String request);
}
