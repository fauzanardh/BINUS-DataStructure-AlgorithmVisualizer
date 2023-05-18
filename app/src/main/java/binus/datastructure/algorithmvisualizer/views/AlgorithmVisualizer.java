package binus.datastructure.algorithmvisualizer.views;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
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
import binus.datastructure.algorithmvisualizer.controllers.SortingWindowControllers;
import binus.datastructure.algorithmvisualizer.models.SortingModel;

public class AlgorithmVisualizer extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Initialize SortingModel randomly
        SortingModel sortingModel = new SortingModel();

        // Start CSSFX
        CSSFX.start();

        //
        // Main Window
        //
        // Load AlgorithmVisualizer
        FXMLLoader loaderMainWindow = new FXMLLoader(MFXAppResourcesLoader.loadURL("fxml/AlgorithmVisualizer.fxml"));
        loaderMainWindow.setControllerFactory(c -> new AlgorithmVisualizerControllers(stage, sortingModel));
        Parent rootMainWindow = loaderMainWindow.load();

        // Get the AlgorithmVisualizerControllers
        AlgorithmVisualizerControllers controllerMainWindow = loaderMainWindow.getController();

        // Create the randomize button with icon since it can't be done in FXML
        MFXIconWrapper randomizeIconWrapper = new MFXIconWrapper("fas-rotate", 20, 28);
        MFXButton randomizeButton = new MFXButton("", randomizeIconWrapper);
        randomizeButton.setId("default-button");
        controllerMainWindow.getRandomizeButtonContainer().getChildren().add(randomizeButton);

        // Set the randomize button handler
        controllerMainWindow.setRandomizeButtonHandler(randomizeButton);

        // Add sorting algorithms to the selector
        controllerMainWindow.getAlgorithmSelector().getItems().addAll(sortingModel.getSortingAlgorithms());
        controllerMainWindow.setDefaultValues();

        //
        // Sorting Window
        //
        // Set the AnchorPane with fx:id `contentPane`
        AnchorPane contentPane = controllerMainWindow.getContentPane();

        // Load the SortingWindow
        FXMLLoader loaderSortingWindow = new FXMLLoader(MFXAppResourcesLoader.loadURL("fxml/SortingWindow.fxml"));
        loaderSortingWindow.setControllerFactory(c -> new SortingWindowControllers(contentPane, sortingModel));
        Parent rootSortingWindow = loaderSortingWindow.load();

        // Get the SortingWindowControllers
        SortingWindowControllers controllerSortingWindow = loaderSortingWindow.getController();

        // Create the control buttons with icon since it can't be done in FXML
        // Backward button
        MFXIconWrapper backwardIconWrapper = new MFXIconWrapper("fas-caret-left", 28, 28);
        MFXButton backwardButton = new MFXButton("", backwardIconWrapper);
        backwardButton.setId("default-button");
        controllerSortingWindow.getStepBackwardContainer().getChildren().add(backwardButton);
        // Play button
        MFXIconWrapper playIconWrapper = new MFXIconWrapper("fas-play", 28, 28);
        MFXButton playButton = new MFXButton("", playIconWrapper);
        playButton.setId("default-button");
        controllerSortingWindow.getPlayContainer().getChildren().add(playButton);
        // Forward button
        MFXIconWrapper forwardIconWrapper = new MFXIconWrapper("fas-caret-right", 28, 28);
        MFXButton forwardButton = new MFXButton("", forwardIconWrapper);
        forwardButton.setId("default-button");
        controllerSortingWindow.getStepForwardContainer().getChildren().add(forwardButton);

        // Setup controllers for the control buttons
        controllerSortingWindow.setStepHandler(backwardButton, forwardButton);
        controllerSortingWindow.setPlayHandler(playButton);

        // Set the SortingWindow as the content of the AnchorPane
        contentPane.getChildren().setAll(rootSortingWindow);

        //
        // Setup that requires both controllers
        //
        controllerMainWindow.setRunVisualizerButtonHandler(controllerSortingWindow);

        //
        // Scene and Stage
        //
        // Setup stage
        Scene scene = new Scene(rootMainWindow);
        // Set theme for the scene
        MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);
        scene.setFill(Color.TRANSPARENT);
        // Set the scene to the stage
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Algorithm Visualizer");

        // Show the stage
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
