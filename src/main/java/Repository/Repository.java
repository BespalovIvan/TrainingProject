package Repository;

import java.util.List;

public interface Repository<T> {
    List<T> findAll();
    T find(Integer id);
}
