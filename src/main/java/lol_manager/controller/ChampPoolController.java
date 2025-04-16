package lol_manager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lol_manager.dto.ChampPoolDTO;
import lol_manager.dto.ResponseDTO;
import lol_manager.service.ChampPoolService;

@RestController
@RequestMapping("/champ-pool")
@CrossOrigin
public class ChampPoolController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ChampPoolController.class);
	
	@Autowired 
	private ChampPoolService champPoolService;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseDTO> save(@RequestBody ChampPoolDTO c) {
		ResponseDTO response = new ResponseDTO();
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
	
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDTO> delete(@RequestParam Long idUser, @RequestParam Long idChamp) {
		ResponseDTO response = new ResponseDTO();
		try {
			champPoolService.delete(idUser, idChamp);
			response.setResponse("Champion pool deleted");
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
	
	@GetMapping("/find-id")
	public ResponseEntity<ResponseDTO> findById(@RequestParam Long idUser, @RequestParam Long idChamp) {
		ResponseDTO response = new ResponseDTO();
		try {
			response.setObjResponse(champPoolService.findById(idUser, idChamp));
			response.setResponse("Champion pool found");
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
	
	@GetMapping("/find-user/{id}")
	public ResponseEntity<ResponseDTO> findByIdUser(@PathVariable Long id) {
		ResponseDTO response = new ResponseDTO();
		try {
			response.setObjResponse(champPoolService.findByIdUser(id));
			response.setResponse("Champion pools found");
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
	
	@GetMapping("/find-champ/{id}")
	public ResponseEntity<ResponseDTO> findByIdChamp(@PathVariable Long id) {
		ResponseDTO response = new ResponseDTO();
		try {
			response.setObjResponse(champPoolService.findByIdChamp(id));
			response.setResponse("Champion pools found");
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
	
	@GetMapping("/find-all")
	public ResponseEntity<ResponseDTO> findAll() {
		ResponseDTO response = new ResponseDTO();
		try {
			response.setObjResponse(champPoolService.findAll());
			response.setResponse("Champion pools found");
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
