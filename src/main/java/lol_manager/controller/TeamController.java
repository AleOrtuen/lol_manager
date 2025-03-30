package lol_manager.controller;

import java.util.ArrayList;
import java.util.List;

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

import lol_manager.dto.ChampRoleDTO;
import lol_manager.dto.RequestDTO;
import lol_manager.dto.ResponseDTO;
import lol_manager.dto.TeamCompDTO;
import lol_manager.dto.TeamDTO;
import lol_manager.service.ChampRoleService;
import lol_manager.service.TeamCompService;
import lol_manager.service.TeamService;

@RestController
@RequestMapping("/team")
@CrossOrigin
public class TeamController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TeamController.class);
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private ChampRoleService champRoleService;
	
	@Autowired
	private TeamCompService teamCompService;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseDTO> save(@RequestBody TeamDTO t) {
		ResponseDTO response = new ResponseDTO();
		try {
			response.setObjResponse(teamService.save(t));
			response.setResponse("Team saved");
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
	public ResponseEntity<ResponseDTO> update(@RequestBody TeamDTO t) {
		ResponseDTO response = new ResponseDTO();
		try {
			response.setObjResponse(teamService.update(t));
			response.setResponse("Team updated");
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
	public ResponseEntity<ResponseDTO> delete(@PathVariable Long id) {
		ResponseDTO response = new ResponseDTO();
		try {
			teamService.delete(id);
			response.setResponse("Team deleted");
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
			response.setObjResponse(teamService.findAll());
			response.setResponse("Teams found");
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
	public ResponseEntity<ResponseDTO> findById(@PathVariable Long id) {
		ResponseDTO response = new ResponseDTO();
		try {
			response.setObjResponse(teamService.findById(id));
			response.setResponse("Team found");
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
	
	@GetMapping("/find-name/{name}")
	public ResponseEntity<ResponseDTO> findByName(@PathVariable String name) {
		ResponseDTO response = new ResponseDTO();
		try {
			response.setObjResponse(teamService.findByName(name));
			response.setResponse("Team found");
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
	
	@GetMapping("/find-tag/{tag}")
	public ResponseEntity<ResponseDTO> findByTag(@PathVariable String tag) {
		ResponseDTO response = new ResponseDTO();
		try {
			response.setObjResponse(teamService.findByTag(tag));
			response.setResponse("Team found");
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
	
	@GetMapping("/find-champs/{id}") 
	public ResponseEntity<ResponseDTO> findChampions(@PathVariable Long id) {
		ResponseDTO response = new ResponseDTO();
		try {
			response.setObjResponse(teamService.findChampions(id));
			response.setResponse("Champions found");
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
	
	@GetMapping("/champs-comps/{id}")
	public ResponseEntity<ResponseDTO> findChampsInComps(@PathVariable Long id) {
		ResponseDTO response = new ResponseDTO();
		try {
			List<TeamCompDTO> comps = teamCompService.findByTeam(id);
			List<ChampRoleDTO> roles = new ArrayList<>();
			for (TeamCompDTO comp : comps) {
				roles.addAll(champRoleService.findByIdComp(comp.getIdComp()));
			}
			response.setObjResponse(roles);
			response.setResponse("Champions in all comps found");
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
	
	@PostMapping("/combinator")
	public ResponseEntity<ResponseDTO> combinator(@RequestBody RequestDTO request) {
		ResponseDTO response = new ResponseDTO();
		try {
			List<ChampRoleDTO> combined = teamService.compCombinator(request.getOldList(), request.getNewList());
			List<ChampRoleDTO> availableChamps = champRoleService.findAllCompatible(combined); 
			response.setObjResponse(combined);
			response.setObjResponse2(availableChamps);
			response.setResponse("Combined; Compatible champs found");
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
