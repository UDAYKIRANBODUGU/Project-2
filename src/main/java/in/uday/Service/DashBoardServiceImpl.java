package in.uday.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.uday.Props.AppProps;
import in.uday.bindings.Quote;

@Service
public class DashBoardServiceImpl implements DashBoardService {

    @Autowired
    private AppProps props;

    private Quote[] quotes;
    private final WebClient webClient;
    ObjectMapper objectMapper = new ObjectMapper();

    public DashBoardServiceImpl(Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(props.getMessages().get("QuoteURL")).build();
    }

    @Override
    public String getQuote() {
        if (quotes == null) {
            try {
                String responseBody = webClient.get().retrieve().bodyToMono(String.class).block();
                quotes = objectMapper.readValue(responseBody, Quote[].class);
            } catch (Exception e) {
                e.printStackTrace();
                return "Error fetching quotes.";
            }
        }

        if (quotes != null && quotes.length > 0) {
            int nextInt = (int) (Math.random() * quotes.length); // Use Math.random() for better randomness
            return quotes[nextInt].getText();
        } else {
            return "No quotes available.";
        }
    }
}