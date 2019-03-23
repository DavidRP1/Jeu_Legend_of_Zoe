public class Sortie extends Cellule {

    /**
     * Constructeur qui prend en parametre les coordonnees
     *
     * @param coordX coordonnee x de la sortie dans le niveau
     * @param coordY coordonnee y de la sortie dans le niveau
     */
    public Sortie(int coordX, int coordY) {
        super(coordX, coordY);
    }

    /**
     * Affiche la sortie comme un caratere "E"
     *
     * @return le String "E"
     */
    @Override
    public String toString(){
        return "E";
    }
}
