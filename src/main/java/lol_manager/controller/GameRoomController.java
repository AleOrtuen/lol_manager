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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lol_manager.dto.GameRoomDTO;
import lol_manager.dto.ResponseDTO;
import lol_manager.service.GameRoomService;

@RestController
@RequestMapping("/game-room")
@CrossOrigin
public class GameRoomController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GameRoomController.class);
	
	@Autowired
	private GameRoomService gameRoomService;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseDTO> save(@RequestBody GameRoomDTO gameRoomDto) {
		ResponseDTO response = new ResponseDTO();
		try {
			response.setObjResponse(gameRoomService.save(gameRoomDto));
			response.setResponse("Room created");
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
	
	@PutMapping("/update")
	public ResponseEntity<ResponseDTO> update(@RequestBody GameRoomDTO gameRoomDto) {
		ResponseDTO response = new ResponseDTO();
		try {
			response.setObjResponse(gameRoomService.update(gameRoomDto));
			response.setResponse("Room updated");
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
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseDTO> delete(@PathVariable String id) {
		ResponseDTO response = new ResponseDTO();
		try {
			gameRoomService.delete(id);
			response.setResponse("Room deleted");
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
			response.setObjResponse(gameRoomService.findAll());
			response.setResponse("Rooms found");
			return ResponseEntity.status(HttpStatus.OK).body(response);			
		} catch (IllegalArgumentException i) {
	    	LOGGER.error(i.getMessage(), i);
	    	response.setResponse(i.getMessage());
	    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);	    	
		} catch (Exception e) {
	    	LOGGER.error(e.getMessage(), e);
			response.setResponse(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@GetMapping("/find-id/{id}")
	public ResponseEntity<ResponseDTO> findById(@PathVariable String id) {
		ResponseDTO response = new ResponseDTO();
		try {
			response.setObjResponse(gameRoomService.findById(id));
			response.setResponse("Room found");
			return ResponseEntity.status(HttpStatus.OK).body(response);			
		} catch (IllegalArgumentException i) {
	    	LOGGER.error(i.getMessage(), i);
	    	response.setResponse(i.getMessage());
	    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);	    	
		} catch (Exception e) {
	    	LOGGER.error(e.getMessage(), e);
			response.setResponse(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@GetMapping("/find-game/{id}")
	public ResponseEntity<ResponseDTO> findById(@PathVariable Long id) {
		ResponseDTO response = new ResponseDTO();
		try {
			response.setObjResponse(gameRoomService.findByIdGame(id));
			response.setResponse("Room found");
			return ResponseEntity.status(HttpStatus.OK).body(response);			
		} catch (IllegalArgumentException i) {
	    	LOGGER.error(i.getMessage(), i);
	    	response.setResponse(i.getMessage());
	    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);	    	
		} catch (Exception e) {
	    	LOGGER.error(e.getMessage(), e);
			response.setResponse(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
}
