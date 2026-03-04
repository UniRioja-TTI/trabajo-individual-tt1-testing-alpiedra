package utilidades;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ServicioConsumibleClient {

    private static final String BASE_URL = "http://localhost:8080";
    private final HttpClient client = HttpClient.newHttpClient();

    public String enviarEmail(String emailAddress, String message) throws Exception {
        String url = BASE_URL + "/Email?emailAddress=" + emailAddress + "&message=" + message;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.noBody())
                .header("Accept", "application/json")
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

    public String crearSolicitud(String nombreUsuario, int[] cantidades, String[] entidades) throws Exception {
        StringBuilder cantidadesJson = new StringBuilder("[");
        for (int i = 0; i < cantidades.length; i++) {
            cantidadesJson.append(cantidades[i]);
            if (i < cantidades.length - 1) cantidadesJson.append(",");
        }
        cantidadesJson.append("]");
        StringBuilder entidadesJson = new StringBuilder("[");
        for (int i = 0; i < entidades.length; i++) {
            entidadesJson.append("\"").append(entidades[i]).append("\"");
            if (i < entidades.length - 1) entidadesJson.append(",");
        }
        entidadesJson.append("]");
        String body = "{\"cantidadesIniciales\":" + cantidadesJson + ",\"nombreEntidades\":" + entidadesJson + "}";
        String url = BASE_URL + "/Solicitud/Solicitar?nombreUsuario=" + nombreUsuario;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .header("Content-Type", "application/json")
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

    public String getSolicitudesUsuario(String nombreUsuario) throws Exception {
        String url = BASE_URL + "/Solicitud/GetSolicitudesUsuario?nombreUsuario=" + nombreUsuario;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

    public String comprobarSolicitud(String nombreUsuario, int tok) throws Exception {
        String url = BASE_URL + "/Solicitud/ComprobarSolicitud?nombreUsuario=" + nombreUsuario + "&tok=" + tok;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

    public String enviarResultados(String nombreUsuario, int tok) throws Exception {
        String url = BASE_URL + "/Resultados?nombreUsuario=" + nombreUsuario + "&tok=" + tok;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }
}