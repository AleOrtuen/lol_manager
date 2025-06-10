package lol_manager.controller;

import lol_manager.dto.LeagueRoleDTO;
import lol_manager.dto.GameRoomDTO;
import lol_manager.dto.ResponseDTO;
import lol_manager.service.LeagueRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/league-role")
@CrossOrigin
public class LeagueRoleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LeagueRoleController.class);
    
    @Autowired
    private LeagueRoleService leagueRoleService;

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> save(@RequestBody LeagueRoleDTO leagueRoleDto) {
        ResponseDTO response = new ResponseDTO();
        try {
            LeagueRoleDTO leagueRole = leagueRoleService.save(leagueRoleDto);
            response.setObjResponse(leagueRole);
            response.setResponse("League role saved");
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
    public ResponseEntity<ResponseDTO> update(@RequestBody LeagueRoleDTO leagueRoleDto) {
        ResponseDTO response = new ResponseDTO();
        try {
            response.setObjResponse(leagueRoleService.update(leagueRoleDto));
            response.setResponse("League role updated");
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
            leagueRoleService.delete(id);
            response.setResponse("League role deleted");
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
            response.setObjResponse(leagueRoleService.findAll());
            response.setResponse("League roles found");
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
            response.setObjResponse(leagueRoleService.findById(id));
            response.setResponse("League role found");
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
