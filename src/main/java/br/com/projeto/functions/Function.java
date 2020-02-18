package br.com.projeto.functions;

/**
 * @author jefferson
 * @param <E>
 * @param <T>
 */
@FunctionalInterface
public interface Function<E, T> {

    T action(final E e);

}
