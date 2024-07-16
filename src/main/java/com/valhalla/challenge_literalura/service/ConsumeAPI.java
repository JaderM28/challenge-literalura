package com.valhalla.challenge_literalura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumeAPI {

    public String obtenerDatosPeticion( String url){

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();


        HttpResponse<String> response = null;

        try{

            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();

        }catch (IOException | InterruptedException e){
            throw new RuntimeException(e);
        }

    }

}
