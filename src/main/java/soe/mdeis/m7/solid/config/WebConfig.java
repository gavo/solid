package soe.mdeis.m7.solid.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

   @Value("${frontend.host}")
   private String host;

   @Value("${frontend.port}")
   private String port;

   @SuppressWarnings("null")
   @Override
   public void addCorsMappings(CorsRegistry registry) {
      var address = host
            + (port != null && Integer.valueOf(port) > 0 && Integer.valueOf(port) != 80 && Integer.valueOf(port) != 443
                  ? (":" + port)
                  : "");
      registry.addMapping("/**")
            .allowedOrigins(address)
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(true);
   }
}