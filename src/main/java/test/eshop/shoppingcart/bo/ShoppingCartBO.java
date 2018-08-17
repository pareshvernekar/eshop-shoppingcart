package test.eshop.shoppingcart.bo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartBO{
	
	@Id
	@NotNull
	@GeneratedValue
	@Column(name="id", nullable=false, updatable=false)
	Long id;
	
	
	@NotNull
	@Size(max=100)
	@Column(name="shoppingCartId",nullable=false)
	private String shoppingCartId;
	
	
	@NotNull
	@Size(max=100)
	@Column(name="userId",nullable=false)
	private String userId;
	
	@OneToMany(
	        cascade = CascadeType.ALL, 
	        orphanRemoval = true
	    )
	@JoinColumn(name = "shoppingCartId")
	private Set<ItemBO> items;
}
