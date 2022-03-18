let carts = document.querySelectorAll('.add-cart');

let products = [
	{
		name: 'cherry',
		tag: 'cherry',
		price: 1,
		inCart : 0		
	},
	{
		name: 'fineapple',
		tag: 'fineapple',
		price: 2,
		inCart : 0		
	},
	{
		name: 'banana',
		tag: 'banana',
		price: 3,
		inCart : 0		
	},
		{
		name: 'onion',
		tag: 'onion',
		price: 4,
		inCart : 0		
	}
	
]

for(let i=0; i<carts.length; i++) {
	carts[i].addEventListener('click', () => {
		cartNumbers(products[i]);
		totalCost(products[i])
	}) 
	
}


function onLoadCartNumbers(){
	let productNumbers = localStorage.getItem('cartNumbers');
	
	if(productNumbers){
		document.querySelector('.cart span').textContent = productNumbers;
	}
}




function cartNumbers(product){
//	console.log("Theproduct clicked is ", product);
	let productNumbers = localStorage.getItem('cartNumbers');
	
	productNumbers = parseInt(productNumbers);
	
	if(productNumbers){
		localStorage.setItem('cartNumbers', productNumbers + 1);
		document.querySelector('.cart span').textContent = productNumbers + 1;
	} else {
		localStorage.setItem('cartNumbers', 1);
		document.querySelector('.cart span').textContent = 1;
	}
	
	setItems(product);
}	
function setItems(product){
//	console.log("Inside of setItems function");
//	console.log("My product is", product);
	let cartItems = localStorage.getItem('productsInCart');
	cartItems = JSON.parse(cartItems);
//	console.log("My cartItems are", cartItems);
	
	if(cartItems != null) {
//		cartItems['banana']
		if(cartItems[product.tag] == undefined){
			cartItems = {
				cartItems,
				[product.tag]: product
			}
		}
		cartItems[product.tag].inCart +=1;
	} else {
		product.inCart = 1;
		cartItems ={
			[product.tag]: product
		} 
	}
	localStorage.setItem("productsInCart", JSON.stringify(cartItems));

}

	function totalCost(product){
//		console.log("The product price is", product.price);
		let cartCost = localStorage.getItem('totalCost');

		console.log("My cartCost is ", cartCost);
		console.log(typeof cartCost);
		
		if(cartCost != null){
			cartCost = parseInt(cartCost);
			localStorage.setItem("totalCost", cartCost + product.price);
		} else {
			localStorage.setItem("totalCost", product.price);
		
		}
		
		
	}

function displayCart(){
	let cartItems = localStorage.getItem("productsInCart")
	cartItems = JSON.parse(cartItems)
	let productContainer = document.querySelector(".products");
	let cartCost = localStorage.getItem('totalCost');
	
//	console.log(cartItems);
	if(cartItems && productContainer){
//		console.log("running")
		productContainer.innerHTML = '';
		Object.values(cartItems).map(item => {
			productContainer.innerHTML += `
			<div class="product">
				<ion-icon name = "close-circle"></ion-icon>
				<img src="../resources/img/store/${item.tag}.jpg">
				<span> ${item.name} </span>
			</div>
			<div class="price">${item.price}</div>
			<div class="quantity">
				<ion-icon class="decrease"
				name="arrow-dropleft-circle"></ion-icon>
				<span>${item.inCart}</span>	
				<ion-icon class="increase"
				name="arrow-dropright-circle"></ion-icon>		
			</div>
			<div class="total">
				${item.inCart * item.price}
			</div>
			`;
			
			
		});

	productContainer.innerHTML += `
		<div class="basketTotalContainer">
			<h4 class="basketTotalTitle">
				Basket Total
			</h4>
			<h4 class="basketTotal">
			 	$${cartCost},00
			</h4>
			
		`;
	}
}

onLoadCartNumbers();
displayCart();
	
