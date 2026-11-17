package model;

/**
 * Representasi kamar bertipe Suite.
 * Memberikan diskon progresif: 15% jika durasi >= 3 malam, dan 20% jika durasi >= 5 malam.
 */
public class KamarSuite extends Kamar {

    public KamarSuite(String id, double harga, StatusKamar status) {
        super(id, harga, status);
    }

    @Override
    public String getTipeKamar() {
        return "Suite";
    }

    @Override
    public String getFasilitas() {
        return "AC, Smart TV, Wi-Fi, Minibar, Ruang Tamu, Bathtub";
    }

    @Override
    public double hitungTarif(int durasi) {
        validasiDurasi(durasi);
        double total = getHargaDasar() * durasi;
        if (durasi >= 5) {
            return total * 0.80;
        }
        if (durasi >= 3) {
            return total * 0.85;
        }
        return total;
    }
}

