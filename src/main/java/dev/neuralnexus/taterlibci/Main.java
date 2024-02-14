package dev.neuralnexus.taterlibci;

import dev.neuralnexus.ampapi.modules.Minecraft;

import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        String ipAddress = System.getenv("IP_ADDRESS");
        int port = Integer.parseInt(System.getenv("PORT"));
        String baseUri = System.getenv("AMP_API_URL");
        String username = System.getenv("AMP_API_USERNAME");
        String password = System.getenv("AMP_API_PASSWORD");
        Minecraft API = new Minecraft(baseUri, username, password);

        Javalin app = Javalin.create();

        // Static files
        app.get("/openapi.json", APIRoutes::getOpenAPIJSON);
        // API Docs
        app.get("/docs", APIRoutes::getDocs);
        app.get("/", APIRoutes::redirectToDocs);

        // API Endpoints
        app.post("/trigger", APIRoutes::trigger);

        app.start(ipAddress, port);
    }
}
