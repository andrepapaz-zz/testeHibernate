package com.testehibernate.Dao.Impl;

import com.testehibernate.Bean.Editora;
import com.testehibernate.Dao.EditoraDAO;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by andrepapazoglu on 15/05/15.
 */
public class EditoraDAOImpl implements EditoraDAO {

    private Session session;

    public EditoraDAOImpl(Session session) {
        this.session = session;
    }

    @Override
    public void adicionar(Editora editora) {
        session.beginTransaction();
        session.save(editora);
        session.getTransaction().commit();
    }

    @Override
    public void alterar(Editora editora) {
        session.beginTransaction();
        session.update(editora);
        session.getTransaction().commit();

    }

    @Override
    public void deletar(Editora editora) {
        session.beginTransaction();
        session.delete(editora);
        session.getTransaction().commit();

    }

    @Override
    public Editora buscar(Long id) {
        return (Editora) session.get(Editora.class, id);
    }

    @Override
    public Editora buscaPorNome(String nome) {

        List<Editora> list = session.createCriteria(Editora.class).add(Restrictions.eq("nome", nome)).list();

        if (list.size() == 0) {
            return null;
        }

        return list.get(0);

    }

    @Override
    public List<Editora> listAll() {
        return session.createCriteria(Editora.class).list();
    }
}
