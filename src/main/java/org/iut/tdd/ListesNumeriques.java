package org.iut.tdd;

import java.util.ArrayList;
import java.util.List;

/**
* La classe ListesNumeriques permet d'ajouter 2 entiers représentés en utilisant des listes de chiffres.
* 
*/
class ListesNumeriques {

    /**
     * <p>La méthode considère 2 entiers qui sont représentés en utilisant des listes de 
     * chiffres, respectivement <code>nb1</code> et <code>nb2</code>.
     * La méthode ajoute ces 2 entiers et renvoie le résultat comme une liste de chiffres.
     * </p>
     * <p>Par exemple, si on veut ajouter les entiers 142 et 13, on doit créer une liste 
     * (nb1) avec trois éléments [1,4,2] et une liste (nb2) avec deux éléments [1,3].
     * Comme 142+13 = 155, le programme doit donc produire une liste avec trois éléments [1,5,5]
     * </p>
     * [1,4,2] + [1,3] = [1,5,5]
     * <p>
     * Chaque élément des listes nb1 et nb2 doit être compris entre 0 et 9.
     * Une exception <code>IllegalArgumentException</code> est levée si cette condition préalable n'est 
     * pas remplie.
     * </p>
     * 
     * @param nb1  liste qui contient le premier entier. Null renvoie <code>null</code>, vide signifie 0
     * @param nb2  liste qui contient le deuxième entier. Null renvoie <code>null</code>, vide signifie 0
     * @return la somme de nb1 et nb2 représentée comme une liste de chiffres
     */
    List<Integer> ajoute(List<Integer> nb1, List<Integer> nb2) {
        // on verifie que les listes ne sont pas null
        if (nb1 == null || nb2 == null) return null;

        // on verifie que le cas des listes vides
        if (nb1.isEmpty()) return nb2;
        if (nb2.isEmpty()) return nb1;

        int i = nb1.size() - 1;
        int j = nb2.size() - 1;

        int retenue = 0;
        int somme;
        List<Integer> resultat = new ArrayList<>();

        // on parcourt les listes en partant de la fin
        // on ne fait que j iterations si nb2< nb1 et inversement
        while (i >= 0 && j >= 0) {
            int n1 = nb1.get(i);
            int n2 = nb2.get(j);

            chiffreValide(n1);
            chiffreValide(n2);

            somme = nb1.get(i) + nb2.get(j) + retenue;
            resultat.addFirst(somme % 10);
            retenue = somme / 10;

            i--;
            j--;
        }

        // on parcourt la liste la plus longue
        List<Integer> nb = i<j ? nb2 : nb1;
        for(int k = i<j ? j : i; k>=0; k--){
            int n = nb.get(k);

            chiffreValide(n);
            somme = nb.get(k) + retenue;
            resultat.addFirst(somme % 10);
            retenue = somme / 10;
        }

        // on ajoute la retenue si elle existe
        if (retenue > 0) {
            resultat.addFirst(retenue);
        }

        return resultat;
    }


    private void chiffreValide(int chiffre) {
        if (chiffre < 0 || chiffre > 9) {
            throw new IllegalArgumentException("Les listes doivent contenir des chiffres entre 0 et 9");
        }
    }

}
