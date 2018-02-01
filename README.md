# JWT Encryption and Signing Example

This project contains 2 simple libraries:
* `jwt-generator` for generating a signed and encrypted JWTs
* `jwt-verifier` for verifying the signature, encryption and data from a signed and encrypted JWT

In addition, it contains 2 simple webapps that use these libraries:
* `jwt-generator-webapp` which exposes a REST API to generate a JWT with the `jwt-generator` library
* `jwt-verifier-webapp` which exposes a REST API to verify a JWT with the `jwt-verifier` library

## Building

To build the entire project, just run a standard mvn build command, e.g.

```
mvn clean install
```

## Running

The `*-webapp` modules Spring Boot deployable webapps. The easiest way to run is used the Spring Boot maven commands:

```
mvn spring-boot:run -f jwt-generator-webapp
mvn spring-boot:run -f jwt-verifier-webapp
```

## Using

Once both webapps are running, you can generate a JWT by invoking the following URL to hit `jwt-generator-webapp`:

`http://localhost:8880/login?username=<username>`

This will return a response like:

```
{
  jwt: "eyJjdHkiOiJKV1QiLCJlbmMiOiJBMjU2R0NNIiwiYWxnIjoiUlNBLU9BRVAtMjU2In0.bzWxsUNcyk8TGbxKr2LzH8x_IJMS83Zciu90fAGIILTSjbcVQkz5gE9YwCVi2b_y1AYuwgz3QVfx7RMwE7nMXI3DR-xG9WGMGwd1jHqbyJ5YrhDyxcnCmhmNTXih-MMj8MyMC3mKk7qd7koSejzHLtjc2uGBsarAItYJBQ04igz8RKc33NRIyNTDprNSFSfABRXwyx8_vLuex7oNaqAFqSqiISpM0Nhw0W_CBC5JNwaDXugFuTRyzGaYf0KgPnRx6DUmK7sIGMu-Th2GcvrsxV3-9WV7zaep25ppllhoNTlz2zEAAfQn1QcduvMGfW6nNfpwXFzuMj9AQum1_O1NiFGO8W5s94VKZfyIlZZGeJMBaR_zXlO5fVIXlH2z18o8Aftkd4B5bPGFrBLxg0wq7PiRR3Grdswgrfx82vDyib_z9Qs6iSnP-Cb9dXL9c5fZ9UUc0MoZ3gToVwAOSY3pu-O0EWK1wp3qbf7kCSRf7pTA0JdVL4eIjSmlioO_91zdG5ftRVZgoVZCAyhJRDDm_hXHAnG8ej9rVAYonvi2AcvIOuZBuq4Ip-X_gr3KgBFtdz_grF46I9LSjt70bw0NHwuYz4e82LpV5JEIfh0Vg1zKG2B-GCD6isR4r3CQODHbdh8MHFgGm1xeUDdh8e0g0IFqSBPSPIYb4gAys3Nc8zc.-IHAJjUn5eD0F4IX.62OC8xQ3pNnUeUPTVnCER7GBVKJkd6cZhyleYz9Vt1I75aWvhwI1hFCMPL6o_wPyZrzFIIjeOjeYFkvE8X5iXw_TL2uuQ7rPzumvbd8IhGFxhhFJP0ha2w_YqK5_sQ475ULdLouYmtkmeWqIFo_UIrkEGAe_YACWxk5rmXUYsrmhy9uSF4iMTUOQhZ1ua32-q4GM2Kmw5cWsXJEO5lw3r4wkSlf4YAyfO3m37wnZuMWZcySRart1sKuFR-rORdnbs24AEf_BB1Qjy_psH5kcdjltUreghWCcZ6V8QpWEVO6kMDkjNTtLu_4MBqwTDl5bdUw1t2cIBeFzee4ex0DOestjPJOPoM1LKIGIV2B6dJpaCUWoE3al8AAnNpx9PznPq9U7vgnDapIUdAiT5DFz9A80mEtV_9hQM1Y5c_vDmaBzpXr9nE83HYCJ-rcSVk_m-z1j9nTbXhqnRZyefo5HnLOrZ2AOC7NAa95zK_ewvM2NUvIrAkp8NtZNUwuw5p8k_XwdLO8n3ygAZG7vRpi7u801Eokrp94oQnipztQZd3JHBdBm8bt3gIdFsmcI54kso6NiMsa0qlWKNHCHzUibdUbSiZ7C94brcopeJLWDKTpAll3omPSi9NGJxl7whtzhZP7XYig4SbbMj5deXVpSb1nNnpQCbQiStRy5b0AACb9SY0CFFA71MaMT1PRFzozq0MziRtp3OfgoBHxnu_Y_SdEOSDWnkr9Of1LypYLBh5qEOjIMiOPWGZ1SmmduOkM7uUCk0ZM6ObQO_Qg0xoTxlIxigmRDatpi-M9Bk97ZUjWWQDiAHVGiMJHInwBjatJl8pR5C3o_MKoWLalr1fUJoELCLEj_y3gSqaca3IaActRDt43vU_87bfHlQqLnnEQgd27ecwF93xvVhda1kUt--QRm_96nLMOOTxlhRmbNh-F4EUHDKWbCAsxILGJWNfUP0haloqAXgLzYmiFyWLHtrp7U9QpKpPR3xI6FDPprN6hvfzSwOAAjMkBe8C10Bhax3USgSUj11yqhaGMRt1SAEw08yW9W8FtBnTKxub71byQPjmu_jYL9FDNum4ouLH-W1HnqBr1i9LzwjbWCTaqUWOdMExOmBs-Mv1B1WTnHX_iaJeGj9J1rfOdwRBh4UWJzE87MYYPJyQ837pMetm70C0O-wX-7IWNrsyWmukL6IZTH-uskIaPxT-qxqXWziZ2aTKagMTG63AZfN_sk4Ip5.dmgmswl0tafMIRIHpFgjXQ"
}
```

The returned JWT value can then be passed in the following URL to hit `jwt-verifier-webapp`:

`http://localhost:8881/login?jwt=<jwt>`

This will return the username from the JWT which you provided in the original request for a JWT, such as:

```
{
  name: "john"
}
```

## RSA Keys

These examples use RSA keys to encrypt and sign the JWT, the project contains some default RSA key pairs for each, but to generate new keys then run the `re-generate-keys.sh` script.

```
sh re-generate-keys.sh
```

This will generate new keys and replace the old keys in the projects. To use the new keys, re-build and deploy the `*-webapp` projects.
