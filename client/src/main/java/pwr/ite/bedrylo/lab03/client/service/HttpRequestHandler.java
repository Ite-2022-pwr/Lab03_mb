package pwr.ite.bedrylo.lab03.client.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import reactor.core.publisher.Mono;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.nio.charset.StandardCharsets;

public class HttpRequestHandler<T> {

    private final WebClient webClient;
    private final ObjectMapper objectMapper;
    
    private final String baseUrl = "http://localhost:8080";

    public HttpRequestHandler(WebClient webClient) {
        this.webClient = webClient;
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    public <R> R sendRequest(String url, String method, Class<R> resultClass) {
        return webClient
                .method(HttpMethod.valueOf(method))
                .uri(baseUrl + url)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .retrieve()
                .bodyToMono(String.class)
                .map(responseBody -> {
                    try {
                        return objectMapper.readValue(responseBody, resultClass);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .block();
    }
    
    public <P, R> R sendRequest(String url, String method, Class<R> resultClass, P body, Class<P> bodyClass) {
        return webClient
                .method(HttpMethod.valueOf(method))
                .uri(baseUrl + url)
                .body(Mono.just(body), bodyClass)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .retrieve()
                .bodyToMono(String.class)
                .map(responseBody -> {
                    try {
                        return objectMapper.readValue(responseBody, resultClass);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .block();
    }
}
