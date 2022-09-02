package com.onpassive.odesk.gof.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.onpassive.odesk.dto.PaginationResponseDto;
import com.onpassive.odesk.gof.dto.GofounderHelperDto;
import com.onpassive.odesk.gof.dto.GofounderListDto;
import com.onpassive.odesk.gof.service.GoFounderService;
import com.onpassive.odesk.mysql.gof.service.impl.GofounderMyServiceImpl;

import io.jsonwebtoken.Claims;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = { GofoundersListController.class })
@AutoConfigureMockMvc
@ContextConfiguration(classes = GofoundersListController.class)
public class GofoundersListControllerTest {

	// Dependencies
	//	@MockBean
	//	GofounderMyService gofounderMyService;

	@MockBean
	GofounderMyServiceImpl gofounderMyService;

	
	@MockBean
	GoFounderService goFounderService;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	@Autowired
	ObjectMapper objectMapper;

	@Mock
	Claims claims;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	

	@Test
	public void getAllGofunderusersPositiveTest() throws Exception {

		// Prepare Parameters
		GofounderHelperDto gofounderHelperDto = new GofounderHelperDto(1, "Active", 1, 10, "", false);
		gofounderHelperDto.setEmail("demo@gmail.com");
		
		// Note: GofounderHelperDto must contain equals method override.
		when(gofounderMyService.getAllGofunderDetails(gofounderHelperDto)).thenReturn(new PaginationResponseDto());
		
		// Preparing Request Body
		ObjectWriter writer = objectMapper.writer().withDefaultPrettyPrinter();
		String json = writer.writeValueAsString(gofounderHelperDto);
		
		// Create Mock Response
		var mockRequest = MockMvcRequestBuilders.post("/api/getAllGofunderusers")
				.content(json).contentType(MediaType.APPLICATION_JSON);
		
		var response = mockMvc.perform(mockRequest).andReturn().getResponse();

		assertEquals(200, response.getStatus());
	}

	
	@Test
	public void getAllGofunderusersNegitiveTest() throws Exception {

		// Prepare Parameters
		GofounderHelperDto gofounderHelperDto = new GofounderHelperDto();
	
		when(gofounderMyService.getAllGofunderDetails(Mockito.any())).thenReturn(null);

		// Preparing Request body
		ObjectWriter writer = objectMapper.writer().withDefaultPrettyPrinter();
		String json = writer.writeValueAsString(gofounderHelperDto);

		// Create Mock Response
		var mockRequest = MockMvcRequestBuilders.post("/api/getAllGofunderusers").accept(MediaType.APPLICATION_JSON)
				.content(json).contentType(MediaType.APPLICATION_JSON);

		var response = mockMvc.perform(mockRequest).andReturn().getResponse();

		assertEquals(204, response.getStatus());
	}
	
	@Test
	public void getGoFounderMailIdPositiveTest() throws Exception {

		// Request Body
		GofounderHelperDto gofounderHelperDto = new GofounderHelperDto(1, "", 10, 1, "", false);
		gofounderHelperDto.setEmail("email");
		
		ObjectWriter writer = objectMapper.writer().withDefaultPrettyPrinter();
		String requestBody = writer.writeValueAsString(gofounderHelperDto);

		// Mocking Service method
		when(gofounderMyService.getByMailId("email", 1, 10)).thenReturn(new PaginationResponseDto());

		// Create Mock Response
		var mockRequest = MockMvcRequestBuilders.post("/api//GofoundergetByMailId").accept(MediaType.APPLICATION_JSON)
				.content(requestBody).contentType(MediaType.APPLICATION_JSON);
		
		var response = mockMvc.perform(mockRequest).andReturn().getResponse();

		assertEquals(200, response.getStatus());
	}

	@Test
	public void getGoFounderMailIdNegitiveTest() throws Exception {

		// Request Body
		GofounderHelperDto gofounderHelperDto = new GofounderHelperDto(1, "Active", 1, 10, "", false);
		ObjectWriter writer = objectMapper.writer().withDefaultPrettyPrinter();
		String requestBody = writer.writeValueAsString(gofounderHelperDto);

		// Mocking Service method
		when(gofounderMyService.getByMailId("email", 1, 10)).thenReturn(null);

		// Create Mock Response
		var mockRequest = MockMvcRequestBuilders.post("/api//GofoundergetByMailId").accept(MediaType.APPLICATION_JSON)
				.content(requestBody).contentType(MediaType.APPLICATION_JSON);

		var response = mockMvc.perform(mockRequest).andReturn().getResponse();

		assertEquals(204, response.getStatus());
	}
	
	@Test
	public void getGoFounderMailIdIsNull() throws Exception {

		// Request Body
		GofounderHelperDto gofounderHelperDto = new GofounderHelperDto(1, "Active", 1, 10, "", false);
		gofounderHelperDto.setEmail(null);
		
		ObjectWriter writer = objectMapper.writer().withDefaultPrettyPrinter();
		String requestBody = writer.writeValueAsString(gofounderHelperDto);

		// Mocking Service method
		when(gofounderMyService.getByMailId(null, 1, 10)).thenReturn(null);

		// Create Mock Response
		var mockRequest = MockMvcRequestBuilders.post("/api//GofoundergetByMailId").accept(MediaType.APPLICATION_JSON)
				.content(requestBody).contentType(MediaType.APPLICATION_JSON);

		var response = mockMvc.perform(mockRequest).andReturn().getResponse();

		assertEquals(204, response.getStatus());
	}

	
		@Test
		public void getGoFounderById() throws Exception {

			// RequestBody Preparation
			GofounderHelperDto gofounderHelperDto = new GofounderHelperDto(1, "Active", 1, 10, "", false);
			gofounderHelperDto.setEmail("demo@onpassive.com");
			
			ObjectWriter writer = objectMapper.writer().withDefaultPrettyPrinter();
			String requestBody = writer.writeValueAsString(gofounderHelperDto);
			
			// Mocking Service method
			when( gofounderMyService.getByGofunderId("demo@onpassive.com")).thenReturn(new GofounderListDto());
			
			// Create Mock Response
			var mockRequest = MockMvcRequestBuilders.post("/api/GofoundergetById").accept(MediaType.APPLICATION_JSON)
					.content(requestBody).contentType(MediaType.APPLICATION_JSON);

			var response = mockMvc.perform(mockRequest).andReturn().getResponse();

			assertEquals(200, response.getStatus());
			
		}

		
}