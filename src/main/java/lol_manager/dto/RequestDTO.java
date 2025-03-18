package lol_manager.dto;

import java.util.List;

public class RequestDTO {

	private Object obj;
	private List<ChampRoleDTO> oldList;
	private List<ChampRoleDTO> champRoles;

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public List<ChampRoleDTO> getOldList() {
		return oldList;
	}

	public void setOldList(List<ChampRoleDTO> oldList) {
		this.oldList = oldList;
	}

	public List<ChampRoleDTO> getChampRoles() {
		return champRoles;
	}

	public void setChampRoles(List<ChampRoleDTO> champRoles) {
		this.champRoles = champRoles;
	}
		
	
}
