public class Personnage {
    private int nbrVies;           // nombre de vies du personnage
    private int force;             // nombre de points de force du personnage
    private int coordX;            // coordonne X de l'emplacement du personnage sur la carte
    private int coordY;            // coordonne Y de l'emplacement du personnage sur la carte
    private boolean vivant = true; // indique si le personnage est vivant ou mort

    /**
     * Constructeur qui prends en parametre le nombre initial de vies, la force
     * et la position initiale du personnage sur la carte du niveau
     *
     * @param nbrVies le nombre de points de vie restants
     * @param force le nombre de points de vie enleves a un adversaire lors d'une attaque
     * @param coordX coordonnee x representant ou se situe le personnage dans le niveau
     * @param coordY coordonnee y representant ou se situe le personnage dans le niveau
     */
    public Personnage(int nbrVies, int force, int coordX, int coordY){
        this.nbrVies = nbrVies;
        this.force = force;
        this.coordX = coordX;
        this.coordY = coordY;
    }

    /**
     * Indique le nombre de vies restantes du personnage
     *
     * @return nombre de vies restantes du personnage
     */
    public int getNbrvies(){
        int copie = this.nbrVies;
        return copie;
    }

    /**
     * Permet de modifier le nombre de vies restantes du personnage
     *
     * @param nbrVies nombre de vies restantes du personnage
     */
    public void setNbrvies(int nbrVies){
        if (nbrVies>=0) {
            this.nbrVies = nbrVies;
        }
    }

    /**
     * Indique la force du personnage
     *
     * @return la force du personnage
     */
    public int getForce(){
        int copie = this.force;
        return copie;
    }

    /**
     * Retourne la coordonnee x de l'emplacement du personnage dans le niveau
     *
     * @return coordonnee x du personnage dans le niveau
     */
    public int getCoordX(){
        int copie = this.coordX;
        return copie;
    }

    /**
     * Retourne la coordonnee y de l'emplacement du personnage dans le niveau
     *
     * @return coordonnee y du personnage dans le niveau
     */
    public int getCoordY(){
        int copie = this.coordY;
        return copie;
    }

    /**
     * Indique si le personnage est vivant ou mort
     *
     * @return booleen indiquant si le personnage est vivant ou mort
     */
    public boolean getVivant(){
        boolean copie = this.vivant;
        return copie;
    }

    /**
     * Modifie le nombre de vie d'un personnage suite a l'attaque d'un autre personnage
     *
     * @param force la force du personnage qui attaque
     */
    public void modifierVie(int force){
        if (this.nbrVies - force <= 0){
            this.vivant = false;
            this.nbrVies = 0;
        } else {
            this.nbrVies -= force;
        }
    }

    /**
     * permet au personnage de se deplacer dans la carte du niveau
     *
     * @param x coordonnee X ou le personnage veut se deplacer
     * @param y coordonnee y ou le personnage veut se deplacer
     * @param carte carte du niveau
     */
    public void deplacer(int x, int y, Carte carte){
        if(carte.deplacementValide(x, y)){ // faire le deplacement seulement s'il est possible
            this.coordX = x;
            this.coordY = y;
        }
    }

    /**
     * verifie si un autre personnage est avoisinant au personnage
     *
     * @param autrePerso autre personnage pour lequel l'emplacement est verifie
     * @return booleen qui indique si l'autre personnage est avoisinant
     */
    public boolean avoisinant(Personnage autrePerso){
        int x = this.getCoordX();
        int y = this.getCoordY();

        if(autrePerso.getCoordX() <= x + 1 &&
                autrePerso.getCoordX() >= x-1 &&
                autrePerso.getCoordY() <= y+1 &&
                autrePerso.getCoordY() >= y-1){
            return true;
        } else {
            return false;
        }
    }

    /**
     * permet d'attaquer un ennemi s'il est avoisinnant
     *
     * @param autrePerso ennemi a attaquer
     */
    public void attaquer(Personnage autrePerso){
        int force = this.getForce();

        if(this.avoisinant(autrePerso)){
            autrePerso.modifierVie(force);
        }
    }
}
