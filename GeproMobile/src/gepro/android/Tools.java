package gepro.android;

import java.security.acl.LastOwnerException;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.provider.MediaStore;

public class Tools {

	private final static String MESSAGE_ERROR = "MESSAGE_ERROR";

	public static Message CreateMessageFomEx(Exception ex) {
		Bundle data = new Bundle();
		data.putString(MESSAGE_ERROR, ex.getMessage());
		Message message = new Message();
		message.setData(data);
		return message;
	}
	
	public static String FomatDate(int year, int month , int day )
	{
		return "01-01-2010";
		
	}

	public static String GetMessageExeption(Message message) {
		Bundle data = message.getData();
		return data.getString(MESSAGE_ERROR);
	}

	public class EmptyListener implements DialogInterface.OnClickListener {

		@Override
		public void onClick(DialogInterface dialog, int which) {
		}
	}

	public static String getMIMEtype(String filename) {
		String ext = filename.substring(filename.lastIndexOf("."));
		if (ext == "pdf")
			return "application/pdf";
		else if (ext == "jpg" || ext == "jpge" || ext == "jpe")
			return  MediaStore.Images.Media.CONTENT_TYPE;
		else
			return "";

	}
	
	public static String padRight(String s, int n) {
	     return String.format("%1$-" + n + "0", new Object[]{  s });  
	}

	public static String padLeft(String s, int n) {
	    return String.format("%1$#" + n + "0", new Object[]{  s } );  
	}
}
