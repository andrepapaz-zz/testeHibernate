package com.testehibernate.Test.Dao.Impl;

import com.testehibernate.Bean.Editora;
import com.testehibernate.Dao.EditoraDAO;
import com.testehibernate.Dao.Impl.EditoraDAOImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class EditoraDAOImplTest {

    public static Session session;
    private static SessionFactory ourSessionFactory;
    private static ServiceRegistry serviceRegistry;

    @Before
    public void setUP() {
        Configuration configuration = new Configuration();
        configuration.configure();

        serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);

        session = ourSessionFactory.openSession();
    }

    @Test
    public void testInsert() {
        Editora editora = new Editora();
        editora.setNome("teste");
        editora.setEmail("teste@");
        EditoraDAO editoraDAO = new EditoraDAOImpl(session);
        editoraDAO.adicionar(editora);

        List<Editora> editoraList = editoraDAO.listAll();
        System.out.println("editoraList.get(0).getNome() = " + editoraList.get(0).getNome());
        System.out.println("editoraList.get(0).getEmail() = " + editoraList.get(0).getEmail());
        assertEquals(editoraList.size(), 1);
    }

    @Test
    public void testDeleteAll() {
        EditoraDAO editoraDAO = new EditoraDAOImpl(session);
        List<Editora> editoraList = editoraDAO.listAll();

        for (Editora editora : editoraList) {
            editoraDAO.deletar(editora);
        }

        editoraList = editoraDAO.listAll();

        assertEquals(0, editoraList.size());

    }

    @Test
    public void testBusca() throws Exception {

        EditoraDAO editoraDAO = new EditoraDAOImpl(session);

        Editora editora = new Editora();
        editora.setNome("Abril");
        editora.setEmail("abril@abril.com");

        Editora editora2 = new Editora();
        editora2.setNome("Panini");
        editora2.setEmail("panini@panini.com");

        Editora editora3 = new Editora();
        editora3.setNome("marvel");
        editora3.setEmail("marvel@marvel.com");

        editoraDAO.adicionar(editora);
        editoraDAO.adicionar(editora2);
        editoraDAO.adicionar(editora3);

        Editora panini = editoraDAO.buscaPorNome("Panini");

        assertEquals("Panini", panini.getNome());
        assertEquals("panini@panini.com", panini.getEmail());

    }

    @After
    public void tearDown() {
        session.close();
    }

}