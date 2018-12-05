package com.codecool;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class MyServer implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) {
        Class myServerClass = MyServer.class;

        Method[] methods = myServerClass.getDeclaredMethods();
        for(Method method : methods){

            if(method.isAnnotationPresent(WebRoute.class)) {
                Annotation annotation = method.getDeclaredAnnotation(WebRoute.class);
                WebRoute webRouteAnnotation = (WebRoute) annotation;

                if(isPathEqual(webRouteAnnotation, httpExchange) && isMethodEqual(webRouteAnnotation, httpExchange)) {
                    try {
                        Object serverClassObject = myServerClass.newInstance();
                        method.invoke(serverClassObject, httpExchange);
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private boolean isPathEqual(WebRoute webRouteAnnotation, HttpExchange httpExchange) {
        return webRouteAnnotation.path().equalsIgnoreCase(httpExchange.getRequestURI().getPath());
    }

    private boolean isMethodEqual(WebRoute webRouteAnnotation, HttpExchange httpExchange) {
        return webRouteAnnotation.method().equalsIgnoreCase(httpExchange.getRequestMethod());
    }

    @WebRoute(path = "/test")
    private void handleTest(HttpExchange requestData) throws IOException {
        String response = "Hi I'm in test";
        requestData.sendResponseHeaders(200, response.length());
        OutputStream os = requestData.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    @WebRoute(path = "/form")
    private void handleForm (HttpExchange requestData) throws IOException {

        String response = "<html><body>" +
                "<form method=\"POST\">\n" +
                "  First name:<br>\n" +
                "  <input type=\"text\" name=\"firstname\" value=\"Katarzyna\">\n" +
                "  <br>\n" +
                "  Last name:<br>\n" +
                "  <input type=\"text\" name=\"lastname\" value=\"Kapusta\">\n" +
                "  <br><br>\n" +
                "  <input type=\"submit\" value=\"Submit\">\n" +
                "</form> " +
                "</body></html>";

        requestData.sendResponseHeaders(200, response.length());
        OutputStream os = requestData.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    @WebRoute(method = "POST", path = "/form")
    private void handlereceivedForm (HttpExchange requestData) throws IOException {
        String response = "I've save it... somewhere.";
        requestData.sendResponseHeaders(200, response.length());
        OutputStream os = requestData.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    @WebRoute(path = "/user")
    private void handleUser(HttpExchange requestData) throws IOException {
        String response = "Hi I'm in trail";
        requestData.sendResponseHeaders(200, response.length());
        OutputStream os = requestData.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
