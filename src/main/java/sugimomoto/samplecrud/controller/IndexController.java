package sugimomoto.samplecrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sugimomoto.samplecrud.util.PropertyFileManager;
import sugimomoto.withings4j.WithingsAPIClient;
import sugimomoto.withings4j.WithingsOAuthClient;
import sugimomoto.withings4j.model.*;
import sugimomoto.withings4j.query.SleepGetQueryParameters;

import java.io.IOException;

@Controller
public class IndexController {

    private AccessToken accessToken;
    private WithingsOAuthClient oauth;
    private WithingsAPIClient apiClient;

    private String clientId;
    private String clientSecret;

    public IndexController(){
        PropertyFileManager property = new PropertyFileManager("oauth.properties");
        this.clientId = property.GetPropertyValue("system.oauth.clientid");
        this.clientSecret = property.GetPropertyValue("system.oauth.clientsecret");
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("title","helloworld!");
        model.addAttribute("message","Welcome your page.");


        Scope[] scopes = {Scope.USER_ACTIVITY, Scope.USER_METRICS};
        oauth = new WithingsOAuthClient(clientId, clientSecret, "http://localhost:8881/callback", scopes);

        String authorizationUrl = oauth.getAuthorizationUrl("12345");

        model.addAttribute("url",authorizationUrl);

        return "index";
    }

    @GetMapping("/sleepsummary")
    public String sleepSummaryList(Model model){
        SleepGetQueryParameters param = new SleepGetQueryParameters();
        param.setStartDate(1641740400);
        param.setEndDate(1641826800);
        param.setDataFileds("hr,rr");

        try {
            SleepBase sleepBase =  apiClient.sleepGet(param);
            model.addAttribute("sleeplist",sleepBase.getBody().getSeries());
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error",e.getMessage());
        }

        return "index";
    }

    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code, @RequestParam("state") String state, Model model) {

        try {
            accessToken = oauth.getAccessToken(code).getBody();
            apiClient = new WithingsAPIClient(accessToken);
            model.addAttribute("message","OAuth success");
            model.addAttribute("token",accessToken.getAccessToken());
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error",e.getMessage());
        }

        return "redirect:/sleepsummary";
    }
}
