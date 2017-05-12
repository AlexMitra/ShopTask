package by.kalilaska.service.impl;

import by.kalilaska.data.DataAccess;
import by.kalilaska.entities.SportEquipment;
import by.kalilaska.model.RentUnit;
import by.kalilaska.model.Shop;
import by.kalilaska.service.ShopService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lovcov on 10.05.2017.
 */
public class ShopServiceImpl implements ShopService{
    private DataAccess dataAccess;

    public ShopServiceImpl() {
    }

    public ShopServiceImpl(DataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    @Override
    public Shop getData() {
        return dataAccess.getData();
    }

    @Override
    public RentUnit getRentedEquipment() {
        return dataAccess.getRentedEquipment();
    }

    @Override
    public void rentEquipment(SportEquipment se) {
        dataAccess.rentEquipment(se);
        dataAccess.removeEquipmentFromShop(se);
    }

    @Override
    public int getMaxNumberOfRents() {
        return dataAccess.getMaxNumberOfRents();
    }

    @Override
    public int getCurrentNumberOfRents() {
        return dataAccess.getCurrentNumberOfRents();
    }

    @Override
    public List<SportEquipment> searchEquipment(String request) {
        SportEquipment se;
        List<SportEquipment> equipmentList = new ArrayList<>();

        for (Map.Entry<SportEquipment, Integer> entry: getData().getGoods().entrySet()) {
            se = entry.getKey();
            if(se.getTitle().contains(request)){
                equipmentList.add(se);
            }

        }
        return equipmentList;
    }
}
