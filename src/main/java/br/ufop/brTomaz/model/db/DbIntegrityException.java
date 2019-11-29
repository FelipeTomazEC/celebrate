package br.ufop.brTomaz.model.db;

public class DbIntegrityException extends RuntimeException{
    public DbIntegrityException(String message) {
        super(message);
    }
}
