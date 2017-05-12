package by.kalilaska.controller;

import by.kalilaska.controller.speakers.Speakers;
import by.kalilaska.entities.Category;
import by.kalilaska.entities.SportEquipment;
import by.kalilaska.model.RentUnit;
import by.kalilaska.service.ShopService;
import by.kalilaska.view.Viewer;

import java.util.*;

/**
 * Created by lovcov on 10.05.2017.
 */
public class ShopController {
    private ShopService shopService;
    private Viewer viewer;

    private Formatter f = new Formatter(System.out);
    private List<Category> categoryList = new ArrayList<>();
    private int choosenCategory = -1;
    List<SportEquipment> searchedEquipment = new ArrayList<>();

    public ShopController() {
    }

    public ShopController(ShopService shopService, Viewer viewer) {
        this.shopService = shopService;
        this.viewer = viewer;
    }

    public void showStartPage(){
        int countOfRents = shopService.getCurrentNumberOfRents();
        int maxNumberOfRents = shopService.getMaxNumberOfRents();

        viewer.view(Speakers.GREATINGS.speak());

        while(countOfRents< maxNumberOfRents){
            viewer.view(showEquipmentTable());

            if(readAnswerAboutYesNo() == true){
                viewer.view(showMainMenu());

                int answer = readAnswerAboutCNumberS();

                switch (answer){
                    case 0:
                        viewer.view(showCategories());
                        viewer.view(showEquipmentTableByCategory(readChoosingCategory()));
                        viewer.view(showChooseEquipmentOrReturnToMainMenu());
                        rentOrReturn(readChoosingEquipment());
                        break;
                    case 1:
                        viewer.view(showEquipmentTable());
                        break;
                    case 2:
                        viewer.view(showSuggestionAboutSearchingEquipment());
                        viewer.view(showSearchedEquipmentOrReturnToMainMenu(readSearchingEquipment()));
                        rentOrReturn(readChoosingEquipment());
                        break;
                }
                countOfRents = shopService.getCurrentNumberOfRents();
            }else{
                viewer.view(showEquipmentTable());
                viewer.view(Speakers.BYE_BYE.speak());
                return;
            }
        }
        viewer.view(Speakers.BYE_BYE.speak());
        return;

    }

    private String showEquipmentTable(){
        SportEquipment se;
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%1s %-5s %-50s %10s %10s\n", " ", "#", "Name of equipment", "Price, $", "Amount"));
        sb.append(String.format("%1s %-5s %-50s %10s %10s\n", "|", "-", "-----------------", "--------", "------"));

        for (Map.Entry<SportEquipment, Integer> entry: shopService.getData().getGoods().entrySet()) {
            se = entry.getKey();
            if(!categoryList.contains(se.getCategory())){
                categoryList.add(se.getCategory());
                sb.append(String.format("%1s %-5s %50s %10s %10s\n", "|", " ", se.getCategory().getName(), "       ", "      "));
            }
            sb.append(String.format("%1s %-5d %-50s %10.2f %9d %1s\n", "|", se.getId(), se.getTitle(), se.getPrice(), entry.getValue(), "|"));
        }
        sb.append(getRents());
        return sb.toString();
    }

    private String getRents(){
        StringBuilder sb = new StringBuilder();

        RentUnit rentUnit = shopService.getRentedEquipment();
        int countOfRents = rentUnit.getCurrentNumberOfUnits();
        int maxNumberOfRents = rentUnit.getMaxNumberOfUnits();

        if(countOfRents == 0){
            sb.append(Speakers.YOU_DID_NOT_RENT_ANYTHING.speak());
            sb.append(Speakers.WANT_TO_RENT_SMTH.speak());
        }else if(countOfRents > 0 && countOfRents< maxNumberOfRents){
            sb.append(Speakers.RENTS_LIST.speak());
            sb.append("\n-----------");
            sb.append(rentUnit.toString());
            sb.append(Speakers.WANT_TO_RENT_SMTH_ELSE.speak());
        }else{
            sb.append(rentUnit.toString());
            sb.append(Speakers.CAN_NOT_RENT.speak());
        }

        return sb.toString();
    }

    private String showCategories(){
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("\n%1s %-5s %-50s\n", " ", "#", "Name of category"));
        sb.append(String.format("%1s %-5s %-50s\n", "|", "-", "-----------------"));
        for (Category c: categoryList) {
            sb.append(String.format("%1s %-5s %49s %1s\n", "|", c.getId(), c.getName(), "|"));
        }
        sb.append(getCategoryNumbers());
        return sb.toString();
    }

    private String getCategoryNumbers(){
        StringBuilder sb = new StringBuilder();

        sb.append("\n choose: ");
        for (int i=0; i< categoryList.size(); i++) {
            if(i>0){
                sb.append(", ");
            }
            sb.append(categoryList.get(i).getId());
        }
        sb.append(" ");
        return sb.toString();
    }

    private String showMainMenu(){
        return Speakers.MAIN_MENU.speak();
    }

    private boolean readAnswerAboutYesNo(){
        Scanner sc = null;

        if(sc == null){
            sc = new Scanner(System.in);

        }else{
            sc.next();
        }

        String s;

        while(sc.hasNext()){
            s = sc.nextLine();
            if(s.toUpperCase().equals("Y")){
                return true;

            }else if(s.toUpperCase().equals("N")){
                return false;

            }else{
                viewer.view("Please, write 'Y' or 'N' ");
                if(readAnswerAboutYesNo()){
                    return true;
                }
            }
        }
        return false;
    }

    private int readAnswerAboutCNumberS(){
        Scanner sc = null;

        if(sc == null){
            sc = new Scanner(System.in);

        }else{
            sc.next();
        }
        String s;

        while(sc.hasNext()){
            s = sc.nextLine();
            int number = -1;
            int flag;
            try{
                number = Integer.valueOf(s);
            }catch (NumberFormatException e){

            }
            if(s.toUpperCase().equals("C")){
                return 0;

            }else if(number >= 0) {
                rentOrReturn(String.valueOf(number));
                return 1;

            }else if(s.toUpperCase().equals("S")){
                return 2;

            }else{
                viewer.view("Please, write 'C' or 'Number' or 'S' ");
                if((flag = readAnswerAboutCNumberS()) >= 0){
                    return flag;
                }
            }
        }
        return -1;
    }

    private int readChoosingCategory(){
        Scanner sc = null;

        if(sc == null){
            sc = new Scanner(System.in);

        }else{
            sc.next();
        }
        String s;

        while(sc.hasNext()) {
            s = sc.nextLine();
            int number = -1;
            int flag;

            try {
                number = Integer.valueOf(s);
            } catch (NumberFormatException e) {
                viewer.view(getCategoryNumbers());
                if ((flag = readChoosingCategory()) >= 0) {
                    return flag;
                }
            }
            for (int i = 0; i < categoryList.size(); i++) {
                if (categoryList.get(i).getId() == number) {
                    return number;
                }
            }
            viewer.view(getCategoryNumbers());
            if ((flag = readChoosingCategory()) >= 0) {
                return flag;
            }
        }

        return -1;
    }

    private String showEquipmentTableByCategory(int choice){
        SportEquipment se;
        StringBuilder sb = new StringBuilder();
        choosenCategory = choice;

        sb.append(String.format("\n%1s %-5s %-50s %10s %10s\n", " ", "#", "Name of equipment", "Price, $", "Amount"));
        sb.append(String.format("%1s %-5s %-50s %10s %10s\n", "|", "-", "-----------------", "--------", "------"));

        for (Map.Entry<SportEquipment, Integer> entry: shopService.getData().getGoods().entrySet()) {
            se = entry.getKey();
            if(se.getCategory().getId() == choice){
                sb.append(String.format("%1s %-5d %-50s %10.2f %9d %1s\n", "|", se.getId(), se.getTitle(), se.getPrice(), entry.getValue(), "|"));
            }

        }
        sb.append(getEquipmentNumbersByCategory());
        return sb.toString();
    }

    private String showChooseEquipmentOrReturnToMainMenu(){
        return Speakers.CHOOSE_EQUIPMENT_OR_RETURN.speak();
    }

    private String getEquipmentNumbersByCategory(){
        StringBuilder sb = new StringBuilder();
        sb.append("\n choose: ");
        Set<Map.Entry<SportEquipment, Integer>> equipmentEntrySet = shopService.getData().getGoods().entrySet();


        for (Map.Entry<SportEquipment, Integer> entry: equipmentEntrySet) {
            SportEquipment se = entry.getKey();
            if(choosenCategory == se.getCategory().getId()){
                sb.append(se.getId() + " ");
            }
        }
        sb.append("or 'R': ");
        return sb.toString();
    }

    private String readChoosingEquipment(){
        Scanner sc = null;

        if(sc == null){
            sc = new Scanner(System.in);

        }else{
            sc.next();
        }
        String s;

        while(sc.hasNext()) {
            s = sc.nextLine();
            int number = -1;
            String flag = "";

            if(s.toUpperCase().equals("R")){
                flag = "Return";
                return flag;
            }

            try {
                number = Integer.valueOf(s);
            } catch (NumberFormatException e) {

            }
            if(number >= 0){
                if(searchedEquipment.size()>0){
                    for (SportEquipment se : searchedEquipment) {
                        if(number == se.getId()){
                            flag = String.valueOf(number);
                            return flag;
                        }
                    }
                }
                Set<Map.Entry<SportEquipment, Integer>> equipmentEntrySet = shopService.getData().getGoods().entrySet();
                for (Map.Entry<SportEquipment, Integer> entry : equipmentEntrySet) {
                    if(entry.getKey().getCategory().getId() == choosenCategory){
                        if(entry.getKey().getId() == number){
                            flag = String.valueOf(number);
                            return flag;
                        }
                    }
                }

            }
            if(searchedEquipment.size()>0){
                viewer.view(getEquipmentNumbersBySearching());
            }else{
                viewer.view(getEquipmentNumbersByCategory());
            }
            if (flag.toUpperCase().equals("ERROR")) {
                return "Error";
            }
        }

        return "Error";
    }

    private void rentOrReturn(String answer){

        if(answer.toUpperCase().equals("RETURN")){
            return;
        }
        int sportEquipmentId = Integer.valueOf(answer);

        Set<Map.Entry<SportEquipment, Integer>> equipmentEntrySet = shopService.getData().getGoods().entrySet();

        for (Map.Entry<SportEquipment, Integer> entry: equipmentEntrySet) {
            SportEquipment se = entry.getKey();
            if(sportEquipmentId == se.getId()){
                shopService.rentEquipment(se);
                break;
            }
        }
    }

    private String showSuggestionAboutSearchingEquipment(){
        return Speakers.SEARCHING_SUGGESTION.speak();
    }

    private String readSearchingEquipment(){
        Scanner sc = null;

        if(sc == null){
            sc = new Scanner(System.in);

        }else{
            sc.next();
        }
        String s;

        while(sc.hasNext()) {
            s = sc.nextLine();
            String flag = "";

            if(s.toUpperCase().equals("RETURN")){
                flag = "RETURN";
                return flag;
            }
            return s;
        }
        return null;
    }

    private String showSearchedEquipmentOrReturnToMainMenu(String request){
        searchedEquipment.clear();
        searchedEquipment = shopService.searchEquipment(request);

        if(searchedEquipment == null || searchedEquipment.size() == 0){
            viewer.view(Speakers.BAD_SEARCHING_RESULT.speak());
            showSearchedEquipmentOrReturnToMainMenu(readSearchingEquipment());

            return "Good!";
        }else{
            StringBuilder sb = new StringBuilder();

            sb.append(String.format("\n%1s %-5s %-50s %10s %10s\n", " ", "#", "Name of equipment", "Price, $", "Amount"));
            sb.append(String.format("%1s %-5s %-50s %10s %10s\n", "|", "-", "-----------------", "--------", "------"));

            for (SportEquipment se: searchedEquipment) {
                sb.append(String.format("%1s %-5d %-50s %10.2f %9d %1s\n", "|", se.getId(), se.getTitle(), se.getPrice(), 1, "|"));
            }

            sb.append(getEquipmentNumbersBySearching());
            return sb.toString();
        }
    }

    private String getEquipmentNumbersBySearching(){
        StringBuilder sb = new StringBuilder("\nChoose ");
        for (SportEquipment se: searchedEquipment) {
            sb.append(se.getId() + " ");
        }
        sb.append("or 'R'");
        return sb.toString();
    }



}
