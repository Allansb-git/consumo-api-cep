package br.edu.fatecpg.cepapi.service;

import br.edu.fatecpg.cepapi.model.Endereco;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ServiceApi {

    public static Endereco buscarEndereco(String cep) throws Exception {
        // Remove caracteres não numéricos
        String cepFormatado = cep.replaceAll("\\D", "");

        if (cepFormatado.length() != 8) {
            throw new IllegalArgumentException("CEP inválido. Deve conter 8 dígitos.");
        }

        String url = "https://viacep.com.br/ws/" + cepFormatado + "/json/";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Erro ao consultar CEP. Status: " + response.statusCode());
        }

        Gson gson = new Gson();
        Endereco endereco = gson.fromJson(response.body(), Endereco.class);

        if (endereco.getCep() == null) {
            throw new IllegalArgumentException("CEP não encontrado.");
        }

        return endereco;
    }
}