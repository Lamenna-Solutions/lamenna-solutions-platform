package app.lamenna.commons.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@ConfigurationProperties(prefix = "lamenna.security.jwt")
public class SecurityProperties {
    private Resource publicKeyLocation;

    private String issuer;

    public Resource getPublicKeyLocation() { return publicKeyLocation; }
    public void setPublicKeyLocation(Resource publicKeyLocation) { this.publicKeyLocation = publicKeyLocation; }
    public String getIssuer() { return issuer; }
    public void setIssuer(String issuer) { this.issuer = issuer; }
}
