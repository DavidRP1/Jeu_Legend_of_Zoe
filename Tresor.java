public class Tresor extends Cellule {
    private Boolean etat = false; // le tresor est initialement ferme (false)
    private Item item;

    /**
     * Constructeur qui prend en parametre les coordonnees et le contenu du tresor
     *
     * @param coordX coordonnee x du tresor dans le niveau
     * @param coordY coordonnee y du tresor dans le niveau
     * @param item item contenu dans le tresor
     */
    public Tresor(int coordX, int coordY, Item item) {
        super(coordX, coordY);
        this.item = item;
    }

    /**
     * retourne l'etat du tresor
     *
     * @return
     */
    public Boolean getEtat(){
        Boolean copie = this.etat;
        return  copie;
    }

    /**
     * Affiche le tresor comme un caractere "$" s'il est ferme et comme "_" s'il est ouvert
     *
     * @return le String "$" si le tresor est ferme et "_" s'il est ouvert
     */
    @Override
    public String toString(){
        if(this.etat == false) {
            return "$";
        } else {
            return "_";
        }
    }

    /**
     * Retourne l'item contenu dans le tresor et change l'etat du tresor a ouvert
     *
     * @return l'item contenu dans le tresor
     */
    public Item donnerItem() {
        this.etat = true;
        return this.item;
    }
}
