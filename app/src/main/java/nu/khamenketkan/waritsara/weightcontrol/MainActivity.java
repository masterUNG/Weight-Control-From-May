package nu.khamenketkan.waritsara.weightcontrol;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private MyManage myManage;
    private MyData myData;
    private TextView dateTextview, nameTextView, bmrTextView,
            caloriesTextView, burnTextView, myBMRTextView;
    private String dateString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget ให้ทำการค้นหารายชื่อจาก table ใน file ที่ชื่อว่า r.จาวา
        dateTextview = (TextView) findViewById(R.id.textView8);
        nameTextView = (TextView) findViewById(R.id.textView9);
        bmrTextView = (TextView) findViewById(R.id.textView10);
        caloriesTextView = (TextView) findViewById(R.id.textView11);
        burnTextView = (TextView) findViewById(R.id.textView12);
        myBMRTextView = (TextView) findViewById(R.id.textView13);


        myManage = new MyManage(this);

        //Test Add Value
        // testAddValue();

        //Add First Data
        addFirstData();

        //CheckUserTABLE
        checkUserTABLE();


    } // main method

    private void showBurn() {

        try {

            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                    MODE_PRIVATE, null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM burnTABLE WHERE Date = " + "'" + dateString + "'", null);
            cursor.moveToFirst();
            double douTotalBurn = 0;

            if (cursor.getCount() == 0) {
                burnTextView.setText("Burn ==> " + "?");
            } else {

                String[] burnStrings = new String[cursor.getCount()];

                for (int i = 0; i < cursor.getCount(); i += 1) {

                    burnStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_burn));
                    douTotalBurn = douTotalBurn + Double.parseDouble(burnStrings[i]);

                    cursor.moveToNext();
                }   // for

            }   // if
            cursor.close();
            burnTextView.setText("Burn ==> " + Double.toString(douTotalBurn));


        } catch (Exception e) {
            Log.d("WeightV1", "e burn ==> " + e.toString());
            burnTextView.setText("Burn ==> " + "?");
        }


    }    //showBurn

    @Override
    protected void onRestart() {
        super.onRestart();
        showCalories();
        showBurn();
    }

    public void clickBurn(View view) {
        Intent intent = new Intent(MainActivity.this, BurnListView.class);
        intent.putExtra("Date", dateString);
        startActivity(intent);
    } //ผูกปุ่มกับเมธอด intent คือการเคลื่อนย้ายการทำงาน


    public void clickCalories(View view) {
        Intent intent = new Intent(MainActivity.this, CaloriesListView.class);
        intent.putExtra("Date", dateString);
        startActivity(intent);
    } //ผูกปุ่มกับเมธอด intent คือการเคลื่อนย้ายการทำงาน

    private void showCalories() {

        try {

            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                    MODE_PRIVATE, null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM caloriesTABLE WHERE Date = " + "'" + dateString + "'", null);
            cursor.moveToFirst();
            double douTotalCalories = 0;

            if (cursor.getCount() == 0) {
                caloriesTextView.setText("Calories ==> " + "?");
            } else {

                String[] caloriesStrings = new String[cursor.getCount()];

                for (int i = 0; i < cursor.getCount(); i += 1) {

                    caloriesStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_calories));
                    douTotalCalories = douTotalCalories + Double.parseDouble(caloriesStrings[i]);

                    cursor.moveToNext();
                }   // for

            }   // if
            cursor.close();
            caloriesTextView.setText("Calories ==> " + Double.toString(douTotalCalories));


        } catch (Exception e) {
            Log.d("WeightV1", "e showCalories ==> " + e.toString());
            caloriesTextView.setText("Calories ==> " + "?");
        }

    }   // showCalories

    private void showName() {

        //show date อ่านเดต้าแล้วดึงขึ้นมาโชว ดึงวันที่ ปจบ มาโชว์
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateString = dateFormat.format(calendar.getTime());
        dateTextview.setText("Date = " + dateString);

        //Connected SQLite
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM userTABLE", null);
        cursor.moveToFirst();
        String strName = cursor.getString(cursor.getColumnIndex(MyManage.column_Name));
        String strSurname = cursor.getString(cursor.getColumnIndex(MyManage.column_Surname));
        String strBMR = cursor.getString(cursor.getColumnIndex(MyManage.column_BMR));
        nameTextView.setText(strName + " " + strSurname);
        bmrTextView.setText("BMR = " + String.format("%.2f", Double.parseDouble(strBMR)));
        cursor.close();
        //  cursor.close คืนหน่วยความจำให้กับมือถือ
        // %.2f คือจะเอาจุดทศนิยมมาโชแค่ 2 ตัว
        // string ดึง ชื่อ bmr ขึ้นมาโชวจากในฐานข้อมูล
    } // Show name rawquery การ query data การดึงข้อมูล select คือคำสั่งดึงข้อมูล cursor ดึงข้อมูลเข้าไปทำงานในแรม

    private void checkUserTABLE() {

        try {

            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                    MODE_PRIVATE, null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM userTABLE", null);

            if (cursor != null) {
                Log.d("6octV1", "cursor not null" + cursor.getCount());
            } else {
                Log.d("6octV1", "cursor null");
            }

            cursor.moveToFirst();

            if (cursor.getCount() == 0) {   //userTABLE ไม่มีข้อมูล รอการบันทึก
                Log.d("6octV1", "Intent OK");
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            } else {

                // show name ดึงชื่อผู้ใช้มาโชว์
                showName();

                //show calories
                showCalories();

                //show Burn
                showBurn();

            }
            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    } //checkUser

    //จะทำการลบ foodTABLE  และ exerciseTABLE ก่อน แล้วค่อย Add Value ใหม่ทุกๆ ครั้งที่เปิด
    private void addFirstData() {

        //Delete All Data
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        sqLiteDatabase.delete(MyManage.food_table, null, null);
        sqLiteDatabase.delete(MyManage.exercise_table, null, null);

        //For foodTABLE
        myData = new MyData();
        String[] foodStrings = myData.getFoodStrings();
        String[] unitStrings = myData.getUnitStrings();
        String[] caloriesStrings = myData.getCaloriesStrings();
        for (int i = 0; i < foodStrings.length; i += 1) {

            // Add Value to foo
            myManage.addFood(
                    foodStrings[i],
                    unitStrings[i],
                    caloriesStrings[i]);
        } //For

        //For exerciseTABLE
        String[] exerciseStrings = myData.getExerciseStrings();
        String[] burnStrings = myData.getBurnStrings();
        for (int i = 0; i < exerciseStrings.length; i += 1) {
            myManage.addExercise(exerciseStrings[i], burnStrings[i]);
        }   // for

    } //addFirstData

    private void testAddValue() {

        myManage.addFood("food", "unit", "calories");
        myManage.addExercise("exercise", "burn");

    } //testAddVale


} //Main class
