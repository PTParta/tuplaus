package tuplaus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import tuplaus.Pelitapahtuma.PelitapahtumaController;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

//@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class TuplausApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void statusOk() throws Exception {
		mockMvc.perform(get("/pelaajat"))
				.andExpect(status().isOk());
	}

	/* @Test
	public void tuplausOk() throws Exception {
		mockMvc.perform(post("/tuplaus")
		.contentType(MediaType.APPLICATION_JSON)
		.content("{"tunniste":"kalle123", }"))
		)
		
		//.andExpect(status().isOk());
	} */

	@Test
	void contextLoads() {

	}

}
