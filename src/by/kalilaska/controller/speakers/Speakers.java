package by.kalilaska.controller.speakers;

/**
 * Created by lovcov on 11.05.2017.
 */
public enum Speakers {
    GREATINGS("Hello! This is a Sport shop and we have good sport equipment!\n"),
    BYE_BYE("Bye Bye!"),

    YOU_DID_NOT_RENT_ANYTHING("\nYou didn't rent anything "),
    RENTS_LIST("\nYour rents: "),

    WANT_TO_RENT_SMTH("\nDo you want to rent some thing? Y/N "),
    WANT_TO_RENT_SMTH_ELSE("\nDo you want to rent some thing else? Y/N "),
    CAN_NOT_RENT("\nYou can not rent anything"),

    MAIN_MENU("\nIf you want to choose category write 'C' \n" +
            "If you want to choose equipment write its Number \n" +
            "If you want to search equipment write 'S' "),
    CHOOSE_EQUIPMENT_OR_RETURN("\nIf you want to choose equipment write its Number \n" +
            "If you want to return to the main menu write 'R' "),

    SEARCHING_SUGGESTION("\n Write all name or part of name "),
    BAD_SEARCHING_RESULT("\n We didn't find anything, write again or 'return' to return to main menu "),

    SHOP_CLOSED("SHOP CLOSED!!!");

    private String message;

    private Speakers(String message){
        this.message = message;
    }

    public String speak(){
        return message;
    };
}
