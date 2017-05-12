package by.kalilaska.model;

import by.kalilaska.entities.SportEquipment;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by lovcov on 10.05.2017.
 */
public class Shop {
    private Map<SportEquipment, Integer> goods;

//    public Shop() {
//        goods = new HashMap<>();
//    }
    public Shop() {
        goods = new TreeMap<>();
    }

    public void addGood(SportEquipment se){
        boolean flag = goods.containsKey(se);
        int count;
        if(flag){
            count = goods.get(se);
            goods.put(se, (count + 1));
        }else{
            goods.put(se, 1);
        }
    }

    public Map<SportEquipment, Integer> getGoods() {
        return goods;
    }

    public void setGoods(Map<SportEquipment, Integer> goods) {
        this.goods = goods;
    }

    public void removeEquipmentFromShop(SportEquipment se){
        int amount = goods.get(se);
        if(amount > 1){
            amount--;
            goods.put(se, amount);
        }else{
            goods.remove(se);
        }
    }

    @Override
    public String toString() {
        return goods.toString();
    }
}
