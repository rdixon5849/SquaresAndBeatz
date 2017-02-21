package application;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.scene.media.AudioClip;

public class AudioFile
{
	private String song; //song to be used
	private String name; //name of the song
	private URL resource; //url of song
	private File file; //file name
	private AudioClip clip; //audio clip
	private int delay; //the delay necessary for the song and colors to match
	private int iterations; //the space of how many spaces in the array to be skipped and used
	private int space; //Space between when the lights will be shown
	private int file_bytes; //File Size
	private int byte_space; //how far to space out the bytes
	
	public AudioFile()
	{
		super();
	}
	
	public AudioFile(String path)
	{
		setSong(path);
		setName(path.substring(7));
		setResource(getClass().getResource(getSong()));
		setFile((new File(this.resource.getPath())));
		setClip(new AudioClip(this.resource.toString()));
		AudioInputStream aInStream = null;
		try {
			aInStream = AudioSystem.getAudioInputStream(this.file);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		AudioFormat format = aInStream.getFormat();
		long frames = aInStream.getFrameLength();
		double secDuration = (frames+0.0)/format.getFrameRate();
		setDelay(600);
		setSpace(150);
		setIterations((int)secDuration*1000/getSpace());
		setFile_bytes((int)getFile().length());
		setByte_space(getFile_bytes()/getIterations());
	}
	
	public AudioFile(String path, File file)
	{
		setSong(path);
		setName(file.getName());
		try
		{
			setResource(file.toURI().toURL());
		} catch (MalformedURLException e1)
		{
			e1.printStackTrace();
		}
		setFile(file);
		setClip(new AudioClip(getResource().toString()));
		AudioInputStream aInStream = null;
		try {
			aInStream = AudioSystem.getAudioInputStream(getFile());
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		AudioFormat format = aInStream.getFormat();
		long frames = aInStream.getFrameLength();
		double secDuration = (frames+0.0)/format.getFrameRate();
		setDelay(0);
		setSpace(150);
		setIterations((int)secDuration*1000/getSpace());
		setFile_bytes((int)getFile().length());
		setByte_space(getFile_bytes()/getIterations());
	}
	
	public AudioFile(String path, String name)
	{
		setSong(path);
		setName(name);
		setResource(getClass().getResource(getSong()));
		setFile((new File(this.resource.getPath())));
		setClip(new AudioClip(this.resource.toString()));
		AudioInputStream aInStream = null;
		try {
			aInStream = AudioSystem.getAudioInputStream(this.file);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		AudioFormat format = aInStream.getFormat();
		long frames = aInStream.getFrameLength();
		double secDuration = (frames+0.0)/format.getFrameRate();
		setDelay(600);
		setSpace(150);
		setIterations((int)secDuration*1000/getSpace());
		setFile_bytes((int)getFile().length());
		setByte_space(getFile_bytes()/getIterations());
	}
	
	public AudioFile(String path, int delay, int space)
	{
		setSong(path);
		setName(path.substring(7));
		setResource(getClass().getResource(getSong()));
		setFile((new File(this.resource.getPath())));
		setClip(new AudioClip(this.resource.toString()));
		AudioInputStream aInStream = null;
		try {
			aInStream = AudioSystem.getAudioInputStream(this.file);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		AudioFormat format = aInStream.getFormat();
		long frames = aInStream.getFrameLength();
		double secDuration = (frames+0.0)/format.getFrameRate();
		setDelay(delay);
		setSpace(space);
		setIterations((int)secDuration*1000/getSpace());
		setFile_bytes((int)getFile().length());
		setByte_space(getFile_bytes()/getIterations());
	}
	
	public AudioFile(AudioFile other)
	{
		setSong(other.getSong());
		setName(other.getName());
		setResource(other.getResource());
		setFile(other.getFile());
		setClip(other.getClip());
		setDelay(other.getDelay());
		setIterations(other.getIterations());
		setSpace(other.getSpace());
		setFile_bytes(other.getFile_bytes());
		setByte_space(other.getByte_space());
	}

	

	public int getSpace() {
		return space;
	}

	public void setSpace(int space) {
		this.space = space;
	}

	public URL getResource() {
		return resource;
	}

	public void setResource(URL resource) {
		this.resource = resource;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public AudioClip getClip() {
		return clip;
	}

	public void setClip(AudioClip clip) {
		this.clip = clip;
	}

	public int getIterations() {
		return iterations;
	}

	public void setIterations(int iterations) {
		this.iterations = iterations;
	}

	public int getFile_bytes() {
		return file_bytes;
	}

	public void setFile_bytes(int file_bytes) {
		this.file_bytes = file_bytes;
	}

	public int getByte_space() {
		return byte_space;
	}

	public void setByte_space(int byte_space) {
		this.byte_space = byte_space;
	}
	
	public String getSong() {
		return song;
	}

	public void setSong(String song) {
		this.song = song;
	}
	
	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
