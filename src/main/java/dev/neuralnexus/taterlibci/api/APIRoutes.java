package dev.neuralnexus.taterlibci.api;

import dev.neuralnexus.taterlibci.Main;

import io.javalin.http.ContentType;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.io.IOException;

/** A class for handling API routes. */
public class APIRoutes {
    /**
     * Gets the OpenAPI JSON file.
     *
     * @param ctx The context to use.
     */
    public static void getOpenAPIJSON(Context ctx) {
        try {
            String index =
                    new String(
                            Main.class
                                    .getClassLoader()
                                    .getResourceAsStream("openapi.json")
                                    .readAllBytes());
            ctx.status(HttpStatus.OK);
            ctx.contentType(ContentType.TEXT_PLAIN);
            ctx.result(index);
        } catch (IOException e) {
            ctx.status(HttpStatus.INTERNAL_SERVER_ERROR);
            ctx.result("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Gets the docs page.
     *
     * @param ctx The context to use.
     */
    public static void getDocs(Context ctx) {
        try {
            String index =
                    new String(
                            Main.class
                                    .getClassLoader()
                                    .getResourceAsStream("index.html")
                                    .readAllBytes());
            ctx.status(HttpStatus.OK);
            ctx.contentType(ContentType.TEXT_HTML);
            ctx.result(index);
        } catch (IOException e) {
            ctx.status(HttpStatus.INTERNAL_SERVER_ERROR);
            ctx.result("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Redirects to the docs page.
     *
     * @param ctx The context to use.
     */
    public static void redirectToDocs(Context ctx) {
        ctx.status(HttpStatus.MOVED_PERMANENTLY);
        ctx.redirect("/docs");
    }

    /**
     * Triggers a CI test.
     *
     * @param ctx The context to use.
     */
    public static void trigger(Context ctx) {
        if (!ContentType.JSON.equals(ctx.header("Content-Type"))) {
            ctx.status(HttpStatus.BAD_REQUEST);
            ctx.result("Invalid content type.");
            return;
        }
        TriggerRequest request;
        try {
            request = ctx.bodyAsClass(TriggerRequest.class);
            System.out.println(request.generatePermutations());
        } catch (Exception e) {
            ctx.status(HttpStatus.BAD_REQUEST);
            ctx.result("Invalid request body.");
            return;
        }

        ctx.contentType(ContentType.JSON);
        ctx.status(HttpStatus.OK);
        ctx.json("{\"testId\": \"" + request.testId + "\"}");
    }
}
