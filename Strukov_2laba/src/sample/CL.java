package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Matthew on 22.12.2016.
 */
public class CL {
    private final StringProperty cryptName;

    public CL(String cryptName) {
        this.cryptName = new SimpleStringProperty(cryptName);
    }

    public StringProperty getCryptName() {
        return cryptName;
    }

    public void setCryptName(String cryptName) {
        this.cryptName.set(cryptName);
    }
}
