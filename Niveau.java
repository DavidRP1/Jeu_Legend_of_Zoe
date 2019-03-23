import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Niveau {
    private boolean m_complete;                 // indique si le niveau est complete
    private int m_numero ;                      // indique le numéro de niveau
    private Zoe m_zoe;                          // le personnage principal
    private ArrayList<Monstre> m_listeMonstres; // liste des monstres dans un niveau
    private Carte m_carte;                      // carte du jeu

    /**
     * Constructeur qui prends en parametre le numero du niveau et le personnage principal Zoe
     * @param numero numero du niveau
     * @param zoe personnage principal
     */
    public Niveau(int numero, Zoe zoe){
        m_complete = false;
        m_numero = numero;
        m_zoe = zoe;
        m_listeMonstres = new ArrayList<Monstre>();
    }

    /**
     * Getter pour m_complete
     * @return un booleen qui indique si le niveau est complete
     */
    public boolean getComplete(){
        boolean copie = m_complete;
        return copie;
    }

    /**
     * Initialise le niveau en cours
     */
    private void initialiser(){
        m_carte = new Carte();
        Paire<boolean[][], String[]>  parametresNiveau = LevelGenerator.generateLevel(m_numero);

        // genere les murs dans la carte du niveau
        m_carte.genererMurs(parametresNiveau.getKey());

        // genere les tresors, les monstres et la sortie dans la carte du niveau et l'emplacement de depart de Zoe
        String [] parametres = parametresNiveau.getValue();
        for(int i = 0; i < parametres.length; i ++){
            String [] params = parametres[i].split(":");
            Item item;
            int y;
            int x;

            switch(params[0]){
                case "tresor":
                    item = m_carte.getItem(params[1]);
                    y = Integer.parseInt(params[3]);
                    x = Integer.parseInt(params[2]);
                    Tresor tresor = new Tresor(x,y, item);
                    m_carte.setCellule(tresor);
                    break;
                case "monstre":
                    item = m_carte.getItem(params[1]);
                    y = Integer.parseInt(params[3]);
                    x = Integer.parseInt(params[2]);
                    int nbreVies = (int) Math.max( 0.6 * m_numero, 1);
                    int force = (int) Math.max( 0.4 * m_numero, 1);
                    Monstre monstre = new Monstre(nbreVies,force, x,y, item);
                    m_listeMonstres.add(monstre);
                    break;
                case "sortie":
                    y = Integer.parseInt(params[2]);
                    x = Integer.parseInt(params[1]);
                    Sortie sortie = new Sortie(x, y);
                    m_carte.setCellule(sortie);
                    break;
                case "zoe":
                    y = Integer.parseInt(params[2]);
                    x = Integer.parseInt(params[1]);
                    m_zoe.deplacer(x,y,m_carte);
                    break;
            }
        }
    }

    /**
     * Execute le niveau en cours
     */
    public void executer(){

        // initialise la carte du niveau
        this.initialiser();

        // affiche tous les elements du niveau
        m_carte.afficher(m_zoe, m_listeMonstres);

        // enregistre les commandes entrees par le joueur a la console
        String commandesJoueur = "";
        int index = 0;

        Scanner scan = new Scanner(System.in);

        // tant que Zoe est vivante, la partie continue
        while(m_zoe.getNbrvies() > 0){
            this.afficherInfo();

            // remplit le buffer de commandes lorsque celui-ci est vide
            if( commandesJoueur.isEmpty() || index == commandesJoueur.length()){
                commandesJoueur = scan.next();
                commandesJoueur = commandesJoueur.toLowerCase();
                index = 0;
            }

            // transforme les commandes en actions pour Zoe
            char action = commandesJoueur.charAt(index);
            m_zoe.faireAction(action, m_carte, m_listeMonstres);
            index ++;

            // fait agir les monstres
            Iterator<Monstre> it = m_listeMonstres.iterator();
            while (it.hasNext()){
                Monstre monstre = it.next();
                //si un monstre est mort, il ne fait plus d'actions
                if(monstre.getVivant()) {
                    monstre.faireAction(m_zoe, m_carte);
                }
            }

            // si Zoe obtient 6 pieces d'Hexaforce, elle gangne la partie immediatement
            if(m_zoe.getNbreHexa() == 6){
                this.m_complete = true;
                return;
            }

            // si Zoe obtient une piece d'Hexaforce et va sur une cellule avoisinante a
            // la sortie, elle change de niveau
            if(m_zoe.getNbreHexa() == this.m_numero
                    && m_carte.typeCelAvoisinante(m_zoe, m_carte,"SORTIE")){
                this.m_complete = true;
                return;
            }

            // si Zoe n'a plus de vie, elle perd la partie immediatement
            if(m_zoe.getNbrvies() <= 0){
                return;
            }
            m_carte.afficher(m_zoe, m_listeMonstres);
        }
    }

    /**
     * Affiche les informations pertinentes au jeu
     */
    private void afficherInfo(){
        System.out.println("---------- Niveau" + m_numero + " ----------");
        System.out.println("----------   Zoé   ----------");
        System.out.println("Vies:" + m_zoe.getNbrvies() + "  Pieces d’Hexaforce: " + m_zoe.getNbreHexa());
    }
}