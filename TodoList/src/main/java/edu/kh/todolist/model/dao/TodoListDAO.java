package edu.kh.todolist.model.dao;

import java.util.List;

import edu.kh.todolist.model.dto.Todo;

/*
 * DAO(Data Access Object) : DB나 파일과 같은 영속성 계층 접근하는 로직
 * -> 즉, DB의 데이터를 조죄, 삽입, 삭제, 수정 등의 작업을 수행하는 객체
 * */
public interface TodoListDAO {

	/** TodoList를 파일로 저장하는 메서드
	 * 
	 */
	void saveFile() throws Exception;
	
	List<Todo> todoListFullView();

	int todoAdd(Todo todo) throws Exception;

	
}
