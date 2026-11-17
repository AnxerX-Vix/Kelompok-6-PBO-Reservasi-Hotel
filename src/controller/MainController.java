package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import model.Kamar;
import model.StatusKamar;
import util.SceneNavigator;

/**
 * Controller utama untuk MainDashboardView.fxml.
 * Mengoordinasikan 4 sub-controller tab (KamarTabController, PelangganTabController,
 * ReservasiTabController, RiwayatTabController) dan memperbarui Status Bar aplikasi.
 */
public class MainController {

    @FXML private TabPane tabPaneDashboard;
    @FXML private Tab tabKamar;
    @FXML private Tab tabPelanggan;
    @FXML private Tab tabReservasiBaru;
    @FXML private Tab tabRiwayatReservasi;

    // Injeksi otomatis nested controllers dari fx:include
    @FXML private KamarTabController kamarTabController;
    @FXML private PelangganTabController pelangganTabController;
    @FXML private ReservasiTabController reservasiTabController;
    @FXML private RiwayatTabController riwayatTabController;

    @FXML private Label lblStatusBar;

    @FXML
    private void initialize() {
        // Pasang callback sinkronisasi lintas tab
        if (kamarTabController != null) {
            kamarTabController.setOnDataChangedCallback(this::sinkronisasiLintasTab);
        }
        if (pelangganTabController != null) {
            pelangganTabController.setOnDataChangedCallback(this::sinkronisasiLintasTab);
        }
        if (riwayatTabController != null) {
            riwayatTabController.setOnDataChangedCallback(this::sinkronisasiLintasTab);
        }

        // Listener pergantian tab untuk memastikan data selalu up-to-date saat dibuka
        tabPaneDashboard.getSelectionModel().selectedIndexProperty().addListener((obs, lama, indexBaru) -> {
            if (indexBaru.intValue() == 2 && reservasiTabController != null) {
                reservasiTabController.muatDataPilihan();
            } else if (indexBaru.intValue() == 3 && riwayatTabController != null) {
                riwayatTabController.muatData();
            }
            perbaruiStatusBar();
        });

        perbaruiStatusBar();
    }

    /**
     * Memilih tab tertentu secara programatik (digunakan oleh MenuBar / SceneNavigator).
     * @param index 0: Kamar, 1: Pelanggan, 2: Reservasi Baru, 3: Riwayat Reservasi
     */
    public void pilihTab(int index) {
        if (tabPaneDashboard != null && index >= 0 && index < tabPaneDashboard.getTabs().size()) {
            tabPaneDashboard.getSelectionModel().select(index);
        }
        sinkronisasiLintasTab();
    }

    /**
     * Memuat ulang data pada seluruh tab setelah terjadi perubahan (tambah/ubah/hapus/check-out).
     */
    public void sinkronisasiLintasTab() {
        if (kamarTabController != null) {
            kamarTabController.muatData();
        }
        if (pelangganTabController != null) {
            pelangganTabController.muatData();
        }
        if (reservasiTabController != null) {
            reservasiTabController.muatDataPilihan();
        }
        if (riwayatTabController != null) {
            riwayatTabController.muatData();
        }
        perbaruiStatusBar();
    }

    /**
     * Memperbarui label status bar di bagian bawah jendela aplikasi.
     */
    public void perbaruiStatusBar() {
        if (lblStatusBar == null) return;

        String adminName = SceneNavigator.getCurrentAdmin() != null ?
                SceneNavigator.getCurrentAdmin().getUsername() : "Admin";

        long totalKamar = 0;
        long tersedia = 0;

        if (kamarTabController != null && kamarTabController.getListKamar() != null) {
            totalKamar = kamarTabController.getListKamar().size();
            tersedia = kamarTabController.getListKamar().stream()
                    .filter(k -> k.getStatusKamar() == StatusKamar.TERSEDIA)
                    .count();
        }

        long terisi = totalKamar - tersedia;
        lblStatusBar.setText(String.format("Login sebagai: %s  |  Total Kamar: %d  |  Kamar Tersedia: %d  |  Kamar Terisi: %d",
                adminName, totalKamar, tersedia, terisi));
    }

    public KamarTabController getKamarTabController() {
        return kamarTabController;
    }

    public PelangganTabController getPelangganTabController() {
        return pelangganTabController;
    }

    public ReservasiTabController getReservasiTabController() {
        return reservasiTabController;
    }

    public RiwayatTabController getRiwayatTabController() {
        return riwayatTabController;
    }
}
