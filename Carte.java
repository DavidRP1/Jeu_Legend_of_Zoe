import java.util.ArrayList;
import java.util.Iterator;

public class Carte {
    private Object[][] tabCellules;  // tableau de cellules qui represente la carte de base d'un tableau
    private int m_nbre_lignes = 0;   // nbre de lignes dans le tableau
    private int m_longueurLigne = 0; // nbre de cellules par ligne

    /**
     * Genere les murs dans la carte d'un niveau
     * @param carteNiveau la carte du niveau
     */
    public void genererMurs(boolean[][] carteNiveau){
        m_nbre_lignes = carteNiveau.length;
        m_longueurLigne = carteNiveau[0].length;

        tabCellules = new Object[carteNiveau.length][carteNiveau[0].length];

        for (int i = 0; i < carteNiveau.length ; i ++){
            boolean[] ligneTab = carteNiveau[i];
            Object[] ligneCellules = new Object[ligneTab.length];
            for(int j =0; j < ligneTab.length; j++){
                if(ligneTab[j] == true){
                    Mur mur = new Mur(j,i);
                    ligneCellules[j] = mur;
                }
            }
            tabCellules[i] = ligneCellules;
        }
    }

    /**
     * Affiche a la console la carte du niveau avec les monstres et Zoe
     * @param zoe personnage principal
     * @param monstres listes des monstres dans le niveau
     */
    public void afficher(Zoe zoe, ArrayList<Monstre> monstres){

        // cree une carte de symboles ASCII
        int longueurTab = this.tabCellules.length;
        int longueurLigne = this.tabCellules[0].length;
        String[][] carteCaracteres = new String[longueurTab][longueurLigne];

        for(int i = 0; i < longueurTab; i++){
            for(int j = 0; j < longueurLigne; j++){
                if (this.tabCellules[i][j] != null){
                    carteCaracteres[i][j] = this.tabCellules[i][j].toString();
                } else {
                    carteCaracteres[i][j] = " ";
                }
            }
        }

        // place les monstres sur la carte
        Iterator<Monstre> it = monstres.iterator();
        while (it.hasNext()) {
            Monstre monstre = it.next();
            int x = monstre.getCoordX();
            int y = monstre.getCoordY();

            if (monstre.getVivant()) { // priorite d'affichage pour les monstres vivants
                carteCaracteres[y][x] = monstre.toString();
            } else if (!monstre.getVivant() && carteCaracteres[y][x].equals(" ")) {
                carteCaracteres[y][x] = monstre.toString();
            }
        }

        // place Zoe sur la carte
        carteCaracteres[zoe.getCoordY()][zoe.getCoordX()] = zoe.toString();

        // affiche la carte contenant Zoe et les monstres
        String chaineCarac = ""; //
        for(int i = 0; i < carteCaracteres.length; i ++){
            String[] ligne = carteCaracteres[i];
            for(int j = 0; j < ligne.length; j++) {
                chaineCarac += ligne[j];
            }
            System.out.println(chaineCarac);
            chaineCarac = "";
         }
    }

    /**
     * retourne un item selon le type demande
     *
     * @param typeItem type d'item demande
     * @return retourne l'item approprie
     */
    public Item getItem(String typeItem){
        if(typeItem.equals("coeur")){
            Item coeur = new Coeur();
            return coeur;
        } else if(typeItem.equals("potionvie")){
            Item potion = new Potion();
            return potion;
        } else if(typeItem.equals("hexaforce")){
            Item hexa = new Hexaforce();
            return hexa;
        } else {
            return null;
        }
    }

    /**
     * Permet de modifier une cellule (case) dans la carte du niveau
     *
     * @param cellule cellule (case) a modifier
     */
    public void setCellule(Cellule cellule){
        if(!this.positionValide(cellule.getX(), cellule.getY())) {
            return;
        }

        this.tabCellules[cellule.getY()][cellule.getX()] = cellule;
    }

    /**
     * Permet de modifier une cellule (case) de la carte du niveau afin qu'elle soit vide
     *
     * @param coordX coordonnee en X de la cellule
     * @param coordY coordonnee en Y de la cellule
     */
    public void setCelluleNulle(int coordX, int coordY){
        if(!this.positionValide(coordX, coordY))
            return;

        this.tabCellules[coordY][coordX] = null;
    }

    /**
     * Permet d'obtenir un objet dans la carte du niveau
     * @param coordX coordonnee en X de la cellule contenant l'objet
     * @param coordY coordonnee en Y de la cellule contenant l'objet
     * @return
     */
    private Object getObjet(int coordX, int coordY){
        if(!this.positionValide(coordX, coordY))
            return null;

        return  this.tabCellules[coordY][coordX];
    }

    /**
     * Permet d'indiquer si le deplacement d'un personnage dans la carte est valide
     * @param coordX coordonnee X ou le personnage veut se deplacer
     * @param coordY coordonnee Y ou le personnage veut se deplacer
     * @return
     */
    public boolean deplacementValide(int coordX, int coordY){

        // si l'emplacement est a l'exterieur de la carte, le deplacement n'est pas valide
        if(!this.positionValide(coordX, coordY))
            return false;

        Object obj = this.getObjet(coordX, coordY);

        // si l'emplacement est vide ou deja occupe par un personnage, le deplacement est valide
        if (obj == null || obj instanceof Personnage){
            return true;
        // pour tout autre cas, le deplacement n'est pas valide
        } else {
            return false;
        }
    }

    /**
     * Permet de verifier si une position d'un personnage a l'interieur de la carte est valide
     *
     * @param coordX coordonnee X du personnage
     * @param coordY coordonnee Y du personnage
     * @return
     */
    public boolean positionValide(int coordX, int coordY){
        if( !(coordX >= 0 && coordX < m_longueurLigne &&
                coordY >= 0 && coordY < m_nbre_lignes) ){
            return false;
        }
        return  true;
    }

    /**
     * Indique si le type d'objet contenu dans une cellule (case) de la carte du niveau est d'un type recherche
     *
     * @param coordX coordonnee X de la cellule
     * @param coordY coordonnee Y de la cellule
     * @param type type d'objet recherche
     * @return
     */
    public boolean typeCellule(int coordX, int coordY, String type) {
        if(!this.positionValide(coordX, coordY))
            return false;

        Object obj = this.getObjet(coordX, coordY);

        if (obj instanceof Mur && type == "MUR"){
            return true;
        } else if (obj instanceof Tresor && type == "TRESOR"){
            Tresor tresor = (Tresor) obj;
            // indique si le tresor est encore ferme
            if(!tresor.getEtat()) {
                return true;
            }
            return false;
        } else if (obj instanceof Sortie && type == "SORTIE"){
            return true;
        } else if (obj == null && type == "VIDE"){
            return true;
        } else{
            return false;
        }
    }

    /**
     * Indique si une cellule avoisinante a un personnage contient un type d'objet recherche
     *
     * @param personnage personnage dans le niveau
     * @param carte carte du niveau
     * @param type type d'objet recherche
     * @return
     */
    public boolean typeCelAvoisinante(Personnage personnage, Carte carte, String type){
        int x = personnage.getCoordX();
        int y = personnage.getCoordY();

        for(int i = x - 1; i <= x + 1; i ++){
            for(int j = y - 1; j <= y + 1; j ++){
                boolean estTypeRecherche = carte.typeCellule(i, j, type);
                if (estTypeRecherche){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Faire disparaitre un mur de la carte
     *
     * @param coordX coordonnee X du mur
     * @param coordY coordonnee Y du mur
     */
    public void creuserMur(int coordX, int coordY){

        if(!this.positionValide(coordX, coordY))
            return;

        Object obj = this.getObjet(coordX,coordY);

        if (obj instanceof Mur){
            setCelluleNulle(coordX, coordY);
        }
    }

    /**
     * Ouvre un tresor contenu dans la carte du jeu et donne l'item contenu a Zoe
     *
     * @param coordX coordonnee X du tresor
     * @param coordY coordonnee Y du tresor
     * @return
     */
    public Item ouvrirTresor(int coordX, int coordY){

        if(!this.positionValide(coordX, coordY))
            return null;

        Object obj = this.getObjet(coordX,coordY);

        if (obj instanceof Tresor) {
            Tresor tresor = (Tresor) obj;
            return tresor.donnerItem();
        } else {
            return null;
        }
    }
}