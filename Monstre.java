public class Monstre extends Personnage{

    private Item item; // item contenu dans le monstre
    private boolean item_donne = false; // indique si le monstre a deja donne son item (en mourant)

    /**
     * Constructeur qui prends en parametre le nombre initial de vies, la force,
     * la position initiale du monstre dans le niveau et l'item qu'il contient
     *
     * @param nbrVies nombre de vies restantes du monstre
     * @param force force du monstre
     * @param coordX coordonnee X du monstre sur la carte du niveau
     * @param coordY coordonnee Y du monstre sur la carte du niveau
     * @param item item libere lorsque le monstre meurt
     */
    public Monstre(int nbrVies, int force, int coordX, int coordY, Item item){
        super(nbrVies, force, coordX, coordY);
        this.item = item;
    }

    /**
     * Retourne l'item contenu dans le monstre
     *
     * @return l'item contenu dans le monstre
     */
    public Item donnerItem() {
        if(!item_donne) {
            item_donne = true;
            return this.item;
        }
        return null;
    }

    /**
     * permet au monstre de se deplacer sur la carte du niveau dans 8
     * directions(haut / bas / gauche / droite / diagonales)
     *
     * @param xProchain coordonnee X ou le monstre veut se deplacer
     * @param yProchain coordonnee Y ou le monstre veut se deplacer
     * @param carte carte du niveau
     */
    public void deplacer(int xProchain, int yProchain, Carte carte) {
        int x = this.getCoordX();
        int y = this.getCoordY();
        int xMin = x - 1;
        int xMax = x + 1;
        int yMin = y - 1;
        int yMax = y + 1;

        // verifie si le deplacement est valide
        if (carte.deplacementValide(xProchain, yProchain)
                && xProchain >= xMin && xProchain <= xMax
                && yProchain >= yMin && yProchain <= yMax) {
            super.deplacer(xProchain, yProchain, carte);
        }
    }


    /**
     * Affiche les monstres comme un caratere "@" si vivants et "x" si morts
     *
     * @return le String "@" si le monstre est vivant et "x" si mort
     */
    @Override
    public String toString(){
        if(this.getVivant()){
            return "@";
        } else {
            return "x";
        }
    }

    /**
     * Execute l'action appropriee pour le monstre selon l'emplacement de Zoe
     *
     * @param ennemi le personnage ennemi dans le jeu (i.e. Zoe)
     * @param carte carte du niveau
     */
    public void faireAction(Personnage ennemi, Carte carte){
        // si le monstre est mort, ne rien faire
        if( this.getVivant() == false)
            return;

        // si Zoe est pres, attaquer
        if(this.avoisinant(ennemi)){
            this.attaquer(ennemi);

        // sinon se deplacer vers Zoe
        } else {
            int xProchain = this.getCoordX();
            int yProchain = this.getCoordY();
            if(ennemi.getCoordX() < xProchain){
                xProchain -= 1;
            } else if (ennemi.getCoordX() > xProchain){
                xProchain += 1;
            }

            if(ennemi.getCoordY() < yProchain){
                yProchain -= 1;
            } else if (ennemi.getCoordY() > yProchain) {
                yProchain += 1;
            }

            this.deplacer(xProchain, yProchain, carte);
        }
    }
}
