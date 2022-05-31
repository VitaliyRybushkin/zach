package com.example.jsonparser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Todos> todos =new ArrayList<>();
    private int k=0;
    private TextView fir;
    private TextView sec;
    private TextView thi;
    private TextView fou;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fir = findViewById(R.id.first);
        sec = findViewById(R.id.second);
        thi = findViewById(R.id.third);
        fou = findViewById(R.id.fourth);
        next = findViewById(R.id.next);

        String url ="https://jsonplaceholder.typicode.com/todos";
        new GetUrlData().execute(url);

/*



        fir.setText(todos.get(k).getId());
        sec.setText(todos.get(k).getUserId());
        thi.setText(todos.get(k).getTitle());
        fou.setText(todos.get(k).getCompleted());

*/

    }
    public void clicked (View view){
        int max = todos.size();
        if (k+1<max){
            k = k + 1;

            fir.setText(todos.get(k).getId());
            sec.setText(todos.get(k).getUserId());
            thi.setText(todos.get(k).getTitle());
            fou.setText(todos.get(k).getCompleted());
        }else{
            k=0;

            fir.setText(todos.get(k).getId());
            sec.setText(todos.get(k).getUserId());
            thi.setText(todos.get(k).getTitle());
            fou.setText(todos.get(k).getCompleted());
        }

    }
    private class GetUrlData extends AsyncTask<String, String, String> {
        protected void onPreExecute(){
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null){
                    buffer.append(line).append("\n");
                }
                System.out.println(buffer);
                return buffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(connection != null)
                    connection.disconnect();
                try {
                    if (reader != null) {
                        reader.close();
                    }
                }
                catch (IOException e){
                    e.printStackTrace();
                }

            }
            return null;
        }

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            try {
                JSONArray obj = new JSONArray(result);
                for( int i = 0; i < obj.length(); i++){
                    Todos todok = new Todos(
                            obj.getJSONObject(i).getString("id"),
                            obj.getJSONObject(i).getString("userId"),
                            obj.getJSONObject(i).getString("title"),
                            obj.getJSONObject(i).getString("completed")
                    );
                    System.out.println(todok.getId());

                    todos.add(todok);


                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
}