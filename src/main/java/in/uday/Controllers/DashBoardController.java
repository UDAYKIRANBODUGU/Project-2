package in.uday.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import in.uday.Service.DashBoardService;

@Controller
public class DashBoardController {

    @Autowired
    private DashBoardService dashser;

    @GetMapping("/dashboard")
    public String buildDashboard(Model model) {
        String quoteText = dashser.getQuote();
        model.addAttribute("quote", quoteText);
        return "dashboard";
    }

    @GetMapping("/new-quote")
    public String getNewQuote(Model model) {
        String newQuoteText = dashser.getQuote();
        model.addAttribute("quote", newQuoteText);
        return "dashboard";
    }
}
