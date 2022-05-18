package com.co.indra.coinmarketcap.portafolio.controllers;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.co.indra.coinmarketcap.portafolio.config.Routes;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AssetControllerTest {

	@Autowired
	private MockMvc mockMvc;


	@Test
	@Sql("/testdata/insert_assets.sql")
	public void deleteAssets() throws Exception {
		// ----la ejecucion de la prueba misma--------------
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(Routes.ASSETS_PATH + Routes.DELETE_ASSETS,
				2);

		MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
		// ------------ las verificaciones--------------------
		Assertions.assertEquals(200, response.getStatus());

	}

}
