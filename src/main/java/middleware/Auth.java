package middleware;

import spark.Request;
import spark.Response;

public class Auth{

    public Response verificarSesion(Request request, Response response){
        if(!request.session().isNew()){
            request.session().id();
            response.redirect("/");
        }
        return response;
    }
}
