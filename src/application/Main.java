package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/*
 * Main class to run the application
 */

public class Main extends Application
{
	@Override
	public void start(
			Stage primaryStage)
	{
		try
		{
			AnchorPane page = (AnchorPane) FXMLLoader.load(Main.class.getResource("Pulser.fxml"));
			Scene scene = new Scene(page);
			scene.getStylesheets()
					.add(getClass()
							.getResource(
									"application.css")
							.toExternalForm());
			primaryStage
					.setScene(scene);
			primaryStage.show();
			primaryStage.getIcons().add(new Image("/images/SquaresAndBeats.png"));
			primaryStage.setTitle("Squares and Beats");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void stop()
	{
		System.exit(0);
	}
	
	public static void main(
			String[] args)
	{
		launch(args);
	}
}

