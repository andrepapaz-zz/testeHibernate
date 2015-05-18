package com.testehibernate.Test.Dao.Impl;

import antlr.StringUtils;
import com.testehibernate.Bean.Editora;
import com.testehibernate.Dao.Impl.EditoraDAOImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;
import java.util.Scanner;

/**
 * Created by andrepapazoglu on 18/05/15.
 */
public class InsertNewPublishers {
    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory(new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry());

        Session session = sessionFactory.openSession();

        String entrada = "";

        Scanner entradaScanner = new Scanner(System.in);

        EditoraDAOImpl editoraDAO = new EditoraDAOImpl(session);

        while (!entrada.toUpperCase().equals("SAIR")) {

            System.out.println("------------------------Editoras------------------") ;
            List<Editora> editoraList = editoraDAO.listAll();
            if (editoraList.size() > 0) {
                for (Editora editora : editoraList) {
                    System.out.println("[" + editora.getNome() + "] - [" + editora.getEmail() + "]");
                }
            }
            System.out.println("--------------------------------------------------") ;

            Editora editora = new Editora();

            System.out.println(" Digite o nome da editora ou sair para sair : ") ;
            entrada = entradaScanner.nextLine() ;
            editora.setNome(entrada);

            if (entrada.toUpperCase().equals("SAIR")) {
                session.close();
                break;
            }

            System.out.println(" Digite o email da editora : ") ;
            entrada = entradaScanner.nextLine() ;
            editora.setEmail(entrada);

            Editora buscaPorNome = editoraDAO.buscaPorNome(editora.getNome());

            if (buscaPorNome != null) {
                System.out.println("Editora já existe, não é possível duplicidade!");
            } else {
                editoraDAO.adicionar(editora);
            }

            editoraDAO.adicionar(editora);

        }

    }
}
