package br.com.desafio.gestaoclientes.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull(message = "O nome não pode ser nulo.")
    @Size(min = 3, message = "O nome do cliente deve ter no mínimo 3 caracteres.")
    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    @NotEmpty(message = "A lista de telefones não pode estar vazia.")
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private Set<Telefone> telefones = new HashSet<>();

    @NotEmpty(message = "A lista de e-mails não pode estar vazia.")
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private Set<Email> emails = new HashSet<>();
}