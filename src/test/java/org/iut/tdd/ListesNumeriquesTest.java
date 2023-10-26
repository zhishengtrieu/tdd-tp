package org.iut.tdd;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class ListesNumeriquesTest {
    private ListesNumeriques liste;
    private ArrayList<Integer> nb1;
    private ArrayList<Integer> nb2;
    private ArrayList<Integer> resultat;

    @BeforeEach
    void setup() {
        liste = new ListesNumeriques();
        nb1 = new ArrayList<Integer>();
        nb2 = new ArrayList<Integer>();
        resultat = new ArrayList<Integer>();
    }

    /**
     * On teste le cas d'un ajout simple
     */
    @Test
    void casDeBase() {
        nb1.add(1);
        nb1.add(4);
        nb1.add(2);
        nb2.add(1);
        nb2.add(3);

        resultat.add(1);
        resultat.add(5);
        resultat.add(5);
        assertEquals(resultat, liste.ajoute(nb1, nb2));
    }

    /**
     * On teste le cas d'un ajout avec retenue de 1
     */
    @Test
    void TestAvecRetenue() {
        // Liste 1 : 1 et liste 2 : 99 donc resultat 100
        nb1.add(1);
        nb2.add(9);
        nb2.add(9);

        resultat.add(1);
        resultat.add(0);
        resultat.add(0);

        assertEquals(liste.ajoute(nb1, nb2), resultat);
    }

    /**
     * Test pour verifier le cas d'une valeur invalide superieur a 9 dans la liste.
     */
    @Test
    void TestValeurSup9() {
        nb1.add(1);
        nb1.add(4);
        nb1.add(12);

        nb2.add(1);
        nb2.add(3);

        // On s'attend à ce qu'une exception soit levée à cause de la valeur invalide.
        assertThrows(IllegalArgumentException.class, () -> liste.ajoute(nb1, nb2));
    }

    /**
     * Cas avec des nombres negatifs
     */
    @Test
    void TestNombresNegatifs() {
        nb1.add(-8);
        nb1.add(6);
        nb2.add(2);
        nb2.add(-3);
        assertThrows(IllegalArgumentException.class, () -> liste.ajoute(nb1, nb2));
    }

    /**
     * On teste avec des listes null
     */
    @Test
    void TestListeNull() {
        nb1.add(1);
        nb1.add(4);
        nb2.add(7);
        nb2.add(3);

        assertNull(liste.ajoute(null, nb2));
        assertNull(liste.ajoute(nb1, null));
        assertNull(liste.ajoute(null, null));
    }

    /**
     * Test avec une liste vide.
     */
    @Test
    void TestListeVide() {
        // avec nb1 vide
        nb2.add(1);
        nb2.add(3);
        assertEquals(nb2, liste.ajoute(nb1, nb2));

        // avec nb2 vide
        nb1.add(1);
        nb1.add(4);
        nb2.clear();
        assertEquals(nb1, liste.ajoute(nb1, nb2));
    }

    /**
     * Test avec les 2 listes vides.
     */
    @Test
    void TestListesVides() {
        assertTrue(liste.ajoute(nb1, nb2).isEmpty());
    }

    /**
     * Cas avec 0
     */
    @Test
    void TestAvec0() {
        nb1.add(0);
        nb2.add(5);

        assertEquals(nb2, liste.ajoute(nb1, nb2));
    }

    /**
     * Cas d'un potentiel debordement
     */
    @Test
    void TestOverflow() {
        for (int i = 0; i < 100000; i++) {
            nb1.add(4);
            nb2.add(4);
            resultat.add(8);
        }

        assertEquals(resultat, liste.ajoute(nb1, nb2));
    }

}
