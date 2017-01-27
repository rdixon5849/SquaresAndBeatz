package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/*
 * The controller Class that allows for the controller to communicate with the FXML document
 */

public class Controller
{
	// Definitions of FXML Objects
	@FXML
	Button btn1;
	@FXML
	Button btn2;
	@FXML
	Rectangle b1;
	@FXML
	Rectangle b2;
	@FXML
	Rectangle b3;
	@FXML
	Rectangle b4;
	@FXML
	Rectangle b5;
	@FXML
	Rectangle b6;
	@FXML
	Rectangle b7;
	@FXML
	Rectangle b8;
	@FXML
	Rectangle b9;
	@FXML
	Rectangle b10;
	@FXML
	Rectangle b11;
	@FXML
	Rectangle b12;
	@FXML
	Rectangle b13;
	@FXML
	Rectangle b14;
	@FXML
	Rectangle b15;
	@FXML
	Rectangle b16;
	@FXML
	RadioMenuItem redColor;
	@FXML
	RadioMenuItem blueColor;
	@FXML
	RadioMenuItem yellowColor;
	@FXML
	RadioMenuItem greenColor;
	@FXML
	RadioMenuItem pinkColor;
	@FXML
	RadioMenuItem rainbowColor;
	@FXML
	RadioMenuItem chronosBtn;
	@FXML
	RadioMenuItem longLiveBtn;
	@FXML
	RadioMenuItem logicGateSong;
	@FXML
	RadioMenuItem destBtn;
	@FXML
	RadioMenuItem intoWav;

	/*
	 * The Audio file and all of its data to be used for this version only this
	 * song works
	 * URL resource: location of file
	 */
	AudioFile logicGate = new AudioFile("/music/logicGateKeeper.wav");
	AudioFile chronos = new AudioFile("/music/chronosWav.wav");
	AudioFile longLive = new AudioFile("/music/LongLiveTheNewFreshWav.wav");
	AudioFile dest = new AudioFile("/music/DestatiFragments.wav");
	AudioFile intoTheNight = new AudioFile("/music/intoTheNight.wav", 0, 200);
	AudioFile mainFile = new AudioFile();
	private boolean colorSwitch;
	
	Paint black = Paint.valueOf("black"); //Color needed to repaint squares
	private ArrayList<Rectangle> rects = new ArrayList<Rectangle>(
			16); //arraylist of the rectangles used

	  //audio clip to be used to play
	Timer timee = new Timer(); //a timer to do the light show!

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
		mainFile=new AudioFile(chronos);				
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
		else if(pinkColor.isSelected())
			color = "pink";
		else if(rainbowColor.isSelected())
		{
			setColorSwitch(true);
		}
		else
			color = "yellow";
		return color;
	}

	//Method that runs the code and makes the lights light up
	public void makeTime(ActionEvent event)
	{
		//data();
		mainFile = new AudioFile(getSelectedFile());
		Paint currentPaint = Paint
				.valueOf(paintColor());
		btn1.setDisable(true);
		btn2.setDisable(false);
		mainFile.getClip().play();
		rects = fillArray();
		// int pulseVals[] = pulseVal();
		int pulseVals[] = pulseValSub();
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
		if(logicGateSong.isSelected())
		{
			logicGate.setDelay(1200);
			return  logicGate;
		}
		else if(destBtn.isSelected())
		{
			dest.setSpace(200);;
			return dest;
		}
		else if(longLiveBtn.isSelected())
		{
			longLive.setDelay(1200);
			return longLive;
		}
		else if(intoWav.isSelected())
		{
			return intoTheNight;
		}
		else
			return chronos;
	}

	//Method to interrupt the timer and sound if you want to.
	public void interrupt(ActionEvent event)
	{
		timee.cancel();
		for (Rectangle rect : rects)
		{
			if (rect.getFill() != black)
			{
				rect.setFill(black);
			}
		}
		mainFile.getClip().stop();
		btn1.setDisable(false);
		btn2.setDisable(true);
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

	// Like pulseVal() method but instead finds the difference between values of
	// pulses and displays them.
	private int[] pulseValSub()
	{
		byte[] byteArray = new byte[mainFile.getFile_bytes()];
		FileInputStream fileInStream = null;
		try
		{
			fileInStream = new FileInputStream(
					mainFile.getFile());
		} catch (FileNotFoundException e1)
		{
			e1.printStackTrace();
		}
		try
		{
			fileInStream.read(byteArray);
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		int count = 0;
		int value[] = new int[mainFile.getIterations() + 1];
		for (int i = 0; i < byteArray.length; i = i
				+ mainFile.getByte_space())
		{
			value[count] = (byteArray[i] + 128)
					/ 16;
			if (count != 0)
			{
				value[count] = Math.abs(
						value[count] - value[count
								- 1]);
			}
			//System.out.println(count + ": "
				//	+ (byteArray[i] + 128) / 16);
			count++;
		}
		return value;
	}
	
	
	public boolean isColorSwitch() {
		return colorSwitch;
	}

	public void setColorSwitch(boolean colorSwitch) {
		this.colorSwitch = colorSwitch;
	}

}
