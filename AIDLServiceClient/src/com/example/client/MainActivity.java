package com.example.client;

import com.example.server.IPerson;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button btn;
	private IPerson person;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn = (Button)findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction("com.example.server.MyService");
				bindService(intent, conn, Service.BIND_AUTO_CREATE);
			}
		});
	}
	
	private ServiceConnection conn = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub		
		}
		
		@Override
		public synchronized void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			person = IPerson.Stub.asInterface(service);
			if(person != null){
				try {
					String name1 = person.getValue();
					Toast.makeText(MainActivity.this, "远程调用成功，值为："+name1, Toast.LENGTH_LONG).show();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(MainActivity.this, "远程调用失败", Toast.LENGTH_LONG).show();
				}
			}
		}
	};

}
