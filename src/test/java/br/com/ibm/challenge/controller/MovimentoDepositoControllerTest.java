package br.com.ibm.challenge.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class MovimentoDepositoControllerTest {

    @Test
    public void deveRetornarErrorAoSalvarSemInformarOsDados() {
        Map<String, String> params = new HashMap<>();

        RestAssured
                .given()
                    .request()
                    .header("Accept", ContentType.ANY)
                    .header("Content-type", ContentType.JSON)
                    .body(params)
                .when()
                    .post("/movimento/deposito")
                .then()
                    .log().body()
                    .and()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void deveRetornarDepositoAoSalvar() {
        Map<String, Object> params = new HashMap<>();
        params.put("valor", 100);
        params.put("depositoContaOrigemNumero", 100);
        params.put("depositoContaOrigemAgencia", 1);
        params.put("depositoTipoDeposito", "DINHEIRO");

        RestAssured
                .given()
                    .request()
                    .header("Accept", ContentType.ANY)
                    .header("Content-type", ContentType.JSON)
                    .body(params)
                .when()
                    .post("/movimento/deposito")
                .then()
                    .log().body()
                    .and()
                    .statusCode(HttpStatus.CREATED.value())
                    .and()
                    .body(
                            "tipoContabil", Matchers.equalTo("CREDITO"),
                            "tipoMovimento", Matchers.equalTo("DEPOSITO"),
                            "valor", Matchers.equalTo(100),
                            "depositoContaOrigemNumero", Matchers.equalTo(100),
                            "depositoContaOrigemAgencia", Matchers.equalTo(1),
                            "depositoTipoDeposito", Matchers.equalTo("DINHEIRO")
                    );
    }

}
