package application;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MP3Converter
{	
	//sourceBytes represents the mp3 file
	public static byte[] getAudioDataBytes(byte[] sourceBytes, AudioFormat audioFormat)
			throws UnsupportedAudioFileException, IllegalArgumentException, Exception {
		if (sourceBytes == null || sourceBytes.length == 0 || audioFormat == null)
		{
			throw new IllegalArgumentException("Illegal Argument passed to getAudioDataBytes");
		}

		ByteArrayInputStream inStream = null;
		ByteArrayOutputStream outStream = null;
		AudioInputStream sourceIn = null;
		AudioInputStream convertIn = null;
		AudioInputStream convertIn2 = null;

		try
		{
			inStream = new ByteArrayInputStream(sourceBytes);
			sourceIn = AudioSystem.getAudioInputStream(inStream);
			AudioFormat sourceFormat = sourceIn.getFormat();
			AudioFormat convertFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, sourceFormat.getSampleRate(),
					16, sourceFormat.getChannels(), sourceFormat.getChannels() * 2, sourceFormat.getSampleRate(),
					false);
			convertIn = AudioSystem.getAudioInputStream(convertFormat, sourceIn);
			convertIn2 = AudioSystem.getAudioInputStream(audioFormat, convertIn);
			
			outStream = new ByteArrayOutputStream();
			
			byte[] buffer = new byte[8192];
			while(true)
			{
				int readCount = convertIn2.read(buffer, 0, buffer.length);
				if(readCount==-1)
				{
					break;
				}
				outStream.write(buffer, 0, readCount);			
			}
			return outStream.toByteArray();
		} catch (UnsupportedAudioFileException e)
		{
			e.printStackTrace();
			throw e;
		} catch (IOException e)
		{
			e.printStackTrace();
			throw e;
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		finally{
			if(outStream != null){
                try{
                    outStream.close();
                }catch(Exception e){
                }
            }
            if(convertIn2 != null){
                try{
                    convertIn2.close();
                }catch(Exception e){
                }
            }
            if(convertIn != null){
                try{
                    convertIn.close();
                }catch(Exception e){
                }
            }
            if(sourceIn != null){
                try{
                    sourceIn.close();
                }catch(Exception e){
                }
            }
            if(inStream != null){
                try{
                    inStream.close();
                }catch(Exception e){
                }
            }
		}
	}
}
