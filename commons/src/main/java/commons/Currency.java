package commons;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.net.URI;
import java.util.Objects;

/**
 * Currency class.
 */
@Entity
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String name; // for example, Dollar
    String code; // for example, USD
    char symbol; // for example, $

    /**
     * Currency constructor.
     *
     * @param name   Name of a currency, e.g. Dollar
     * @param code   Code of a currency, e.g. USD
     * @param symbol Symbol for a currency, e.g. $
     */
    public Currency(String name, String code, char symbol) {
        this.name = name;
        this.code = code;
        this.symbol = symbol;
    }

    /**
     * Empty constructor for JPA.
     */
    protected Currency() {
    }

    /**
     * Gets the conversion rate between two currencies.
     * Uses the <a href="https://www.frankfurter.app/docs/">Frankfurter</a> api.
     *
     * @param otherCurrency The Currency method that it should be converted to.
     * @return conversion rate
     */
    public BigDecimal getConversionRate(Currency otherCurrency) {
        if (this.equals(otherCurrency)) {
            return BigDecimal.ONE;
        }

        try {
            URI uri = new URI(
                "https://api.frankfurter.app/latest?from=" + code + "&to=" + otherCurrency.code);

            JsonNode root = new ObjectMapper().readTree(uri.toURL());

            return new BigDecimal(root.get("rates").get(otherCurrency.code).asText());
        } catch (Exception e) {
            System.err.println("Couldn't get conversion rate from api. Reason: " + e);
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Currency currency = (Currency) o;
        return symbol == currency.symbol && Objects.equals(name, currency.name)
            && Objects.equals(code, currency.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code, symbol);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public long getId() {
        return id;
    }
}
