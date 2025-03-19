package lol_manager.utility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lol_manager.model.ChampRole;

public class ChampRoleUtility {
	
	// RIMUOVE TUTTI I CHAMP NON COMPATIBILI CON LE COMP DELLA NUOVA SELEZIONE
	public static List<ChampRole> filterComp(List<ChampRole> oldList, List<ChampRole> newChamps) {
	    List<ChampRole> result = new ArrayList<>();
	    
	    // Estrai le composizioni disponibili nei nuovi campioni
	    Set<Long> availableComps = new HashSet<>();
	    for (ChampRole newChamp : newChamps) {
	        availableComps.add(newChamp.getIdChampRole().getIdComp());
	    }
	    
	    // Filtra la vecchia lista per includere solo i campioni con composizioni compatibili
	    for (ChampRole oldChamp : oldList) {
	        if (availableComps.contains(oldChamp.getIdChampRole().getIdComp())) {
	            result.add(oldChamp);
	        }
	    }
	    
	    // Aggiungi i nuovi campioni
	    result.addAll(newChamps);
	    
	    return result;
	}
	
	// RIMUOVE TUTTI I CHAMP NON COMPATIBILI CON LE COMP 
	public static List<ChampRole> filterComp(List<ChampRole> oldList) {
	    List<ChampRole> result = new ArrayList<>();
	    
	    // Estrai le composizioni valide per il primo champ
	    Set<Long> availableComps = new HashSet<>();
	    for (ChampRole champ : oldList) {
	        // Aggiungi la composizione del campione corrente al set
	        availableComps.add(champ.getIdChampRole().getIdComp());
	    }
	    
	    // Per ogni champ, verifica se è compatibile con una delle composizioni
	    for (ChampRole champ : oldList) {
	        if (availableComps.contains(champ.getIdChampRole().getIdComp())) {
	            result.add(champ); // Aggiungi solo i campioni compatibili
	        }
	    }
	    
	    return result;
	}

	

	
	//CREA LA NUOVA LISTA COMBINANDO IL NUOVO CHAMP CON LA LISTA VECCHIA
	public static List<ChampRole> updateTeamWithNewChamp(List<ChampRole> currentTeam, List<ChampRole> selectedChamp) {
		List<ChampRole> withOutNewChamp = currentTeam;
		withOutNewChamp.removeAll(selectedChamp);
	    List<ChampRole> updatedTeam = new ArrayList<>();
	    	    
	    for (ChampRole newRole : selectedChamp) {
	    	
		    // Aggiungi solo i campioni che non causano duplicazioni di ruolo con il nuovo campione
		    for (ChampRole champ : withOutNewChamp) {
		        
		        // Se questo campione ha lo stesso ruolo del nuovo campione selezionato,
		        // assicuriamoci che non ci siano duplicazioni non necessarie
		        if (champ.getIdChampRole().getRole().equals(newRole.getIdChampRole().getRole())) {
		            boolean keepChamp = true;
		            
		            // Verifica se il campione attuale è intercambiabile (ha altre opzioni di ruolo)
		            // Se è intercambiabile, possiamo considerare di rimuoverlo
		            for (ChampRole otherChamp : withOutNewChamp) {
		                if (!otherChamp.equals(champ) && 
		                    otherChamp.getIdChampRole().getIdChamp() == champ.getIdChampRole().getIdChamp() &&
		                    !otherChamp.getIdChampRole().getRole().equals(champ.getIdChampRole().getRole())) {
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

	    // STREAM PER RIMUOVERE I DUPLICATI
	    updatedTeam = updatedTeam.stream().distinct().collect(Collectors.toList());
	    
	    // Aggiungi i campioni selezionati
	    updatedTeam.addAll(selectedChamp);
	    
	    return updatedTeam;
	}
	
	//RIMUOVE TUTTI I CHAMP CHE CONDIVIDONO IL RUOLO CON UN CHAMP CHE HA RUOLO UNICO
	public static List<ChampRole> uniqueRoleChampRemoval(List<ChampRole> currentTeam) {
	    List<ChampRole> uniqueRoleTeam = new ArrayList<>();	    
	    uniqueRoleTeam.addAll(currentTeam);
	    
	    for (ChampRole champ : currentTeam) {
	    	boolean uniqueRoleChamp = true;
	    	
	    	for (ChampRole unique : currentTeam) {
	    		if(champ.getIdChampRole().getIdChamp() == unique.getIdChampRole().getIdChamp() &&
	    		  !champ.getIdChampRole().getRole().equals(unique.getIdChampRole().getRole())) {
	    			uniqueRoleChamp = false;
	    			break;
	    		}
	    	}
	    	
	    	if (uniqueRoleChamp) {
	    		for (ChampRole removable : currentTeam) {
	    			if (!champ.getIdChampRole().getIdChamp().equals(removable.getIdChampRole().getIdChamp())  &&
	    				champ.getIdChampRole().getRole().equals(removable.getIdChampRole().getRole())) {
	    				uniqueRoleTeam.remove(removable);
	    			}
	    		}
	    	}
	    	
	    }
	    
	    return uniqueRoleTeam;
	}
	
	//RAGGRUPPA TUTTI I METODI DEL COMBINER
	public static List<ChampRole> compCombinator(List<ChampRole> oldList, List<ChampRole> champRoles) {
		List<ChampRole> validComps = filterComp(oldList, champRoles);
		List<ChampRole> validRoles = updateTeamWithNewChamp(validComps, champRoles);
		List<ChampRole> validUniqueRole = uniqueRoleChampRemoval(validRoles);
		List<ChampRole> validTeam = filterComp(validUniqueRole); 
		return validTeam;
	}

	// RITORNA UNA LISTA DI ID VALIDI DI TEAM COMP
	public static List<Long> validIdComps(List<ChampRole> champRoles) {
	    Set<Long> availableComps = new HashSet<>();
	    for (ChampRole comp : champRoles) {
	        availableComps.add(comp.getIdChampRole().getIdComp());
	    }	    
		List<Long> idsComp = new ArrayList<>(availableComps);
		return idsComp;
	}
	
	// RITORNA UNA LISTA DI RUOLI GIA' PRESI NELLA COMP
	public static List<String> invalidRoles(List<ChampRole> champRoles) {
		Set<String> takenRoles = new HashSet<>();
		
	    for (ChampRole role : champRoles) {
	    	boolean uniqueRoleChamp = true;
	    	
	    	for (ChampRole unique : champRoles) {
	    		if(role.getIdChampRole().getIdChamp() == unique.getIdChampRole().getIdChamp() &&
	    		  !role.getIdChampRole().getRole().equals(unique.getIdChampRole().getRole())) {
	    			uniqueRoleChamp = false;
	    			break;
	    		}
	    	}
	    	
	    	if(uniqueRoleChamp) {
	    		takenRoles.add(role.getIdChampRole().getRole());
	    	}	    	
	    }
		
		List<String> roles = new ArrayList<>(takenRoles);
		return roles;
	}
	
	// RITORNA UNA LISTA DI INVALID CHAMPS CHE SONO GIA'STATI PICKATI 
	public static List<Long> invalidChamps(List<ChampRole> champRoles) {
		Set<Long> includedChamps = new HashSet<>();
		for (ChampRole champ : champRoles) {
			includedChamps.add(champ.getIdChampRole().getIdChamp());
		}
		List<Long> invalidChamps = new ArrayList<>(includedChamps);
		return invalidChamps;
	}
}
