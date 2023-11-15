package pwr.ite.bedrylo.lab03.client.service;

import pwr.ite.bedrylo.lab03.client.config.ConfigClass;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
public class RequestHandler {

    public static <P, R> R requestHandler(HttpMethod method, String url, P payload, Class<P> payloadType, Class<R> responseType) {
        return WebClient.builder().baseUrl(ConfigClass.SERVER_URL).build()
                .method(method)
                .uri(url)
                .body(Mono.just(payload), payloadType)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .retrieve()
                .bodyToMono(responseType)
                .block();
    }

    public static <R> R requestHandler(HttpMethod method, String url, Class<R> responseType) {
        return WebClient.builder().baseUrl(ConfigClass.SERVER_URL).build()
                .method(method)
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .retrieve()
                .bodyToMono(responseType)
                .block();
    }
        
    
}
