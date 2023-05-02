package binus.datastructure.algorithmvisualizer.views;

import binus.datastructure.algorithmvisualizer.MFXAppResourcesLoader;
import binus.datastructure.algorithmvisualizer.controllers.AlgorithmVisualizerControllers;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import fr.brouillard.oss.cssfx.CSSFX;
import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;

public class AlgorithmVisualizer extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Start CSSFX
        CSSFX.start();

        // Load MainWindow
        FXMLLoader loader = new FXMLLoader(MFXAppResourcesLoader.loadURL("fxml/AlgorithmVisualizer.fxml"));
        loader.setControllerFactory(c -> new AlgorithmVisualizerControllers(stage));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);
        scene.setFill(Color.TRANSPARENT);

        // Stage setup
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Algorithm Visualizer");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
