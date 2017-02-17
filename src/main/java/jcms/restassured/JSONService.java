package jcms.restassured;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public final class JSONService {

    private JSONService() {
    }

    public static JsonNode loadResourceFileAsJSONObject(String pathInClassPath) throws IOException {

        InputStream body = JSONService.class.getResourceAsStream(pathInClassPath);
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readTree(body);
    }

    public static String loadResourceFile(String pathInClassPath) throws IOException {

        JsonNode jsonNode = loadResourceFileAsJSONObject(pathInClassPath);
        return jsonNode.toString();
    }

    public static void change(JsonNode parent, String fieldName, String newValue) {
        if (parent.has(fieldName)) {
            ((ObjectNode) parent).put(fieldName, newValue);
        }

        // Now, recursively invoke this method on all properties
        for (JsonNode child : parent) {
            change(child, fieldName, newValue);
        }
    }

    public static void add(JsonNode jsonNode, String fieldName, String newValue) {
        ((ObjectNode) jsonNode).put(fieldName, newValue);
    }
}
