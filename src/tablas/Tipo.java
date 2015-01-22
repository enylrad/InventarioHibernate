package tablas;

// Generated 22-ene-2015 17:40:52 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * Tipo generated by hbm2java
 */
public class Tipo implements java.io.Serializable {

	private Integer cod;
	private String nombre;
	private Set<Componente> componentes = new HashSet<Componente>(0);

	public Tipo() {
	}

	public Tipo(String nombre, Set<Componente> componentes) {
		this.nombre = nombre;
		this.componentes = componentes;
	}

	public Integer getCod() {
		return this.cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Componente> getComponentes() {
		return this.componentes;
	}

	public void setComponentes(Set<Componente> componentes) {
		this.componentes = componentes;
	}

}
