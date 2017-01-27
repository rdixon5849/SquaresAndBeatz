package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.scene.media.AudioClip;

public class AudioFile
{
	private String song; //song to be used
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
		setIterations((int)secDuration*1000/this.space);
		setFile_bytes((int)this.file.length());
		setByte_space(this.file_bytes/this.iterations);
	}
	
	public AudioFile(AudioFile other)
	{
		setSong(other.getSong());
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
}
