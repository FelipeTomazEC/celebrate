package br.ufop.brTomaz.model.db;

public class DbException extends RuntimeException {
    public DbException (String message) {
        super(message);
    }
}
