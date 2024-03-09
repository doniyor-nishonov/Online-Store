package uz.pdp.backend.service.support;

import java.util.List;
@FunctionalInterface
public interface GetAll<E> {
    List<E> getAll();
}
