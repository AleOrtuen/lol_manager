package lol_manager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lol_manager.dto.ChampPoolDTO;
import lol_manager.model.ChampPool;
import lol_manager.service.ChampPoolService;

@RestController
@RequestMapping("/champ-pool")
@CrossOrigin
public class ChampPoolController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ChampPoolController.class);
	
	@Autowired 
	private ChampPoolService champPoolService;
	
	@PostMapping("/save")
	public ResponseEntity<ChampPoolDTO> save(@RequestBody ChampPool c) {
		ChampPoolDTO response = new ChampPoolDTO();
		try {
			response.setObjResponse(champPoolService.save(c));
			response.setResponse("Champion in pool saved");
			return ResponseEntity.status(HttpStatus.OK).body(response);			
		} catch (IllegalArgumentException i) {
	    	LOGGER.error(i.getMessage(), i);
	    	response.setResponse(i.getMessage());
	    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);	    	
		}	catch (Exception e) {
	    	LOGGER.error(e.getMessage(), e);
			response.setResponse(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
}
