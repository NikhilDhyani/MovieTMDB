package com.example.nikhil.moviereviews.CastPOJO;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Credits{

	@SerializedName("cast")
	private List<CastItem> cast;

	@SerializedName("crew")
	private List<CrewItem> crew;

	public void setCast(List<CastItem> cast){
		this.cast = cast;
	}

	public List<CastItem> getCast(){
		return cast;
	}

	public void setCrew(List<CrewItem> crew){
		this.crew = crew;
	}

	public List<CrewItem> getCrew(){
		return crew;
	}

	@Override
 	public String toString(){
		return 
			"Credits{" + 
			"cast = '" + cast + '\'' + 
			",crew = '" + crew + '\'' + 
			"}";
		}
}