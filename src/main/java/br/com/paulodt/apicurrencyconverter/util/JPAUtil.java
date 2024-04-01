package br.com.paulodt.apicurrencyconverter.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * @author Dilnei
 *
 * Classe responsavel por fornecer as instâncias do Entity Manager. Quando a CDI
 * precisar criar o Objeto ela utilizará o método com
 * @Produces
 */
public class JPAUtil {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("api-currency-converter");

    //@RequestScoped
    //@Produces
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Recebe uma Entity Manager e faz o seu fechamento(@Disposes(ela sera descartada através desta annotation)).
    public static void close(EntityManager em) {
        em.close();
    }
}