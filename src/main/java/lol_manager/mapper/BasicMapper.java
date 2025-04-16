package lol_manager.mapper;

import java.util.ArrayList;
import java.util.List;

public abstract class BasicMapper <E,D> {

	public abstract E entityFromDto(D dto) throws Exception;
	
	public abstract D dtoFromEntity(E entity) throws Exception;
	
	public List<E> entityFromDto(List<D> dtoList)  throws Exception {
		List<E> entityList = new ArrayList<>();
		for (D dto : dtoList) {
			entityList.add(entityFromDto(dto));
		}
		return entityList;
	};
	
	public List<D> dtoFromEntity(List<E> entityList)  throws Exception {
		List<D> dtoList = new ArrayList<>();
		for (E entity : entityList) {
			dtoList.add(dtoFromEntity(entity));
		}
		return dtoList;
	};
	
}
