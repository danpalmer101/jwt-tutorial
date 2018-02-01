package io.danpalmer101.jwt.generator.web;

import com.nimbusds.jwt.JWTClaimsSet;
import io.danpalmer101.jwt.verifier.JWTVerifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.io.InputStreamReader;

@RestController
public class LoginController {

    private JWTVerifier jwtVerifier;

    public LoginController() throws Exception {
        InputStream signingStream = getClass().getResourceAsStream("/signing_pub.pem");
        InputStream encryptionStream = getClass().getResourceAsStream("/encryption.pem");

        this.jwtVerifier = new JWTVerifier(
                new InputStreamReader(signingStream),
                new InputStreamReader(encryptionStream));
    }

    @RequestMapping("/login")
    public UserResponse login(@RequestParam("jwt") String jwt) throws Exception {
        JWTClaimsSet claimsSet = this.jwtVerifier.verifyJwt(jwt);

        return new UserResponse(claimsSet.getSubject());
    }

}
