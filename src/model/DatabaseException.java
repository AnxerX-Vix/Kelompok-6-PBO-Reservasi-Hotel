package model;

/**
 * Custom Exception untuk membungkus kegagalan operasi basis data (SQLException)
 * dengan pesan yang informatif dan ramah pengguna.
 */
public class DatabaseException extends Exception {

    public DatabaseException(String pesan) {
        super(pesan);
    }

    public DatabaseException(String pesan, Throwable penyebab) {
        super(pesan, penyebab);
    }
}

