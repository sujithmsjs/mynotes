package com.onpassive.odesk.controller.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.onpassive.odesk.controller.TicketAssignController;
import com.onpassive.odesk.controller.TicketController;
import com.onpassive.odesk.security.JwtTokenUtil;
import com.onpassive.odesk.service.EmployeeService;
import com.onpassive.odesk.service.TicketAssignService;
import com.onpassive.odesk.service.TicketService;

import io.jsonwebtoken.Claims;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = { TicketAssignController.class })
@AutoConfigureMockMvc
@ContextConfiguration(classes = TicketAssignController.class)
public class TicketAssignControllerTest {

	static final String CLASS_NAME = "TicketAssignController";
	String methodName = "";
	Logger logger = LoggerFactory.getLogger(TicketController.class);

	@Autowired
	MockMvc mockMvc;

	@MockBean
	TicketService ticketserviceimpl;

	@MockBean
	TicketAssignService ticketAssignService;

	@MockBean
	EmployeeService employeeService;

	@MockBean
	private JwtTokenUtil jwtTokenUtil;

	@Mock
	Claims claims;

	@Value("${jwt.secret}")
	private String jwtSecret;
	String mongoId;

	@Autowired
	WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void getTicketHistoryByCaseIdTest() throws Exception {

		// Preparing Return Type
		HashMap<String, Object> map = new HashMap<>();
		map.put("id", "DemoId");
		ResponseEntity<HashMap<String, Object>> res = new ResponseEntity<HashMap<String, Object>>(map,
				HttpStatus.ACCEPTED);

		// Mock Behavior
		when(ticketAssignService.getTicketHistoryByCaseId("1")).thenReturn(res);

		// Create Mock Response
		var mockRequest = MockMvcRequestBuilders.get("/api/gethistoryforTicket/1");
		var response = mockMvc.perform(mockRequest).andReturn().getResponse();

		// Asserting
		assertEquals(200, response.getStatus());
	}

}
