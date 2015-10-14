package com.example.gitdemo2;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;

import android.util.Log;

public class MyCrashHandler implements UncaughtExceptionHandler{
	
	private static final String TAG = "CH";
	private static MyCrashHandler instance;
	private UncaughtExceptionHandler mDefaultExceptionHandler;

	private MyCrashHandler(){
		
	}
	
	public static MyCrashHandler getInstance(){
		if(null==instance){
			instance = new MyCrashHandler();
		}
		return instance;
	}
	
	public void init(){
		mDefaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	@Override
	public void uncaughtException(Thread thread, Throwable throwable) {
		if(!handleException(throwable) && mDefaultExceptionHandler != null){
			mDefaultExceptionHandler.uncaughtException(thread, throwable);
		}else{
			Writer mWriter = new StringWriter();
			PrintWriter pw = new PrintWriter(mWriter);
			throwable.printStackTrace(pw);//少了这句代码，打印的堆栈信息就会少一些
			Throwable cause = throwable.getCause();
			while(null != cause){
				cause.printStackTrace(pw);
				pw.print("\r\n");
				cause = cause.getCause();
			}
			pw.flush();
			pw.close();
			String mResult = mWriter.toString();
			Log.e(TAG, "mResult="+mResult);
			android.os.Process.killProcess(android.os.Process.myPid());  
		}
	}

	private boolean handleException(Throwable ex) {
		if(ex != null){
			return true;
		}
		return false;
	}

}
