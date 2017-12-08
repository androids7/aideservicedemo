package com.example.server;

import com.example.server.IPerson;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service{
	private IPerson.Stub iPerson = new Person();
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return iPerson;
	}
}
