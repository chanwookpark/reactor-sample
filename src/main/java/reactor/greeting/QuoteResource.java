package reactor.greeting;

/**
 * Created by chanwook on 2015. 1. 22..
 */
public class QuoteResource {

    private String type;
    private Quote value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Quote getValue() {
        return value;
    }

    public void setValue(Quote value) {
        this.value = value;
    }
}
