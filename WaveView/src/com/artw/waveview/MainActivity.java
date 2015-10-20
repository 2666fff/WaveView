package com.artw.waveview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.artw.wavetest.R;

public class MainActivity extends FragmentActivity {
	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.fragment_main);
		findViewById(R.id.button1).setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View paramView) {
						startActivity(new Intent(getApplicationContext(),
								AcIWV.class));
					}
				});
		findViewById(R.id.button2).setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View paramView) {
						startActivity(new Intent(getApplicationContext(),
								AcBWV.class));
					}
				});
	}
}