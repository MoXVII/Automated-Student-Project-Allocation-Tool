package StuProjAllocation.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


//class for serving as a store of all the objects produced by the algorithm persisted in the back-end and to be displayed on views using JSTl
@Entity
public class UniSystem {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name="unisystem_id")
	private int id;

	@Column
	private String name;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	List<Preference> prefList = new ArrayList<>();
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	List<Student> studList = new ArrayList<>();
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	List<Project> projList = new ArrayList<>();

	
	public UniSystem() {}
	
	public UniSystem(String name) {
		this.name = name;
		prefList = new ArrayList<>();
		studList = new ArrayList<>();
		projList = new ArrayList<>();

	}
	

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public List<Preference> getPrefList() {
		return prefList;
	}

	public void setPrefList(List<Preference> prefList) {
		this.prefList = prefList;
	}

	public List<Student> getStudList() {
		return studList;
	}

	public void setStudList(List<Student> studList) {
		this.studList = studList;
	}

	public List<Project> getProjList() {
		return projList;
	}

	public void setProjList(List<Project> projList) {
		this.projList = projList;
	}


	
}
