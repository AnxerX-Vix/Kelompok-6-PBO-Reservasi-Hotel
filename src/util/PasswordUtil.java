package util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utilitas keamanan untuk hashing dan verifikasi kata sandi dengan algoritma SHA-256.
 */
public final class PasswordUtil {

    private PasswordUtil() {
        // Utility class
    }

    /**
     * Menghasilkan representasi heksadesimal dari hash SHA-256 kata sandi.
     * @param plainPassword teks kata sandi asli
     * @return 64-karakter string heksadesimal hash SHA-256
     */
    public static String hashPassword(String plainPassword) {
        if (plainPassword == null) {
            throw new IllegalArgumentException("Password tidak boleh null!");
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(plainPassword.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Algoritma SHA-256 tidak didukung pada sistem ini.", e);
        }
    }

    /**
     * Memverifikasi apakah kata sandi mentah cocok dengan hash SHA-256 yang tersimpan.
     */
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        if (plainPassword == null || hashedPassword == null) {
            return false;
        }
        return hashPassword(plainPassword).equalsIgnoreCase(hashedPassword.trim());
    }
}

