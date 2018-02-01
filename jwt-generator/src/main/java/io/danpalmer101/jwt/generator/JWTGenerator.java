package io.danpalmer101.jwt.generator;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.util.io.pem.PemObject;

import java.io.Reader;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class JWTGenerator {

    private final PublicKey encryptionKey;
    private final PrivateKey signingKey;

    public JWTGenerator(Reader signingKeyReader, Reader encryptionKeyReader) throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        KeyFactory factory = KeyFactory.getInstance("RSA", "BC");

        // Read private signing key
        PemObject signingKeyObject = new PEMParser(signingKeyReader).readPemObject();
        byte[] signingPrivatePem = signingKeyObject.getContent();
        PKCS8EncodedKeySpec signingKeySpec = new PKCS8EncodedKeySpec(signingPrivatePem);
        this.signingKey = factory.generatePrivate(signingKeySpec);

        // Read public encryption key
        PemObject encryptionKeyObject = new PEMParser(encryptionKeyReader).readPemObject();
        byte[] encryptionPublicPem = encryptionKeyObject.getContent();
        X509EncodedKeySpec encryptionKeySpec = new X509EncodedKeySpec(encryptionPublicPem);
        this.encryptionKey = factory.generatePublic(encryptionKeySpec);
    }

    public JOSEObject generateJwt(JWTClaimsSet claimsSet) throws Exception {

        // Sign JWT

        RSAPrivateKey privateKey = (RSAPrivateKey)this.signingKey;

        JWSSigner signer = new RSASSASigner(privateKey);

        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader.Builder(JWSAlgorithm.RS256).build(),
                claimsSet);

        signedJWT.sign(signer);

        // Encrypt JWT

        RSAPublicKey publicKey = (RSAPublicKey)this.encryptionKey;

        JWEObject encryptedJwt = new JWEObject(
                new JWEHeader.Builder(JWEAlgorithm.RSA_OAEP_256, EncryptionMethod.A256GCM)
                        .contentType("JWT")
                        .build(),
                new Payload(signedJWT));

        encryptedJwt.encrypt(new RSAEncrypter(publicKey));

        return encryptedJwt;
    }

}
