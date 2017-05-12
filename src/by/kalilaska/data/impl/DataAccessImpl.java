package by.kalilaska.data.impl;

import by.kalilaska.data.DataAccess;
import by.kalilaska.data.exceptions.DataNotFoundException;
import by.kalilaska.data.exceptions.FileNotFoundPushException;
import by.kalilaska.entities.SportEquipment;
import by.kalilaska.model.RentUnit;
import by.kalilaska.model.Shop;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lovcov on 10.05.2017.
 */
public class DataAccessImpl implements DataAccess {
    private RentUnit rentUnit;
    private Shop shop;
    private final String PATH = "Data.json";

    public DataAccessImpl() {
    }

    public DataAccessImpl(Shop shop, RentUnit rentUnit) {
        this.shop = shop;
        this.rentUnit = rentUnit;
    }

    @Override
    public void readData() {
        Gson gson = new Gson();
        BufferedReader br = null;

        try{
            br = new BufferedReader(new FileReader(PATH));
            Type SportEquipmentListType = new TypeToken<ArrayList<SportEquipment>>(){}.getType();
            List<SportEquipment> sportEquipmentList = gson.fromJson(br, SportEquipmentListType);

            if (sportEquipmentList != null) {

                for (SportEquipment se : sportEquipmentList) {
                    shop.addGood(se);
                }
                if(shop.getGoods().size()==0){
                    throw new DataNotFoundException("We have not data");
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundPushException("File not found");
        }finally {
            if(br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public Shop getData(){
            return shop;
    }

    @Override
    public RentUnit getRentedEquipment() {
        return rentUnit;
    }

    @Override
    public void rentEquipment(SportEquipment se) {
        rentUnit.addUnit(se);
    }

    @Override
    public int getMaxNumberOfRents() {
        return rentUnit.getMaxNumberOfUnits();
    }

    @Override
    public int getCurrentNumberOfRents() {
        return rentUnit.getCurrentNumberOfUnits();
    }

    @Override
    public void removeEquipmentFromShop(SportEquipment se) {
        shop.removeEquipmentFromShop(se);
    }

}
