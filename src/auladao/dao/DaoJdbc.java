package auladao.dao;

import java.util.List;

public interface DaoJdbc<T> {

    T findById(int id);

    T findByCPF(String cpf);

    T FindByName(String name);

    List<T> findAll();

    int insert(T obj);

    boolean update(T obj);

    boolean delete(T obj);

    int deleteMany(List<T> objs);

    int updateMany(List<T> objs);
}
