package com.example.gitdemo2;

import java.util.ArrayList;
import java.util.List;

import com.example.gitdemo2.model.TrueFalse;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private TextView tv_question;
	private Button mBtn_true;
	private Button mBtn_false;
	private Button mBtn_preview;
	private Button mBtn_next;
	private List<TrueFalse> questions;
	TrueFalse tf = null;
	private int index;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MyCrashHandler crashHandler = MyCrashHandler.getInstance();
		crashHandler.init();
		initData();
		initView();
		Log.i("ii", "savedInstanceState="+savedInstanceState);
		if(savedInstanceState != null){
			index = (Integer) savedInstanceState.get("current_index");
			tf = questions.get(index);
			tv_question.setText(tf.getQuestion());
		}
	}

	private void initData() {
		questions = new ArrayList<TrueFalse>();
		tf = new TrueFalse(R.string.question,true);
		questions.add(tf);
		tf = new TrueFalse(R.string.question1,true);
		questions.add(tf);
		tf = new TrueFalse(R.string.question2,true);
		questions.add(tf);
		tf = new TrueFalse(R.string.question3,true);
		questions.add(tf);
	}

	private void initView() {
		tv_question = (TextView) findViewById(R.id.tv_questtion);
		mBtn_true = (Button) findViewById(R.id.btn_true);
		mBtn_false = (Button) findViewById(R.id.btn_false);
		mBtn_preview = (Button) findViewById(R.id.btn_preview);
		mBtn_next = (Button) findViewById(R.id.btn_next);
		mBtn_true.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showToast(R.string.toast_correct);
			}
		});
		
		mBtn_false.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showToast(R.string.toast_incorrect);
			}
		});
		
		mBtn_preview.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(index>=1){
					index--;
					tf = questions.get(index);
					if(tf!=null){
						tv_question.setText(tf.getQuestion());
					}
				}
				
			}
		});
		
		mBtn_next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				updataQuestion();
				
			}

		});
		
		tv_question.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				updataQuestion();
			}
		});
	}
	
	private void updataQuestion() {
		if(index<questions.size()){
			index++;
			if(index==questions.size()){
				index--;
				return;
			}
			tf = questions.get(index);
			if(tf!=null){
				tv_question.setText(tf.getQuestion());
			}
		}
	}
	
	private void showToast(int toastCorrect) {
		Toast.makeText(this, toastCorrect, Toast.LENGTH_SHORT).show();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		Log.i("ii", "savedInstanceState=");
		outState.putInt("current_index", index);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
