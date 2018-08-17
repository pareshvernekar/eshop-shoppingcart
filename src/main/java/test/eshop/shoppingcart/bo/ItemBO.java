package test.eshop.shoppingcart.bo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name="ItemBO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemBO{

	@Id
	@NotNull
	@GeneratedValue
	@Column(name="id", nullable=false, updatable=false)
	Long id;
	
	@NotNull
	@Size(max=100)
	@Column(name="productId",nullable=false)
	String productId;
	
	@NotNull
	@Column(name="price",nullable=false)
	BigDecimal price;
	
	@NotNull
	@Column(name="quantity",nullable=false)
	int quantity;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="shoppingCartId")
	@EqualsAndHashCode.Exclude
	ShoppingCartBO shoppingCart;
	
}
