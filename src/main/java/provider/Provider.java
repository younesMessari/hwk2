package provider;

import javax.xml.ws.Endpoint;

public class Provider {

    private static final String url = "http://localhost:9000/filexchange";
    
    public static void main(String[] args) {
        FileXchange fileXchange = new FileXchange();
        System.out.println("Publishing FileXchange Service.");
        Endpoint.publish(url, fileXchange);
        System.out.println("FileXchange Service Published Succesfully.");
    }

}
