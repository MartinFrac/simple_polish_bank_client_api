package com.martinfrac;

import com.martinfrac.model.ExchangeRatesTable;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {

    private static final String api = "http://api.nbp.pl/api/";

    public static void main( String[] args )
    {
        System.out.println( getTable('c') );
    }

    private static String getTable(char character) {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(api)
                .path("exchangerates/tables/" + character)
                .queryParam("format", "json")
                .path("/today");

        Response response = target.request().get();

        if (response.getStatus()!=200) {
            response.close();
            return "error";
        }

        List<ExchangeRatesTable> exchangeRatesTables = response.readEntity(new GenericType<List<ExchangeRatesTable>>() {});
        return exchangeRatesTables.toString();
    }
}
