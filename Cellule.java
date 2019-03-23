public class Cellule {
    private int coordX; // cordonnee X de la cellule
    private int coordY; // cordonnee Y de la cellule

    /**
     * Constructeur qui prend en parametre les coordonnees de la cellule (case)
     *
     * @param coordX coordonnee x de la cellule dans le niveau
     * @param coordY coordonnee y de la cellule dans le niveau
     */
    public Cellule(int coordX, int coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
    }

    /**
     * Affiche la cellule vide comme un caratere " "
     *
     * @return le String " "
     */
    @Override
    public String toString(){
        return " ";
    }

    /**
     * retourne la coordonnee X de la cellule dans la carte du niveau
     *
     * @return coordonnee X de la cellule dans la carte du niveau
     */
    public int getX(){
        int copie = this.coordX;
        return copie;
    }

    /**
     * retourne la coordonnee Y de la cellule dans la carte du niveau
     *
     * @return coordonnee Y de la cellule dans la carte du niveau
     */
    public int getY(){
        int copie = this.coordY;
        return copie;
    }
}
