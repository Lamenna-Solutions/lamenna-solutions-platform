package app.lamenna.commons.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;

import java.security.interfaces.RSAPublicKey;

public class JwtVerifier {
    private final RSAPublicKey publicKey;
    private final String issuer;

    public JwtVerifier(SecurityProperties props) {
        this.publicKey = PublicKeyLoader.fromResource(props.getPublicKeyLocation());
        this.issuer = props.getIssuer();
    }

    public Claims verify(String token) {
        JwtParserBuilder parser = Jwts.parser().verifyWith(publicKey);
        if (issuer != null && !issuer.isBlank()) {
            parser.requireIssuer(issuer);
        }
        return parser.build().parseSignedClaims(token).getPayload();
    }
}
