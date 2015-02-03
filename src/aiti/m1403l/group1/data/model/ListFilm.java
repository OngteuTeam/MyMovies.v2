package aiti.m1403l.group1.data.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ListFilm {

	int now;
	@SerializedName("listFilm")
	List<Film> films;
	List<Integer> deletedId;
	
	public ListFilm() {
	}
	
	public ListFilm(int now, List<Film> films, List<Integer> deletedId) {
		super();
		this.now = now;
		this.films = films;
		this.deletedId = deletedId;
	}

	public int getNow() {
		return now;
	}

	public void setNow(int now) {
		this.now = now;
	}
	
	
	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

	public List<Integer> getDeletedId() {
		return deletedId;
	}

	public void setDeletedId(List<Integer> deletedId) {
		this.deletedId = deletedId;
	}
	
}
