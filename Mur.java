public class Mur extends Cellule {

    /**
     * Constructeur qui prend en parametre les coordonnees du mur
     *
     * @param coordX coordonnee x du mur dans le niveau
     * @param coordY coordonnee y du mur dans le niveau
     */
    public Mur(int coordX, int coordY) {
        super(coordX, coordY);
    }

    /**
     * Affiche le mur comme un caratere "#"
     *
     * @return le String "#"
     */
    @Override
    public String toString(){
        return "#";
    }
}
