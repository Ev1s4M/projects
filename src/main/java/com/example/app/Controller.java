package com.example.app;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Controller {
    @FXML
    private Text tempInfo;
    @FXML
    private Text tempFeels;
    @FXML
    private Text pressure;
    @FXML
    private Text wind;
    @FXML
    protected TextField city;
    @FXML
    private Button getData;
    @FXML
    private Text textError;
    @FXML
    void initialize() {
        getData.setOnAction(event ->{
            String getUserCity = city.getText().trim();
            String output = getUrlContent("https://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + "&appid=e1386411779b00e948fff9d5e95df625");
            if(!getUserCity.isEmpty()) {
                if(output.equals("error")){
                    textError.setText("город не найден");
                    tempFeels.setText("ощущается: ");
                    tempInfo.setText("температура: ");
                    pressure.setText("давление: ");
                    wind.setText("ветер: ");
                }
                else if (!output.isEmpty()) {
                    JSONObject object = new JSONObject(output);
                    textError.setText("");
                    tempFeels.setText("ощущается: " + String.format("%.2f", (object.getJSONObject("main").getDouble("temp") - 273)) + " °C");
                    tempInfo.setText("температура: " + String.format("%.2f", (object.getJSONObject("main").getDouble("feels_like") - 273)) + " °C");
                    pressure.setText("давление: " + String.format("%.2f", object.getJSONObject("main").getDouble("pressure") * 0.7501) + " мм.рт.ст.");
                    wind.setText("ветер: " + String.format("%.2f", object.getJSONObject("wind").getDouble("speed")) + " м/с");
                }
            }
        });
    }
    private static String getUrlContent(String urlAddress){
        StringBuilder content = new StringBuilder();
        try{
            URL url = new URL(urlAddress);
            URLConnection urlConnection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;

            while((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
        }
        catch (Exception e){
            content.append("error");
        }
        return content.toString();
    }
}