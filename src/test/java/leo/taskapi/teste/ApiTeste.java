package leo.taskapi.teste;

import io.restassured.RestAssured;
import io.restassured.http.*;

import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

public class ApiTeste {
	
	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "http://localhost:8001/tasks-backend";
	}
	
	
	@Test
	public void deveRetornarTarefas() {
		RestAssured.given()
		.when()
		    .get("/todo")
		.then()
		.statusCode(200)
		;
		
	}

	@Test
	public void deveAdicionarTarefacomSucesso() {
		RestAssured.given()
			.body("{\"task\": \"Teste via API\", \"dueDate\": \"2023-12-30\" }")
			.contentType(ContentType.JSON)
		.when()
		    .post("/todo")
		.then()
		.statusCode(201)
		;	
		
	}
	
	@Test
	public void NãodeveAdicionarTarefainvalida() {
		RestAssured.given()
			.body("{\"task\": \"Teste via API\", \"dueDate\": \"2020-12-30\" }")
			.contentType(ContentType.JSON)
		.when()
		    .post("/todo")
		.then()
		.statusCode(400)
		.body("message", CoreMatchers.is("Due date must not be in past"))
		;
}

}
