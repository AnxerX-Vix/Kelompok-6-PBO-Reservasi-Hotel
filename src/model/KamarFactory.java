package model;

/**
 * Factory class untuk membuat instance polimorfik dari subclass Kamar
 * berdasarkan nama tipe kamar.
 */
public final class KamarFactory {

    private KamarFactory() {
        // Mencegah instansiasi utility class
    }

    public static Kamar buat(String tipe, String id, double harga, StatusKamar status) {
        if (tipe == null) {
            throw new IllegalArgumentException("Tipe kamar tidak boleh null!");
        }
        return switch (tipe.trim()) {
            case "Standard" -> new KamarStandard(id, harga, status);
            case "Deluxe"   -> new KamarDeluxe(id, harga, status);
            case "Suite"    -> new KamarSuite(id, harga, status);
            default -> throw new IllegalArgumentException("Tipe kamar tidak dikenal: " + tipe);
        };
    }
}

