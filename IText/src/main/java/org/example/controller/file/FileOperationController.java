package org.example.controller.file;

/**
 * Class which define an operation on file.
 * @param <T> return type
 * @param <V> params type
 */
public interface FileOperationController<T, V> {

    /**
     * Methods to define a generic operation on file.
     * @param v V type
     * @return T type
     */
    T operationOnFile(V v);
}
