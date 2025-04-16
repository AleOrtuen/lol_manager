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

import lol_manager.dto.ResponseDTO;
import lol_manager.dto.TeamMemberDTO;
import lol_manager.service.TeamMemberService;

@RestController
@RequestMapping("/team-member")
@CrossOrigin
public class TeamMemberController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TeamMemberController.class);	
	
	@Autowired
	private TeamMemberService teamMemberService;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseDTO> save(@RequestBody TeamMemberDTO t) {
		ResponseDTO response = new ResponseDTO();
		try {
			response.setObjResponse(teamMemberService.save(t));
			response.setResponse("Team member saved");
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
	public ResponseEntity<ResponseDTO> delete(@RequestParam Long idUser, @RequestParam Long idTeam) {
		ResponseDTO response = new ResponseDTO();
		try {
			teamMemberService.delete(idUser, idTeam);
			response.setResponse("Team member deleted");
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
	public ResponseEntity<ResponseDTO> findById(@RequestParam Long idUser, @RequestParam Long idTeam) {
		ResponseDTO response = new ResponseDTO();
		try {
			response.setObjResponse(teamMemberService.findById(idUser, idTeam));
			response.setResponse("Team member found");
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
			response.setObjResponse(teamMemberService.findByIdUser(id));
			response.setResponse("Team member found");
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
	
	@GetMapping("/find-team/{id}")
	public ResponseEntity<ResponseDTO> findByIdTeam(@PathVariable Long id) {
		ResponseDTO response = new ResponseDTO();
		try {
			response.setObjResponse(teamMemberService.findByIdTeam(id));
			response.setResponse("Team members found");
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
			response.setObjResponse(teamMemberService.findAll());
			response.setResponse("Team members found");
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
