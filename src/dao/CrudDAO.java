package dao;

import java.util.List;
import model.DatabaseException;

/**
 * Interface generik untuk operasi dasar CRUD (Create, Read, Update, Delete) pada lapisan DAO.
 * @param <T> tipe entitas model
 */
public interface CrudDAO<T> {

    void tambah(T data) throws DatabaseException;

    List<T> semua() throws DatabaseException;

    void ubah(T data) throws DatabaseException;

    void hapus(T data) throws DatabaseException;
}

