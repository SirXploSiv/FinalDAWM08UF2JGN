/**
 * 
 */
package daw.m08.uf2.jgn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author iaw47930799
 *
 */
public class BeanPersona implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	private String nombre;
	@NotBlank
	private String apellidos;
	private Integer edad;
	/*Alternativa a interpolar messages...*/
	@NotNull(message="[Fecha Nacimiento , no puede estar vacía]")
	@DateTimeFormat(pattern = "YYYY-MM-dd")
	private Date fechaNacimiento;
	@NotEmpty
	private String genero;
	@NotEmpty
	private List<String> idiomas;
	private Boolean carnetDeConducir;

	public BeanPersona() {}

	public BeanPersona(String nombre, String apellidos, Integer edad, Date fechaNacimiento, String genero,
			ArrayList<String> idiomas, Boolean carnetDeConducir) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
		this.fechaNacimiento = fechaNacimiento;
		this.genero = genero;
		this.idiomas = idiomas;
		this.carnetDeConducir = carnetDeConducir;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public List<String> getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(List<String> idiomas) {
		this.idiomas = idiomas;
	}

	public Boolean getCarnetDeConducir() {
		return carnetDeConducir;
	}

	public void setCarnetDeConducir(Boolean carnetDeConducir) {
		this.carnetDeConducir = carnetDeConducir;
	}

	@Override
	public String toString() {
		return "BeanPersona [nombre=" + nombre + ", apellidos=" + apellidos + ", edad=" + edad + ", fechaNacimiento="
				+ fechaNacimiento + ", genero=" + genero + ", idiomas=" + idiomas + ", carnetDeConducir="
				+ carnetDeConducir + "]";
	}
	

}
