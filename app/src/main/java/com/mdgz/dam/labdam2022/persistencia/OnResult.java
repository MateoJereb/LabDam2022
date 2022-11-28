package com.mdgz.dam.labdam2022.persistencia;

public interface OnResult<T> {

    void onSucess(final T result);
    void onError(final Throwable exception);
}
