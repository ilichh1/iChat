/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbm;

import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author RigoBono
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    private static final ThreadLocal localSession;

    /**
     * este bloque de codigo de tipo static es una especie de constructor pero
     * solo para atributos de tipo static la principal diferencia con un
     * constructor es que el constructor se ejecuta cuando creas el objeto esto
     * quiere decir que se puede ejecutar mas de una vez durante la ejecucion
     * del programa. sin embargo el bloque static solo se ejecuta una sola vez
     * sin importar la cantidad de objetos que sean creados es comunmente
     * utilizado para inicializar atributos de tipo static final ya el valor de
     * estos atributos son compartidos por todos los objetos creados y su valor
     * no puede cambiar durante toda la ejecucion del programa
     */
    static {
        // intentata ejecutar esta seccion de codigo haciendo referencia a que puede ocurrir un error en la ejecucion
        try {
            /* Este objeto es utilizado para cargar toda la configuracion establesida en un archivo XML; tal 
               configuracion es utilizada para establecer la coneccion con la base de datos, ademas que hace
               referencia a todos los objetos que representaran las tablas en la base de datos*/
            Configuration config = new Configuration();
            config.configure("hibernate.cfg.xml"); // carga el archivo de configuracion para la base de datos
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
            sessionFactory = config.buildSessionFactory(builder.build());
        } catch (Throwable ex) { // si ocurre un error al ejecutar el codigo se ejecutara esta seccion de codigo
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex); // imprime la eepcion que genero el codigo dentro del try
            throw new ExceptionInInitializerError(ex);
        }
        localSession = new ThreadLocal();
    }

    /**
     * debuelve la sessionFactory
     *
     * @return
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getLocalSession() {
        Session session = (Session) localSession.get();
        if (session == null) {
            session = sessionFactory.openSession();
            localSession.set(session);
        }
        return session;
    }

    public static void closeLocalSession() {
        Session session = (Session) localSession.get();
        if (session != null) {
            session.close();
        }

        localSession.set(null);
        System.out.println("sesion cerrada\n");
    }
}
