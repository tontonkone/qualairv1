package fr.diginamic.qualairapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration personnalisée pour la sécurité web (CORS et gestion des ressources statiques).
 */
@Configuration
@EnableWebMvc // Activation de la configuration Web MVC personnalisée
public class WebSecurityConfig implements WebMvcConfigurer {

    /**
     * Configure les paramètres CORS (Cross-Origin Resource Sharing).
     *
     * @param corsRegistry Le registre CORS pour la configuration.
     */
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedOriginPatterns("*") // Autorise toutes les origines
                .allowedMethods("*") // Autorise toutes les méthodes HTTP
                .maxAge(3600L) // Durée maximale de validité de la configuration CORS en secondes
                .allowedHeaders("*") // Autorise tous les en-têtes HTTP
                .exposedHeaders("Authorization") // Autorise l'exposition de l'en-tête "Authorization"
                .allowCredentials(true); // Autorise l'utilisation de cookies lors des demandes CORS
    }

    /**
     * Configure la gestion des ressources statiques, telles que Swagger UI et WebJars.
     *
     * @param registry Le registre de gestion des ressources statiques.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Configuration pour la gestion des ressources statiques
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
