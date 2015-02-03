package aiti.m1403l.group1.data.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Film {

	private int id;
	private String name;
	private int year;
	private String image;
	private String youtubeId;
	private int viewCount;
	private int like;
	private String duration;
	@SerializedName("category")
	private List<Integer> categories;
	private int bookmark;

	public Film() {
	}	
	
	public Film(int id, String name, int year, String image, String youtubeId,
			int viewCount, int like, String duration, List<Integer> categories) {
		super();
		this.id = id;
		this.name = name;
		this.year = year;
		this.image = image;
		this.youtubeId = youtubeId;
		this.viewCount = viewCount;
		this.like = like;
		this.duration = duration;
		this.categories = categories;
		this.bookmark = 0;
	}
	
	public Film(int id, String name, int year, String image, String youtubeId,
			int viewCount, int like, String duration, List<Integer> categories,
			int bookmark) {
		super();
		this.id = id;
		this.name = name;
		this.year = year;
		this.image = image;
		this.youtubeId = youtubeId;
		this.viewCount = viewCount;
		this.like = like;
		this.duration = duration;
		this.categories = categories;
		this.bookmark = bookmark;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getYoutubeId() {
		return youtubeId;
	}

	public void setYoutubeId(String youtubeId) {
		this.youtubeId = youtubeId;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	
	public List<Integer> getCategories() {
		return categories;
	}


	public void setCategories(List<Integer> categories) {
		this.categories = categories;
	}

	public int getBookmark() {
		return bookmark;
	}

	public void setBookmark(int bookmark) {
		this.bookmark = bookmark;
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", name=" + name + ", year=" + year
				+ ", image=" + image + ", youtubeId=" + youtubeId
				+ ", viewCount=" + viewCount + ", like=" + like + ", duration="
				+ duration + "]";
	}

}
