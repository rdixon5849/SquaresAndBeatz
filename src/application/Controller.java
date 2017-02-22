package application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/*
 * The controller Class that allows for the controller to communicate with the FXML document
 */

public class Controller
{
	// Definitions of FXML Objects
	@FXML   Button playBtn;
	@FXML	Button endBtn;
	@FXML	Rectangle b1;
	@FXML	Rectangle b2;
	@FXML	Rectangle b3;
	@FXML	Rectangle b4;
	@FXML	Rectangle b5;
	@FXML	Rectangle b6;
	@FXML	Rectangle b7;
	@FXML	Rectangle b8;
	@FXML	Rectangle b9;
	@FXML	Rectangle b10;
	@FXML	Rectangle b11;
	@FXML	Rectangle b12;
	@FXML	Rectangle b13;
	@FXML	Rectangle b14;
	@FXML	Rectangle b15;
	@FXML	Rectangle b16;
	@FXML	RadioMenuItem redColor;
	@FXML	RadioMenuItem blueColor;
	@FXML	RadioMenuItem yellowColor;
	@FXML	RadioMenuItem greenColor;
	@FXML	RadioMenuItem rainbowColor;
	@FXML	RadioMenuItem song1;
	@FXML	RadioMenuItem song2;
	@FXML	RadioMenuItem song3;
	@FXML	RadioMenuItem song4;
	@FXML	RadioMenuItem song5;
	@FXML	MenuItem addFile;
	@FXML	RadioMenuItem diffBtn;
	@FXML	RadioMenuItem pcmBtn;
	@FXML	Label nameText;

	Timer timee = new Timer(); //a timer to do the light show!
	Stage stage= new Stage();	
	List<RadioMenuItem> songs = new ArrayList<RadioMenuItem>(5);
	List<AudioFile> afList = new ArrayList<AudioFile>(5);
	
	/*
	 * The Audio file and all of its data to be used for this version only this
	 * song works
	 * URL resource: location of file
	 */
	AudioFile aFile1 = new AudioFile();
	AudioFile aFile2 = new AudioFile();
	AudioFile aFile3 = new AudioFile();		
	AudioFile aFile4 = new AudioFile();
	AudioFile aFile5 = new AudioFile();		
	
	AudioFile mainFile = new AudioFile();
	
	FileChooser fileChooser = new FileChooser();
	private boolean colorSwitch;
	
	Paint black = Paint.valueOf("black"); //Color needed to repaint squares
	private ArrayList<Rectangle> rects = new ArrayList<Rectangle>(
			16); //arraylist of the rectangles used

	//Method to stop sound from continuing after application closes
	@FXML
	public void exitApplication(ActionEvent event)
	{
		mainFile.getClip().stop();
		Platform.exit();
	}
	
	@FXML
	public void initialize()
	{
		fillLists();
		mainFile=new AudioFile(aFile1);	
	}	
	
	private void fillLists()
	{
		afList.add(aFile1);
		afList.add(aFile2);
		afList.add(aFile3);
		afList.add(aFile4);
		afList.add(aFile5);
		songs.add(song1);
		songs.add(song2);
		songs.add(song3);
		songs.add(song4);
		songs.add(song5);
		for(int i =0; i < afList.size(); i++)
		{
			songs.get(i).setText(afList.get(i).getName());
			if(afList.get(i).getFile()==null)
			{
				songs.get(i).setText("<empty>");
			}
		}
	}
	
	//Method to be able to add a song to the list.
	public void addSong(ActionEvent event)
	{
		interrupt(event);
		File file = fileChooser.showOpenDialog(stage);
		if(file!=null)
		{
			openFile(file);
		}		
		fillLists();
	}
	
	private void openFile(File file)
	{
		nameText.setText("");
		for(int i =0; i < afList.size(); i++)
		{
			if(songs.get(i).isSelected())
			{
			String f = file.getAbsolutePath();
			if(!file.getAbsolutePath().endsWith("wav"))
			{
				nameText.setText("Wrong File Type .wav only!");
				break;
			}
			String p=f.replace('\\', '/');
			AudioFile inFile = new AudioFile(p, file);	
			afList.get(i).setByte_space(inFile.getByte_space());
			afList.get(i).setClip(inFile.getClip());
			afList.get(i).setDelay(inFile.getDelay());
			afList.get(i).setFile(inFile.getFile());
			afList.get(i).setFile_bytes(inFile.getFile_bytes());
			afList.get(i).setIterations(inFile.getIterations());
			afList.get(i).setName(inFile.getName());
			afList.get(i).setResource(inFile.getResource());
			afList.get(i).setSong(inFile.getSong());
			afList.get(i).setSpace(inFile.getSpace());		
			afList.set(i, inFile);
			}
		}
	}
	
	//Method to determine the color of the squares
	private String paintColor()
	{
		String color = "yellow";
		if (redColor.isSelected())
			color = "red";
		else if (blueColor.isSelected())
			color = "blue";
		else if(greenColor.isSelected())
			color = "green";
		else if(rainbowColor.isSelected())
		{
			setColorSwitch(true);
		}
		else
			color="yellow";
		return color;
	}

	//Method that runs the code and makes the lights light up
	public void makeTime(ActionEvent event)
	{
		mainFile = new AudioFile(getSelectedFile());
		if(mainFile.getFile()==null)
		{
			nameText.setText("Song value is empty.");
			return;
		}
		nameText.setText(mainFile.getName());
		Paint currentPaint = Paint
				.valueOf(paintColor());
		playBtn.setDisable(true);
		endBtn.setDisable(false);
		mainFile.getClip().play();
		rects = fillArray();
		int pulseVals[] = PulseModes.pulseValSub(mainFile, diffBtn);
		Paint[] currentPaints = {Paint.valueOf("red"), Paint.valueOf("orange"), Paint.valueOf("yellow"), Paint.valueOf("green"), Paint.valueOf("blue"), Paint.valueOf("purple")};
		TimerTask task = new TimerTask()
		{
			int count = 0;
			
			public void run()
			{
				for (Rectangle rect : rects)
				{
					if (rect.getFill() != black)
					{
						rect.setFill(black);
					}
				}
				if(!isColorSwitch())
				{
				rects.get(pulseVals[count])
						.setFill(currentPaint);
				}
				else
				{
					rects.get(pulseVals[count]).setFill(currentPaints[count%6]);
				}
				
				count++;
				if (count >= mainFile.getIterations())
				{
					this.cancel();
					for (Rectangle rect : rects)
					{
						rect.setFill(black);
					}
				}
			}

		};
		timee = new Timer();
		timee.schedule(task, (long) mainFile.getDelay(),
				(long) mainFile.getSpace());
	}

	//Method that knows what file to play
	private AudioFile getSelectedFile() 
	{
		if(song3.isSelected())
		{
			return  aFile3;
		}
		else if(song4.isSelected())
		{
			return aFile4;
		}
		else if(song2.isSelected())
		{
			return aFile2;
		}
		else if(song5.isSelected())
		{
			return aFile5;
		}
		else
			return aFile1;
	}

	//Method to interrupt the timer and sound if you want to.
	public void interrupt(ActionEvent event)
	{
		if(mainFile.getFile()==null)
		{
			return;
		}
		timee.cancel();
		for (Rectangle rect : rects)
		{
			if (rect.getFill() != black)
			{
				rect.setFill(black);
			}
		}
		mainFile.getClip().stop();
		playBtn.setDisable(false);
		endBtn.setDisable(true);
	}

	//simple close for File -> Close button
	public void onClose(ActionEvent event)
	{
		System.exit(0);
	}

	//Way to fill the array with the rectangles
	private ArrayList<Rectangle> fillArray()
	{
		rects.add(b1);
		rects.add(b2);
		rects.add(b3);
		rects.add(b4);
		rects.add(b5);
		rects.add(b6);
		rects.add(b7);
		rects.add(b8);
		rects.add(b9);
		rects.add(b10);
		rects.add(b11);
		rects.add(b12);
		rects.add(b13);
		rects.add(b14);
		rects.add(b15);
		rects.add(b16);
		return rects;
	}	
	
	public boolean isColorSwitch() {
		return colorSwitch;
	}

	public void setColorSwitch(boolean colorSwitch) {
		this.colorSwitch = colorSwitch;
	}

}
