package jaebong.test;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by JaeBong on 15. 8. 20..
 */
public class Dao {
    private Context context;
    private SQLiteDatabase database;
    private static int count = 0;


    public Dao(Context context) {
        this.context = context;

        database = context.openOrCreateDatabase("LocalDATA.db",
                SQLiteDatabase.CREATE_IF_NECESSARY, null);

        makeTableNamed("Accommodation");
        makeTableNamed("Greeting");
        makeTableNamed("Map");
        makeTableNamed("Restaurant");
        makeTableNamed("Shopping");
        makeTableNamed("Tour");
        makeTableNamed("Transportation");


    }


    public String getJsonTOString(){
        String result = "";
        AssetManager assetManager = context.getResources().getAssets();

        try{
            AssetManager.AssetInputStream ais = (AssetManager.AssetInputStream)assetManager.open("json/contents.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(ais));
            StringBuilder stringBuilder = new StringBuilder();
            int bufferSize = 1024 * 1024;
            char readBuf[] = new char[bufferSize];
            int resultSize = 0;

            while((resultSize = br.read(readBuf)) != -1){
                if(resultSize == bufferSize){
                    stringBuilder.append(readBuf);
                }else{
                    for(int i = 0; i < resultSize; i++){
                        //StringBuilder 에 append
                        stringBuilder.append(readBuf[i]);
                    }
                }
            }
            result = stringBuilder.toString();

            Log.i("Json Data :", stringBuilder.toString());


        }catch(IOException e){
            Log.e("Get Json File Error", "" + e);
        }
        return result;
    }

    public void makeTableNamed(String tableName){
        try {
            String createContentsSQL = "CREATE TABLE IF NOT EXISTS "+tableName+"(ID integer primary key autoincrement,"
                    + "										 Korean text not null,"
                    + "										 Japanese text not null,"
                    + "										 Chinese text not null ,"
                    + "										 English text not null ,"
                    + "                                      Sound text);"
                    ;
            database.execSQL(createContentsSQL);
            Log.e("test","CREATE TABLE " +tableName + " SUCCESS");


        } catch (Exception e) {
            Log.e("test", "CREATE TABLE FAILED! - " + e);
            e.printStackTrace();
        }

    }

    public void insertJsonArrayNamed(String keyName, String DBName, JSONObject jsonObject) {
        String Korean;
        String Japanese;
        String Chinese;
        String English;
        String Sound;

        try {

            JSONArray JsonArray = jsonObject.getJSONArray(keyName);
            for (int i = 0; i < JsonArray.length(); ++i) {
                JSONObject jObj = JsonArray.getJSONObject(i);
                Korean = jObj.getString("korean");
                Japanese = jObj.getString("japanese");
                Chinese = jObj.getString("chinese");
                English = jObj.getString("english");
                Sound = DBName.toLowerCase()+ "_"+ (i+1);


                String sql = "INSERT INTO " + DBName + "(Korean, Japanese, Chinese, English, Sound)"
                        + " VALUES('"
                        + Korean
                        + "', '"
                        + Japanese
                        + "', '"
                        + Chinese
                        + "', '"
                        + English
                        + "', '"
                        + Sound + "');";

                try {
                    database.execSQL(sql);
                    Log.e("test","JSON " +DBName + " SUCCESS" );
                } catch (Exception e) {
                    Log.e("test", "Greeting ERROR! -" + e);
                }
            }
        }catch (Exception e){
            Log.e("test", "Json Error at " + DBName + "-" + e);
        }
    }

    public void insertJsonData(String jsonData) {
        if(count != 0) return;
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            insertJsonArrayNamed("기본 인사편","Greeting",jsonObject);
            insertJsonArrayNamed("교통편","Transportation",jsonObject);
            insertJsonArrayNamed("숙소편","Accommodation",jsonObject);
            insertJsonArrayNamed("식당편","Restaurant",jsonObject);
            insertJsonArrayNamed("쇼핑편","Shopping",jsonObject);
            insertJsonArrayNamed("지도편","Map",jsonObject);
            insertJsonArrayNamed("관광편","Tour",jsonObject);
            count++;

        } catch (Exception e) {
            Log.e("test", "JSON Object ERROR! - " + e);
            e.printStackTrace();
        }
    }

    public ArrayList<Content> getContentFromTable(String tableName){
        ArrayList<Content> contentList = new ArrayList<Content>();

        String korean;
        String japanese;
        String chinese;
        String english;
        String sound;

        String sql = "SELECT * FROM " + tableName+";";
        Log.e("test","SQL - " + sql);
        Cursor cursor = database.rawQuery(sql,null);

        while(cursor.moveToNext()){
            korean = cursor.getString(1);
            japanese = cursor.getString(2);
            chinese = cursor.getString(3);
            english = cursor.getString(4);
            sound = cursor.getString(5);

            contentList.add(new Content(korean,japanese,chinese,english,sound));

        }

        cursor.close();
        return contentList;

    }
}
