package com.hancai.core.config;

/**
 * @author diaohancai
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "dhc-security-core")
@Data
public class DhcSecurityCoreProperties {

    private String welcomePage;

    private Browser browser = new Browser();

    private App app = new App();

    @Data
    public static class Browser {
        private String loginPage = "/module/login/default-login.html";

        private String loginProcessUrl = "/api/login";

        private String logoutPage = "/module/login/default-login.html";

        private String logoutProcessUrl = "/api/logout";
    }

    @Data
    public static class App {
        private String loginPage;

        private String loginProcessUrl;

        private String logoutPage;

        private String logoutProcessUrl;
    }

}
