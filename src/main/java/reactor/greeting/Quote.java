package reactor.greeting;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by chanwook on 2015. 1. 22..
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

    private long id;
    private String quote;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
}
