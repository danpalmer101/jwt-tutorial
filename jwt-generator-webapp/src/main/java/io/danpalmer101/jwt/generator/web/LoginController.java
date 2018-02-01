package io.danpalmer101.jwt.generator.web;

import com.nimbusds.jwt.JWTClaimsSet;
import io.danpalmer101.jwt.generator.JWTGenerator;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.UUID;

@RestController
public class LoginController {

    private JWTGenerator jwtGenerator;

    public LoginController() throws Exception {
        InputStream signingStream = getClass().getResourceAsStream("/signing.pem");
        InputStream encryptionStream = getClass().getResourceAsStream("/encryption_pub.pem");

        this.jwtGenerator = new JWTGenerator(
                new InputStreamReader(signingStream),
                new InputStreamReader(encryptionStream));
    }

    @RequestMapping("/login")
    public JWTResponse login(@RequestParam("username") String username) throws Exception {
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .jwtID(UUID.randomUUID().toString())
                .subject(username)
                .issuer("jwt-generator-webapp")
                .expirationTime(new DateTime().plusYears(1).toDate())
                .notBeforeTime(new Date())
                .issueTime(new Date())
                .audience("jwt-verifier-webapp")
                .build();

        return new JWTResponse(this.jwtGenerator.generateJwt(claimsSet).serialize());
    }

}
