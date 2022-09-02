package com.onpassive.odesk.gof.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onpassive.odesk.dto.PaginationResponseDto;
import com.onpassive.odesk.gof.dto.GofounderHelperDto;
import com.onpassive.odesk.gof.dto.GofounderListDto;
import com.onpassive.odesk.gof.dto.GofounderMailDto;
import com.onpassive.odesk.gof.service.GoFounderService;
import com.onpassive.odesk.mysql.gof.service.GofounderMyService;
import com.onpassive.odesk.mysql.gof.service.impl.GofounderMyServiceImpl;
import com.onpassive.odesk.utility.Constants;
import com.onpassive.odesk.utility.Response;
import com.onpassive.odesk.utility.WebUrlConstants;

@RestController
@RequestMapping(value = WebUrlConstants.BASE_URL)
@CrossOrigin(origins = "*")
public class GofoundersListController {

	// Dependencies
	@Autowired
	private GofounderMyService gofounderMyService;
	@Autowired
	private GoFounderService goFounderService;

	@Autowired
	private GofounderMyServiceImpl gofounderMyServiceImpl;

	static final String CLASS_NAME = "GoFounderController";
	String methodName = "";
	Logger logger = LoggerFactory.getLogger(GofoundersListController.class);

	// GET All the GO Founder Users  using
	@PostMapping(value = WebUrlConstants.GET_ALL_GOFOUNDERS_DETAILS)
	public ResponseEntity<Response> getAllGofunderusers(@RequestBody GofounderHelperDto gofounderHelperDto) {
		methodName = "getAllGofunderusers";
		logger.info(CLASS_NAME, methodName, " ::  started");

		PaginationResponseDto details = gofounderMyService.getAllGofunderDetails(gofounderHelperDto);

		logger.info(CLASS_NAME, methodName, " ::  ended");
		if (details != null) {
			return new ResponseEntity<>(new Response(Constants.SUCCESS, details, HttpStatus.OK.value()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new Response(Constants.NO_DATA, null, HttpStatus.NO_CONTENT.value()),
					HttpStatus.NO_CONTENT);
		}
	}
 
	// GET All Countries and Regions
	@GetMapping(value = WebUrlConstants.GET_ALL_COUNTRIES_AND_REGIONS)
	public ResponseEntity<Response> getAllCountriesAndRegions() {

		methodName = "getAllCountries";

		logger.info(CLASS_NAME, methodName, " ::  started");

		List<GofounderMailDto> regionsList = goFounderService.getAllCountriesAndRegions();

		if (regionsList != null) {

			logger.info(CLASS_NAME, methodName, " ::  ended");

			return new ResponseEntity<>(new Response(Constants.SUCCESS, regionsList, HttpStatus.OK.value()),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new Response(Constants.NO_DATA, regionsList, HttpStatus.NO_CONTENT.value()),
					HttpStatus.OK);
		}
	}

	// GET GO FOUNDER By their ID
	@PostMapping(value = WebUrlConstants.GET_GOFOUNDER_BY_ID)
	public ResponseEntity<Response> getGoFounderById(@RequestBody GofounderHelperDto gofounderHelperDto) {

		methodName = "getGoFounderById";
		logger.info(CLASS_NAME, methodName, " ::  started");

		GofounderListDto goId = null;
		if (gofounderHelperDto.getEmail() == null) {
			return new ResponseEntity<>(
					new Response("Payload must have email property.", null, HttpStatus.NO_CONTENT.value()),
					HttpStatus.NO_CONTENT);
		}else {
			goId = gofounderMyService.getByGofunderId(gofounderHelperDto.getEmail());
		}
		
		if (goId != null) {
			logger.info(CLASS_NAME, methodName, " ::  ended");
			return new ResponseEntity<>(new Response(Constants.SUCCESS, goId, HttpStatus.OK.value()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new Response(Constants.NO_DATA, goId, HttpStatus.NO_CONTENT.value()),
					HttpStatus.NO_CONTENT);
		}
	}

	// GET GO FOUNDER BY Their Email ID or UserID.   using 
	@PostMapping(value = WebUrlConstants.GET_GOFOUNDER_BY_MAILID)
	public ResponseEntity<Response> getGoFounderMailId(@RequestBody GofounderHelperDto gofounderHelperDto) {
		methodName = "getGoFounderMailId";
		logger.info(CLASS_NAME, methodName, " ::  started");

		Integer pageNumber = Optional.ofNullable(gofounderHelperDto).map(d -> d.getPageNumber()).orElse(0);
		Integer pageSize = Optional.ofNullable(gofounderHelperDto).map(d -> d.getPageSize()).orElse(10);

		PaginationResponseDto details = gofounderMyService.getByMailId(gofounderHelperDto.getEmail(), pageNumber,
				pageSize);

		if (details != null) {
			logger.info(CLASS_NAME, methodName, " ::  ended");
			return new ResponseEntity<>(new Response(Constants.SUCCESS, details, HttpStatus.OK.value()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new Response(Constants.NO_DATA, null, HttpStatus.NO_CONTENT.value()),
					HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping(value = WebUrlConstants.GO_FOUNDER_GET_BY_FLAG)
	public ResponseEntity<Response> getGofounderByFalg(@RequestBody GofounderHelperDto gofounderHelperDto) {
		methodName = "GofoundergetByFlag";
		logger.info(CLASS_NAME, methodName, " ::  started");
		GofounderListDto goId = null;
		try {
			goId = gofounderMyServiceImpl.goUpdatedFlag(gofounderHelperDto);

			if (goId != null) {
				logger.info(CLASS_NAME, methodName, " ::  ended");
				return new ResponseEntity<>(new Response(Constants.SUCCESS, goId, HttpStatus.OK.value()),
						HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new Response(Constants.NO_DATA, goId, HttpStatus.NO_CONTENT.value()),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(new Response(Constants.NO_DATA,
					"You don't have the access ,Please contact admin.", HttpStatus.NO_CONTENT.value()),
					HttpStatus.BAD_REQUEST);
		}
	}

}
