package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.scene.control.RadioMenuItem;


public class PulseModes
{
	public static int[] pulseValSub(AudioFile mainFile, RadioMenuItem diffBtn)
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

		int value[] = new int[mainFile.getIterations() + 1];

		if (diffBtn.isSelected())
		{
			value = diffVal(value, byteArray, mainFile);
		} else
		{
			value = pcmVal(value, byteArray, mainFile);
}
		
		/*int count = 0;
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
		}*/
		return value;
	}
	
	private static int[] diffVal(int[] value, byte[] byteArray, AudioFile mainFile)
	{
		int count = 0;
		for (int i = 0; i < byteArray.length; i = i + mainFile.getByte_space())
		{
			// storing the values to be displayed
			value[count] = (byteArray[i] + 128) / 16;
			if (count != 0)
			{
				value[count] = Math.abs(value[count] - value[count - 1]);
			}
			// System.out.println(count + ": "
			// + (byteArray[i] + 128) / 16);
			count++;
		}
		return value;
}
	
	private static int[] pcmVal(int[] value, byte[] byteArray, AudioFile mainFile)
	{
		int count = 0;
		for (int i = 0; i < byteArray.length; i = i + mainFile.getByte_space())
		{
			// storing the values to be displayed
			value[count] = (byteArray[i] + 128) / 16;
			count++;
		}
		return value;
}
}
