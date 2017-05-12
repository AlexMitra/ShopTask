package by.kalilaska.model;

import by.kalilaska.entities.SportEquipment;

/**
 * Created by lovcov on 11.05.2017.
 */
public class RentUnit {
    private SportEquipment[] units = new SportEquipment[3];

    public void addUnit(SportEquipment sportEquipment){
        for (int i=0; i< units.length; i++) {
            if(units[i] == null){
                units[i] = sportEquipment;
                break;
            }
        }
    }

    public int getMaxNumberOfUnits(){
        return units.length;
    }

    public int getCurrentNumberOfUnits(){
        int count = 0;
        for (int i=0; i< units.length; i++) {
            if(units[i] != null){
                count++;
            }else{
                break;
            }
        }
        return count;
    }

    public SportEquipment[] getRents(){
        return units;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        double totalPrice = 0;

        for (int i = 0; i < units.length; i++) {
            if(units[i] != null){
                sb.append("\n" + units[i].getTitle());
                totalPrice += units[i].getPrice();
            }else{
                break;
            }

        }
        sb.append("\n------------");
        sb.append("\nTotal price: " + totalPrice + "$");
        return sb.toString();
    }
}
