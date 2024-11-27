package com.handson.chatbot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChuckNorrisService {

    @Autowired
    ObjectMapper objectMapper;
    public String searchJokes(String keyword) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
//        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://api.chucknorris.io/jokes/search?query=" + keyword)
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();

        ChuckNorrisResponse res = objectMapper.readValue(response.body().string(), ChuckNorrisResponse.class);
        return res.getJokeValuesAsString();
    }



    // Main class to represent the response
    private static class ChuckNorrisResponse {
        private int total;
        private List<Joke> result;

        // Getters and Setters
        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<Joke> getResult() {
            return result;
        }

        // Utility method to get all joke values
        public List<String> getJokeValues() {
            return result.stream()
                    .map(Joke::getValue)
                    .collect(Collectors.toList());
        }
        // Utility method to get all joke values as a single string with line breaks
        public String getJokeValuesAsString() {
            return result.stream()
                    .map(Joke::getValue)
                    .collect(Collectors.joining("\n"));
        }

        public void setResult(List<Joke> result) {
            this.result = result;
        }
    }

    // Class to represent each joke
    private static class Joke {
        private List<String> categories;
        private String created_at;
        private String icon_url;
        private String id;
        private String updated_at;
        private String url;
        private String value;

        // Getters and Setters
        public List<String> getCategories() {
            return categories;
        }

        public void setCategories(List<String> categories) {
            this.categories = categories;
        }

        public String getCreatedAt() {
            return created_at;
        }

        public void setCreatedAt(String created_at) {
            this.created_at = created_at;
        }

        public String getIconUrl() {
            return icon_url;
        }

        public void setIconUrl(String icon_url) {
            this.icon_url = icon_url;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUpdatedAt() {
            return updated_at;
        }

        public void setUpdatedAt(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
