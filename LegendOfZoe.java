/**
 * David Raby-Pepin, Matricule: p918119
 * Zoe Wang,         Matricule: p20111352
 *
 * Date: 18 mars 2019
 *
 * Classe principale du programme.
 * Le programme est un petit jeu en ligne de commande inspiré de The Legend of Zelda et des jeux Roguelike.
 * Zoe, le personnage principal, doit partir à la recherche des pièces de l’Hexaforce pour rétablir l’ordre
 * dans le monde. Zoe avance à travers les 6 niveaux d’un donjon, chaque niveau cache une des pièces de
 * l’Hexaforce. Zoe doit collecter les 6 pieces d'Hexaforce afin de remporter la partie.
 *
 * NOTEZ : VOUS NE DEVEZ PAS RENOMMER CETTE CLASSE
 */

public class LegendOfZoe {

    public static void main(String[] args) {
        Messages.afficherIntro();
        boolean jeuGagne = false; // indique si la partie est remportee ou non
        int numNiveau = 1;        // indique le numero du niveau actuel

        // genere Zoe avec les proprietes par defaut
        Zoe zoe = new Zoe(5, 1, 0, 0, 0);

        // tant que Zoe n'a pas gagne ou perdu la partie, executer le jeu
        while(!jeuGagne){
            Niveau niveau = new Niveau(numNiveau, zoe);
            niveau.executer();
            if(niveau.getComplete()) {
                // si le 6e niveau est complete, la partie est gagnee
                if (numNiveau == 6) {
                    jeuGagne = true;
                // si un des niveaux avant le 6e est complete, passer au prochain niveau
                } else {
                    numNiveau += 1;
                }
            // sinon, la partie est perdue
            } else {
                break;
            }
        }

        // si la partie est gagnee, afficher un message de victoire
        if(jeuGagne){
            Messages.afficherVictoire();
        // sinon, afficher un message de defaite
        } else {
            Messages.afficherDefaite();
        }

    }     
}
