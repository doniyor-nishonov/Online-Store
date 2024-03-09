package uz.pdp.backend.service.support;

import java.util.List;

public interface Search<E> {
    E findById(String id);
    List<E> search(String query);
}
