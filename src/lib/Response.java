package lib;

import java.io.Serializable;

public class Response implements Serializable {
    String response;
    public Response(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }
}
