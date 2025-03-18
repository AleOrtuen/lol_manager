package lol_manager.utility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lol_manager.model.ChampRole;

public class ChampRoleUtility {
	
	
//	public static List<ChampRole> getValidOptions(List<ChampRole> currentTeam, List<ChampRole> allAvailableChamps) {
//	    // Implementazione esistente che restituisce EKKO(M) come opzione valida
//	    // ...
//	}
//
	public static List<ChampRole> updateTeamWithNewChamp(List<ChampRole> currentTeam, List<ChampRole> selectedChamp) {
	    List<ChampRole> updatedTeam = new ArrayList<>();
		
		for (ChampRole selectedRoleChamp : selectedChamp) {
			
			String selectedRole = selectedRoleChamp.getIdChampRole().getRole();
			
		    // Aggiungi solo i campioni che non causano duplicazioni di ruolo con il nuovo campione
		    for (ChampRole champ : currentTeam) {
		        Long champId = champ.getIdChampRole().getIdChamp();
		        String champRole = champ.getIdChampRole().getRole();
		        
		        // Se questo campione ha lo stesso ruolo del nuovo campione selezionato,
		        // assicuriamoci che non ci siano duplicazioni non necessarie
		        if (champRole.equals(selectedRole)) {
		            boolean keepChamp = true;
		            
		            // Verifica se il campione attuale è intercambiabile (ha altre opzioni di ruolo)
		            // Se è intercambiabile, possiamo considerare di rimuoverlo
		            for (ChampRole otherChamp : currentTeam) {
		                if (!otherChamp.equals(champ) && 
		                    otherChamp.getIdChampRole().getIdChamp() == champId &&
		                    !otherChamp.getIdChampRole().getRole().equals(champRole)) {
		                    keepChamp = false;
		                    break;
		                }
		            }
		            
		            if (keepChamp) {
		                updatedTeam.add(champ);
		            }
		        } else {
		            // Se il ruolo è diverso, manteniamo il campione
		            updatedTeam.add(champ);
		        }
		    }
			
		}	    

	    
	    // Aggiungi il nuovo campione selezionato
	    updatedTeam.addAll(selectedChamp);
	    
	    return updatedTeam;
	}
	
	public static List<ChampRole> filterComp(List<ChampRole> oldList, List<ChampRole> newChamps) {
	    List<ChampRole> result = new ArrayList<>();
	    
	    Set<Long> availableComps = new HashSet<>();
	    for (ChampRole newChamp : newChamps) {
	        availableComps.add(newChamp.getComp().getIdComp());
	    }
	    
	    for (ChampRole oldChamp : oldList) {
	        if (availableComps.contains(oldChamp.getComp().getIdComp())) {
	            result.add(oldChamp);
	        }
	    }
	    
	    result.addAll(newChamps);
	    
	    return result;
	}

//	public static List<ChampRole> filterComp(List<ChampRole> oldList, List<ChampRole> champRoles) {
//		List<ChampRole> newList = new ArrayList<>();
//
//		if (!oldList.isEmpty()) {
//			for (ChampRole role1 : oldList) {
//				for (ChampRole role2 : champRoles) {
//					if (role1.getComp().getIdComp() == role2.getComp().getIdComp()) {
//						newList.add(role1);
//						break;
//					}
//				}
//			}
//		}
//		newList.addAll(champRoles);
//
//		return newList;
//	}
	
	
//	public static List<ChampRole> filterRole(List<ChampRole> oldList, List<ChampRole> champRoles) {
//		List<ChampRole> noNewRoles = oldList;
//		noNewRoles.removeAll(champRoles);
//		List<ChampRole> addToList = new ArrayList<>();
//		List<ChampRole> newList = new ArrayList<>();
//				
//		for (ChampRole champRole : noNewRoles) {
//			for (ChampRole champRole2 : champRoles) {
//				if(!champRole.getIdChampRole().getRole().equals(champRole2.getIdChampRole().getRole())) {
//					addToList.add(champRole);
//					break;
//				}
//			}
//		}
//		newList.addAll(addToList);
//		newList.addAll(champRoles);
//		
//		return newList;
//	}
	
	public static List<ChampRole> validCombinations(List<ChampRole> oldList, List<ChampRole> champRoles) {
		List<ChampRole> validComps = filterComp(oldList, champRoles);
		List<ChampRole> validRoles = updateTeamWithNewChamp(validComps, champRoles);		
		return validRoles;
	}
}
