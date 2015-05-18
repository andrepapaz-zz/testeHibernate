package com.testehibernate.Dao;

import com.testehibernate.Bean.Editora;

import java.util.List;

/**
 * Created by andrepapazoglu on 15/05/15.
 */
public interface EditoraDAO {
    public void adicionar(Editora editora);
    public void alterar(Editora editora);
    public void deletar(Editora editora);
    public Object buscar(Long id);

    public Editora buscaPorNome(String nome);

    public List<Editora> listAll();
}
