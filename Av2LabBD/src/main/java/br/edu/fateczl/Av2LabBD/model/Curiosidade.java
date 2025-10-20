package br.edu.fateczl.Av2LabBD.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "curiosidade")
public class Curiosidade {
	
	@Id
	@Column(name = "codigo", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Desenvolvedora.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "desenvolvedora_codigo", nullable = false)
	private Desenvolvedora desenvolvedora;
	
	@Column(name = "texto", nullable = false)
	private String texto;
}
