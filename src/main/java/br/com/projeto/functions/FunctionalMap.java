package br.com.projeto.functions;

import java.util.stream.Stream;

/**
 * @param <E>
 * @author jefferson
 */
public abstract class FunctionalMap<E> {

    public final <T> Stream<T> map(final Function<E, T> function){

        final Stream.Builder<T> builder = Stream.builder();

        while (hasNext()) {

            final T action = function.action(next());

            builder.add(action);

        }

        return builder.build();

    }

    protected abstract boolean hasNext();

    protected abstract E next();

}
