package nl.dijkrosoft.jwt;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JwtMacCalculatorTest {

    @ParameterizedTest
    @MethodSource("provideParameters")
    void calculateJwtMac(String header, String payload, String secret, String expectedHash) {

        assertEquals(expectedHash, JwtMacCalculator.calculateJwtMac(header, payload, secret));
    }

    private static Stream<String[]> provideParameters() {
        return Stream.of(
                new String[]{"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9", "eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9", "secret", "TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ"},
                new String[]{"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCIsImlzcyI6ImphcGllIn0", "eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ", "dd", "o_XvcZEPUtn8_zuP8CFoWTe5weyccBAfSuwycdd31cU"}
        );
    }

}