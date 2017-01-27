package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.media.AudioClip;
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


	/*
	 * The Audio file and all of its data to be used for this version only this
	 * song works
	 * URL resource: location of file
	 */
	AudioFile logicGate = new AudioFile("/music/logicGateKeeper.wav");
	AudioFile chronos = new AudioFile("/music/chronosWav.wav");
	AudioFile longLive = new AudioFile("/music/LongLiveTheNewFreshWav.wav");
	AudioFile mainFile = new AudioFile();
	private String song; //song to be used
	private URL resource; //url of song
	private File audioFi; //file name
	private AudioClip clip; //audio clip
	private int delay; //the delay necessary for the song and colors to match
	private int iterations; //the space of how many spaces in the array to be skipped and used
	private int space; //Space between when the lights will be shown
	private int file_bytes; //File Size
	private int byte_space; //how far to space out the bytes
	private boolean colorSwitch; //needed for rainbow mode

	Paint black = Paint.valueOf("black"); //Color needed to repaint squares
	private ArrayList<Rectangle> rects = new ArrayList<Rectangle>(
			16); //arraylist of the rectangles used

	  //audio clip to be used to play
	Timer timee = new Timer(); //a timer to do the light show!

	//Method to stop sound from continuing after application closes
	@FXML
	public void exitApplication(ActionEvent event)
	{
		getClip().stop();
		Platform.exit();
	}
	
	@FXML
	public void initialize()
	{
		setDelay(chronos.getDelay());
		setSong(chronos.getSong());
		setResource(chronos.getResource());
		setAudioFi(chronos.getFile());
		setClip(chronos.getClip());
		setFile_bytes(chronos.getFile_bytes());
		setSpace(chronos.getSpace());
		setIterations(chronos.getIterations());
		setByte_space(chronos.getByte_space());
		
				
	}	
	
	private void data()
	{
		if (longLiveBtn.isSelected())
		{
			setDelay(longLive.getDelay()+600);
			setSong(longLive.getSong());
			setResource(longLive.getResource());
			setAudioFi(longLive.getFile());
			setClip(longLive.getClip());
			setFile_bytes(longLive.getFile_bytes());
			setSpace(longLive.getSpace());
			setIterations(longLive.getIterations());
			setByte_space(longLive.getByte_space());
		}
		if(chronosBtn.isSelected())
		{
			setDelay(chronos.getDelay());
			setSong(chronos.getSong());
			setResource(chronos.getResource());
			setAudioFi(chronos.getFile());
			setClip(chronos.getClip());
			setFile_bytes(chronos.getFile_bytes());
			setSpace(chronos.getSpace());
			setIterations(chronos.getIterations());
			setByte_space(chronos.getByte_space());
		}
		if(logicGateSong.isSelected())
		{
			setDelay(logicGate.getDelay()+600);
			setSong(logicGate.getSong());
			setResource(logicGate.getResource());
			setAudioFi(logicGate.getFile());
			setClip(logicGate.getClip());
			setFile_bytes(logicGate.getFile_bytes());
			setSpace(logicGate.getSpace());
			setIterations(logicGate.getIterations());
			setByte_space(logicGate.getByte_space());
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
		data();
		Paint currentPaint = Paint
				.valueOf(paintColor());
		btn1.setDisable(true);
		btn2.setDisable(false);
		getClip().play();
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
				if (count >= getIterations())
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
		timee.schedule(task, (long) getDelay(),
				(long) getSpace());
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
		getClip().stop();
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
		byte[] byteArray = new byte[getFile_bytes()];
		FileInputStream fileInStream = null;
		try
		{
			fileInStream = new FileInputStream(
					getAudioFi());
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
		int value[] = new int[getIterations() + 1];
		for (int i = 0; i < byteArray.length; i = i
				+ byte_space)
		{
			value[count] = (byteArray[i] + 128)
					/ 16;
			if (count != 0)
			{
				value[count] = Math.abs(
						value[count] - value[count
								- 1]);
			}
			System.out.println(count + ": "
					+ (byteArray[i] + 128) / 16);
			count++;
		}
		return value;
	}
	
	public int getIterations()
	{
		return iterations;
	}


	public void setIterations(int iterations)
	{
		this.iterations = iterations;
	}


	public int getSpace()
	{
		return space;
	}


	public void setSpace(int space)
	{
		this.space = space;
	}


	public int getFile_bytes()
	{
		return file_bytes;
	}


	public void setFile_bytes(int file_bytes)
	{
		this.file_bytes = file_bytes;
	}


	public int getByte_space()
	{
		return byte_space;
	}


	public void setByte_space(int byte_space)
	{
		this.byte_space = byte_space;
	}
	
	public String getSong()
	{
		return song;
	}


	public void setSong(String song)
	{
		this.song = song;
	}

	public URL getResource()
	{
		return resource;
	}

	public void setResource(URL resource)
	{
		this.resource = resource;
	}

	public File getAudioFi()
	{
		return audioFi;
	}

	public void setAudioFi(File chronos)
	{
		this.audioFi = chronos;
	}

	public AudioClip getClip()
	{
		return clip;
	}

	public void setClip(AudioClip clip)
	{
		this.clip = clip;
	}

	public int getDelay()
	{
		return delay;
	}

	public void setDelay(int delay)
	{
		this.delay = delay;
	}
	
	public boolean isColorSwitch() {
		return colorSwitch;
	}

	public void setColorSwitch(boolean colorSwitch) {
		this.colorSwitch = colorSwitch;
	}

}
