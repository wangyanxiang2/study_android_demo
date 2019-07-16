

package com.android.server;
import android.os.ILedService;

public class LedService extends ILedService.Stub {  
	
	private static final String TAG = "LedService";	/* call native c function to access hardware */	

	public int ledCtrl(int which, int status) throws android.os.RemoteException	
	{		
		return ledCtrl(which, status);	
	}
	
	public LedService() 
	{		
		ledOpen();	
	}	
	
	public static native int ledOpen();	
	public static native void ledClose();	
	public static native int ledCtrl(int which, int status);
}

