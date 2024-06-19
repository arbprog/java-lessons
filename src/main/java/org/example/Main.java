package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        // Запрашиваем дату у пользователя
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите дату в формате 2024-06-01: ");
        String curDate = scanner.nextLine();

        // Урл апи с сайта НАСА с ключем
        String url = "https://api.nasa.gov/planetary/apod?api_key=qjs49Unhqv1jA2SnEpO3dP05tiMip7JuyPuXL94i&date=" + curDate;
        System.out.println(url);

        // С помощью apach создает http клиент (httpclient)
        // Передаем в get запрос строку api (httpGet)
        // Получаем ответ с сервера (response)
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpclient.execute(httpGet);

        ObjectMapper mapper = new ObjectMapper();
        NASAAnswer answer = mapper.readValue(response.getEntity().getContent(), NASAAnswer.class);

        if (Objects.equals(answer.media_type, "image")) {
            String imageUrl = answer.url;
            String[] splitUrl = imageUrl.split("/");
            String fileName = splitUrl[splitUrl.length - 1];
            HttpGet imageGet = new HttpGet(imageUrl);
            CloseableHttpResponse image = httpclient.execute(imageGet);
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            image.getEntity().writeTo(fileOutputStream);
        } else {
            System.out.println("Answer media type not image!");
        }
    }
}