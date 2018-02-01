package com.worldpay.mobile.poc.jwtverifier;

import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.util.io.pem.PemObject;

import java.io.Reader;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;

public class JWTVerifier {

    private final PrivateKey encryptionKey;
    private final PublicKey signingKey;

    public JWTVerifier(Reader signingKeyReader, Reader encryptionKeyReader) throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        KeyFactory factory = KeyFactory.getInstance("RSA", "BC");

        // Read private signing key
        PemObject signingKeyObject = new PEMParser(signingKeyReader).readPemObject();
        byte[] signingPrivatePem = signingKeyObject.getContent();
        X509EncodedKeySpec signingKeySpec = new X509EncodedKeySpec(signingPrivatePem);
        this.signingKey = factory.generatePublic(signingKeySpec);

        // Read public encryption key
        PemObject encryptionKeyObject = new PEMParser(encryptionKeyReader).readPemObject();
        byte[] encryptionPublicPem = encryptionKeyObject.getContent();
        PKCS8EncodedKeySpec encryptionKeySpec = new PKCS8EncodedKeySpec(encryptionPublicPem);
        this.encryptionKey = factory.generatePrivate(encryptionKeySpec);
    }

    public JWTClaimsSet verifyJwt(String jwt) throws Exception {

        // Decrypt JWT

        JWEObject jweObject = JWEObject.parse(jwt);
        jweObject.decrypt(new RSADecrypter(this.encryptionKey));

        // Verify JWT Signature

        SignedJWT signedJWT = jweObject.getPayload().toSignedJWT();
        signedJWT.verify(new RSASSAVerifier((RSAPublicKey)this.signingKey));

        // Validate JWT Data

        JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();

        Date now = new Date();
        assertTrue(claimsSet.getExpirationTime() != null && claimsSet.getExpirationTime().after(now),
                "Expiration time must be set and in the future");
        assertTrue(claimsSet.getNotBeforeTime() == null || !claimsSet.getNotBeforeTime().after(now),
                "If provided, not before time must be in the past");
        assertTrue(claimsSet.getIssueTime() == null || !claimsSet.getIssueTime().after(now),
                "If provided, issue time must be in the past");
        assertTrue(claimsSet.getAudience().contains("worldpay_pos"),
                "Audience must contain worldpay_pos");

        return claimsSet;
    }

    private void assertTrue(boolean check, String message) throws Exception {
        if (!check) {
            throw new Exception(message);
        }
    }

}
