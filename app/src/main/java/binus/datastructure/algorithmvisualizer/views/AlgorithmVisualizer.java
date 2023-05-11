package binus.datastructure.algorithmvisualizer.views;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import fr.brouillard.oss.cssfx.CSSFX;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;

import binus.datastructure.algorithmvisualizer.MFXAppResourcesLoader;
import binus.datastructure.algorithmvisualizer.controllers.AlgorithmVisualizerControllers;
import binus.datastructure.algorithmvisualizer.models.SortingModel;

public class AlgorithmVisualizer extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Initialize SortingModel
        SortingModel sortingModel = new SortingModel();

        // Start CSSFX
        CSSFX.start();

        // Load MainWindow
        FXMLLoader loader = new FXMLLoader(MFXAppResourcesLoader.loadURL("fxml/AlgorithmVisualizer.fxml"));
        loader.setControllerFactory(c -> new AlgorithmVisualizerControllers(stage));
        Parent root = loader.load();

        // Get the AlgorithmVisualizerControllers
        AlgorithmVisualizerControllers controller = loader.getController();

        // Create the randomize button with icon since it can't be done in FXML
        MFXIconWrapper randomizeIconWrapper = new MFXIconWrapper("fas-rotate", 20, 28);
        MFXButton randomizeButton = new MFXButton("", randomizeIconWrapper);
        randomizeButton.setId("default-button");
        controller.getRandomizeButtonContainer().getChildren().add(randomizeButton);

        // Add sorting algorithms to the selector
        controller.getAlgorithmSelector().getItems().addAll(sortingModel.getSortingAlgorithms());

        // Setup stage
        Scene scene = new Scene(root);
        // Set theme for the scene
        MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);
        scene.setFill(Color.TRANSPARENT);
        // Set the scene to the stage
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Algorithm Visualizer");

        // Create SortingWindow view
        SortingWindow sortingWindow = new SortingWindow(stage);

        // Show the stage
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
