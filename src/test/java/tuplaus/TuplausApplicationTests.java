package tuplaus;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import jakarta.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class TuplausApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	/**
	 * 
	 * Tehty vain integraatiotestejä.
	 * 
	 * TODO:
	 * 
	 * -Testit toisistaan riippumattomiksi. Nyt tietokantamuutokset yhdessä testissä
	 * vaikuttaa muihin testeihin.
	 * -Yksikkötestit tuplauspelin logiikalle
	 * -Yksikkötestit use caseille pelaajan lisäys, tuplaus ja kotiuta voitot
	 */
	
	@Test
	public void pelaajatStatusOk() throws Exception {
		mockMvc.perform(get("/pelaajat"))
				.andExpect(status().isOk());
	}

	@Test
	public void tuplausOk() throws Exception {

		String jsonPelaaja = "{\"tunniste\":\"kalle123\",\"nimi\":\"Kalle Päätalo\",\"saldo\":100}";
		String jsonTuplaus = "{\"tunniste\":\"kalle123\",\"panos\":5,\"valinta\":\"suuri\",\"onEnsimmainenKierros\":true}";

		mockMvc.perform(post("/pelaaja")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonPelaaja)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()); // .isCreated() olisi varmaan parempi?

		String response = mockMvc.perform(post("/tuplaus")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonTuplaus)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()) // .isCreated() olisi varmaan parempi?
				.andReturn()
				.getResponse()
				.getContentAsString();

		assertTrue(response.contains("arvottuKortti"));
		assertTrue(response.contains("voitto"));
		assertTrue(response.contains("mahdollisenVoitonSuuruus"));
		assertTrue(response.contains("pelitilinSaldo"));
	}

	@Test
	public void virheKunSaldoEiRiita() throws Exception {

		String jsonPelaaja = "{\"tunniste\":\"mikko456\",\"nimi\":\"Mikko Mäki\",\"saldo\":100}";
		String jsonTuplaus = "{\"tunniste\":\"mikko456\",\"panos\":105,\"valinta\":\"suuri\",\"onEnsimmainenKierros\":true}";

		mockMvc.perform(post("/pelaaja")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonPelaaja)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()); // .isCreated() olisi varmaan parempi?

		mockMvc.perform(post("/tuplaus")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonTuplaus)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(status().reason("Saldo ei riitä"));
	}

	@Test
	public void kotiutusOk() throws Exception {

		String jsonPelaaja = "{\"tunniste\":\"pekka789\",\"nimi\":\"Pekka Niemi\",\"saldo\":100}";
		String jsonKotiutaVoitot = "{\"tunniste\":\"pekka789\",\"voitonSuuruus\":10}";

		mockMvc.perform(post("/pelaaja")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonPelaaja)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()); // .isCreated() olisi varmaan parempi?

		String response = mockMvc.perform(post("/kotiutavoitot")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonKotiutaVoitot)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		assertTrue(response.contains("Voitot kotiutettu"));
	}

	

}
