# Jeu_Legend_of_Zoe
Petit jeu en ligne de commande inspiré de The Legend of Zelda et des jeux Roguelike. Zoe, le personnage principal, doit partir à la recherche des pièces de l’Hexaforce pour rétablir l’ordre dans le monde. Elle avance à travers les 6 niveaux d’un donjon, chaque niveau cache une des pièces de l’Hexaforce. Zoe doit collecter les 6 pieces d'Hexaforce afin de remporter la partie.

Fichier principal: LegendofZoe.java

Commandes:

w: déplace Zoe une case vers le haut

a: déplace Zoe une case vers la gauche

s: déplace Zoe une case vers le bas

d: déplace Zoe une case vers la droite

c: creuser dans les murs avoisinants jusqu’à 8 murs voisins -- notez qu’on peut seulement creuser dans les murs, on ne peut pas creuser dans les trésors, monstres et autres

x: attaque tous les monstres dans le voisinage de Zoe, incluant les diagonales + la case sur laquelle Zoe se trouve (si jamais un monstre est sur la même case que Zoe)

o: ouvre tous les trésors dans le voisinage de Zoe jusqu’à 8 voisins

q: ferme le jeu d’un coup sec avec System.exit(0);
