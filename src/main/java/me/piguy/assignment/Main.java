package me.piguy.assignment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.piguy.assignment.database.KVDatabase;
import me.piguy.assignment.models.Role;
import me.piguy.assignment.models.User;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Main extends Application {
    private ScheduledExecutorService scheduler;
    @Override
    public void start(Stage stage) throws IOException {
        // load my config
        ConfigurationManager config = new ConfigurationManager();
        KVDatabase<String, User> userdb = config.userDB;

        // Initialise DB
        userdb.setValue("joey_nonsensejp", new User("Joey Bizinger", Role.Sales, "nonsense"));
        userdb.setValue("cdawgva", new User("Connor Colquhoun", Role.Sales, "jumpking"));
        userdb.setValue("notgrant", new User("Garnt \"Grant\" Maneetapho", Role.IT, "isekai"));
        userdb.setValue("1", new User("Garnt \"Grant\" Maneetapho", Role.IT, "1"));

        // Set the scheduler field
        // This is so I can gracefully shut it down
        scheduler = config.scheduler;

        // Load the login window
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-screen.fxml"));
        fxmlLoader.setController(new LoginScreenController(config));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Open music dungeon - Login");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void stop() {
        scheduler.shutdown();
    }

    public static void main(String[] args) {
        launch();
    }
}