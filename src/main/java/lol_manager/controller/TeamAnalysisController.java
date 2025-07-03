package lol_manager.controller;

import lol_manager.dto.ResponseDTO;
import lol_manager.service.TeamAnalysisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/team-analysis")
@CrossOrigin
public class TeamAnalysisController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamAnalysisController.class);

    @Autowired
    private TeamAnalysisService teamAnalysisService;

    @GetMapping("/find-team/{id}")
    public ResponseEntity<ResponseDTO> findByIdTeam(@PathVariable Long id) {
        ResponseDTO response = new ResponseDTO();
        try {
            response.setObjResponse(teamAnalysisService.getTeamAnalysis(id));
            response.setResponse("Analysis done");
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
