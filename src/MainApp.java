import dao.DatabaseHelper;
import javafx.application.Application;
import javafx.stage.Stage;
import model.DatabaseException;
import util.SceneNavigator;

/**
 * Titik masuk utama (Main Entry Point) aplikasi Grand Hotel Management System (GHMS).
 */
public class MainApp extends Application {

    @Override
    public void init() throws Exception {
        super.init();
        try {
            // Inisialisasi basis data SQLite dan seeder awal
            DatabaseHelper.initDatabase();
        } catch (DatabaseException e) {
            System.err.println("Peringatan: Gagal menginisialisasi database saat startup: " + e.getMessage());
        }
    }

    @Override
    public void start(Stage primaryStage) {
        SceneNavigator.setPrimaryStage(primaryStage);
        SceneNavigator.bukaLogin();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

