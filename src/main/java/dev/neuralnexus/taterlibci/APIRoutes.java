package dev.neuralnexus.taterlibci;

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
    static void getOpenAPIJSON(Context ctx) {
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
    static void getDocs(Context ctx) {
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
    static void redirectToDocs(Context ctx) {
        ctx.status(HttpStatus.MOVED_PERMANENTLY);
        ctx.redirect("/docs");
    }

    /**
     * Triggers a CI test.
     *
     * @param ctx The context to use.
     */
    static void trigger(Context ctx) {
        //        if (!ContentType.JSON.equals(ctx.contentType())) {
        //            ctx.status(HttpStatus.BAD_REQUEST);
        //            ctx.result("Invalid content type.");
        //            return;
        //        }
        System.out.println(ctx.body());

        ctx.contentType(ContentType.JSON);
        ctx.status(HttpStatus.OK);
        ctx.result("{\"status\": \"success\"}");
    }
}
