public class Item {
    private String item_type = ""; // le type d'item

    /***
     * Constructeur prenant comme parametre le type d'item
     *
     * @param item_type type de l'item
     */
    public Item(String item_type){
        this.item_type = item_type;
    }

    /**
     * retourne le type de l"item
     *
     * @return le type de l'item
     */
    public String getItemType(){
        String copie = this.item_type;
        return copie;
    }
}
