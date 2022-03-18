let carts = document.querySelectorAll('.add-cart');

let products = [
	{
		name: 'Onion',
		tag: 'onion',
		price: 2000,
		inCart : 0		
	},
	{
		name: 'Cherry',
		tag: 'cherry',
		price: 1000,
		inCart : 0		
	},
	{
		name: 'banana',
		tag: 'banana',
		price: 31000,
		inCart : 0		
	},
		{
		name: 'Orange',
		tag: 'orange',
		price: 2200,
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
	
		localStorage.setItem("totlaCost", product.price);
	}


onLoadCartNumbers();

	
