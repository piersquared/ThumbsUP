package edu.uml.thumbsup;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
//import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
//import android.widget.TextView;
//import android.widget.Toast;
import android.widget.Toast;


public class playGame extends Activity {

	private RadioButton rButtons[] = new RadioButton[12];
	
	//TODO
	TextView[] nxttv = new TextView[5];
	OnClickListener listener1 = null;
	Button button1;
	
	//DATABASE IMPLEMENTATION
	DatabaseHelper mOpenHelper;
    
	private static final String DATABASE_NAME = "inventions.db";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NAME = "inventions";
	private static final String ITEM = "item";
	private static final String INVENTOR = "inventor";

	private static class DatabaseHelper extends SQLiteOpenHelper {
		  
	    DatabaseHelper(Context context) {
	      super(context, DATABASE_NAME, null, DATABASE_VERSION);
	    }

	    @Override
	    public void onCreate(SQLiteDatabase db) {

	      String sql = "CREATE TABLE " + TABLE_NAME + " (" + ITEM
	          + " text not null, " + INVENTOR + " text not null " + ");";
	      Log.i("haiyang:createDB=", sql);
	      db.execSQL(sql);

	    }

	    @Override
	    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    }
	}
    //DATABASE CLASS ENDS HERE
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		prepareListener();
	    initLayout();
		mOpenHelper = new DatabaseHelper(this);
		
		for(int m=0;m<5;m++)
	    {
	  	  nxttv[m] = new TextView(this);
	    }
	    nxttv[0]=(TextView)findViewById(R.id.textView1);
	    nxttv[1]=(TextView)findViewById(R.id.textView2);
	    nxttv[2]=(TextView)findViewById(R.id.textView3);
	    nxttv[3]=(TextView)findViewById(R.id.textView4);
	    nxttv[4]=(TextView)findViewById(R.id.textView5);
		
		
		rButtons[1] = (RadioButton)findViewById(R.id.option1);
		rButtons[2] = (RadioButton)findViewById(R.id.option2);
		rButtons[3] = (RadioButton)findViewById(R.id.option3);
		rButtons[4] = (RadioButton)findViewById(R.id.option4);
		rButtons[5] = (RadioButton)findViewById(R.id.option5);
		rButtons[6] = (RadioButton)findViewById(R.id.option6);
		rButtons[7] = (RadioButton)findViewById(R.id.option7);
		rButtons[8] = (RadioButton)findViewById(R.id.option8);
		rButtons[9] = (RadioButton)findViewById(R.id.option9);
		rButtons[10] = (RadioButton)findViewById(R.id.option10);
		
				
		Button ansButton = (Button)findViewById(R.id.selected);

		ansButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				int count = checkStates();		
				int ans = scores(count);	
				
		  //SINGLE LINE TOAST MESSAGE
				//Toast.makeText(getApplicationContext(),"Congratulations! Your Score is: "+ans+"/100" ,Toast.LENGTH_LONG).show();
		 
		 //MULTIPLE LINE TOAST MESSAGE
				Context context = getApplicationContext();
				CharSequence text = "Congratulations! Your Score is: "+ans+"/100";
				int duration = Toast.LENGTH_SHORT;
				Toast toast = Toast.makeText(context, text, duration);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.show();		        
				finish();
			}
			
			
			public int checkStates(){
				int val = 0;	 
				if(rButtons[2].isChecked() == true){
			    	val++;
				}
				if(rButtons[3].isChecked() == true){
			    	val++;
				}
				if(rButtons[6].isChecked() == true){
			    	val++;
				}
				if(rButtons[8].isChecked() == true){
			    	val++;
				}
				if(rButtons[9].isChecked() == true){
			    	val++;
				}
							
				return val;
			}
			
			
			public int scores(int count){
				int score = count * 20;
				return score;
			}
		});

	}
	
	private void initLayout() {
	    button1 = (Button) findViewById(R.id.button1);
	    button1.setOnClickListener(listener1);
	  }

	  private void prepareListener() {
		  listener1 = new OnClickListener() {
		    public void onClick(View v) {
		     dropTable();
	         CreateTable();
	         insertItem();
	         showItems();
	         button1.setVisibility(View.GONE);
	        }
	     };
	  } 
	
	 private void CreateTable() {
		    SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		    String sql = "CREATE TABLE " + TABLE_NAME + " (" + ITEM
		        + " text not null, " + INVENTOR + " text not null " + ");";
		    Log.i("createDB=", sql);

		    try {
		      db.execSQL("DROP TABLE IF EXISTS diary");
		      db.execSQL(sql);
		      setTitle("drop");
		    } catch (SQLException e) {
		      setTitle("exception");
		    }
		  }
		  
		  private void dropTable() {
		    SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		    String sql = "drop table " + TABLE_NAME;
		    try {
		      db.execSQL(sql);
		      setTitle(sql);
		    } catch (SQLException e) {
		      setTitle("exception");
		    }
		  }
		  
		  private void insertItem() {
		    SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		    String sql1 = "insert into " + TABLE_NAME + " (" + ITEM + ", " + INVENTOR
		        + ") values('RADIO', 'MARCONI');";
		    String sql2 = "insert into " + TABLE_NAME + " (" + ITEM + ", " + INVENTOR
		        + ") values('TELEPHONE', 'GRAHAMBELL');";
		    String sql3 = "insert into " + TABLE_NAME + " (" + ITEM + ", " + INVENTOR
		    + ") values('MICROWAVE', 'SPENCER');";
		    String sql4 = "insert into " + TABLE_NAME + " (" + ITEM + ", " + INVENTOR
		    + ") values('REVOLVER', 'SAMUEL COLT');";
		    String sql5 = "insert into " + TABLE_NAME + " (" + ITEM + ", " + INVENTOR
		    + ") values('SEWING MACHINE', 'MERRITT SINGER');";

		    try {
		      Log.i("sql1=", sql1);
		      Log.i("sql2=", sql2);
		      
		      db.execSQL(sql1);
		      db.execSQL(sql2);
		      db.execSQL(sql3);
		      db.execSQL(sql4);
		      db.execSQL(sql5);
		 
		      setTitle("done");
		    } catch (SQLException e) {
		      setTitle("exception");
		    }
		  }

		/*  private void deleteItem() {
		    try {
		      SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		      db.delete(TABLE_NAME, " item = 'haiyang'", null);
		      setTitle("item");
		    } catch (SQLException e) {
		     }
		  } */
		  
		  private void showItems() {

			Log.i("Sunil:", "********");  
		    SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		    String col[] = { ITEM, INVENTOR };
		    Cursor cur = db.query(TABLE_NAME, col, null, null, null, null, null);    
		   
		    if(cur.moveToFirst()){
		    /* Get the indices of the Columns we will need */
		    	int itemColumn = cur.getColumnIndex("item");
		    	int inventorColumn = cur.getColumnIndex("inventor");
		        int i=0;
		    	do{
		    		String itemName = cur.getString(itemColumn);
		    		String inventorName = cur.getString(inventorColumn);
		             
		            nxttv[i].setText("WHO INVENTED " + itemName);
		            Log.i("Sunil:",itemName);
		            Log.i("Sunil:",inventorName);
		            i++;
		    	} while (cur.moveToNext());
		    }
		  }
}


