import java.util.ArrayList;
import java.util.Iterator;

public class Zoe extends Personnage{
    private int nbreHexa; // nombre de pieces d'Hexaforce recoltees par Zoe

    /**
     * Constructeur qui prends en parametre le nombre initial de vies, la force,
     * la position initiale de Zoe dans le niveau et le nombre de pieces d'Hexaforce initial de Zoe
     *
     * @param nbrVies nombre de vies restantes pour Zoe
     * @param force force de Zoe
     * @param coordX coordonne X de l'emplacement de Zoe sur la carte de niveau
     * @param coordY coordonne Y de l'emplacement de Zoe sur la carte de niveau
     * @param nbreHexa nombre de pieces d'Hexaforce que Zoe a en sa possession
     */
    public Zoe(int nbrVies, int force, int coordX, int coordY, int nbreHexa){
        super(nbrVies, force, coordX, coordY);
        this.nbreHexa = nbreHexa;
    }

    /**
     * Indique le nombre de pieces d"Hexaforce que Zoe a recoltees
     *
     * @return nombre de pieces d"Hexaforce que Zoe a recoltees
     */
    public int getNbreHexa(){
        int copie = this.nbreHexa;
        return copie;
    }

    /**
     * permet a Zoe d'attaquer les monstres (1 a la fois)
     *
     * @param listeMonstres liste de Monstres dans le niveau
     */
    public void attaquer(ArrayList<Monstre> listeMonstres){
        int x = this.getCoordX();
        int y = this.getCoordY();
        int force = this.getForce();

        // attaque un seul monstre a la fois
        Monstre monstre = getMonstre(listeMonstres);
        if(monstre != null) {
            monstre.modifierVie(force);
            if (!monstre.getVivant()) {
                Item item = monstre.donnerItem();
                this.recevoirItem(item);
            }
        }
    }

    /**
     * Retourne un monstre avoisinant et vivant si possible
     *
     * @param listeMonstres liste de Monstres dans le niveau
     * @return un monstre avoisinant et vivant si possible
     */
    private Monstre getMonstre(ArrayList<Monstre> listeMonstres){
        Iterator<Monstre> it = listeMonstres.iterator();
        while (it.hasNext()) {
            Monstre monstre = it.next();
            if(this.avoisinant(monstre) && monstre.getVivant())
                return monstre;
        }
        return null;
    }

    /**
     * Permet a Zoe d'ouvrir les tresors (1 a la fois)
     *
     * @param carte carte du niveau
     */
    public void ouvrirTresor(Carte carte){
        // Verifie toutes les cellules dans le voisinnage de Zoe afin de trouver un tresor
        int x = this.getCoordX();
        int y = this.getCoordY();
        for(int i = x - 1; i <= x + 1; i ++){
            for(int j = y - 1; j <= y + 1; j ++){
                boolean estTresor = carte.typeCellule(i, j, "TRESOR");
                if (estTresor){
                    Item item = carte.ouvrirTresor(i, j);
                    this.recevoirItem(item);
                    // assure d'ouvrir 1 tresor a la fois.
                    return;
                }
            }
        }
    }

    /**
     * permet a Zoe de recevoir un item
     *
     * @param item l'item a recevoir
     */
    private void recevoirItem(Item item){
        // si l'item est un coeur, ajouter une vie
        if (item.getItemType() == "coeur") {
            int nbVie = this.getNbrvies() == 5 ? 5 : this.getNbrvies() + 1;
            this.setNbrvies(nbVie);
            // si l'item est une potion, remplir la vie
        } else if (item.getItemType() == "potion"){
            this.setNbrvies(5);
            // si l'item est une piece d'Hexaforce, ajouter une piece au compte
        } else if (item.getItemType() == "hexaforce"){
            this.nbreHexa++;
        }
        System.out.println("get item: " + item.getItemType());
    }

    /**
     * Permet a zoe de creuser les murs
     *
     * @param carte carte du niveau
     */
    public void creuserMur(Carte carte){
        //Zoe creuse tous les murs dans son voisinnage
        int x = this.getCoordX();
        int y = this.getCoordY();
        for(int i = x - 1; i <= x + 1; i ++){
            for(int j = y - 1; j <= y + 1; j ++){
                boolean estMur = carte.typeCellule(i, j, "MUR");
                if (estMur){
                    carte.creuserMur(i, j);
                }
            }
        }
    }

    /**
     * Affiche Zoe comme un caratere "&"
     *
     * @return le String "&"
     */
    @Override
    public String toString(){
        return "&";
    }

    /**
     * Execute l'action appropriee pour Zoe selon la commande entree a la console
     *
     * @param action caractere representant la commande entree a la console
     * @param carte carte du niveau
     * @param listeMonstres liste des monstres dans le niveau
     */
    public void faireAction(char action, Carte carte, ArrayList<Monstre> listeMonstres){
        int coordY = this.getCoordY();
        int coordX = this.getCoordX();
        switch (action){
            case 'w':
                coordY -= 1;
                this.deplacer(coordX, coordY, carte);
                break;
            case 's':
                coordY += 1;
                this.deplacer(coordX, coordY, carte);
                break;
            case 'a':
                coordX -= 1;
                this.deplacer(coordX, coordY, carte);
                break;
            case 'd':
                coordX += 1;
                this.deplacer(coordX, coordY, carte);
                break;
            case 'c':
                this.creuserMur(carte);
                break;
            case 'x':
                this.attaquer(listeMonstres);
                break;
            case 'o':
                this.ouvrirTresor(carte);
                break;
            case 'q':
                System.exit(0);
                break;
        }
    }
}