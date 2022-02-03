//Ajouter un produit au panier
function add_product(idProduct) {
  var request = new XMLHttpRequest();
  var url = "my_resto/addProductShoppingCard/"+idProduct;
  request.open("GET", url, true);
  //request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
  request.send();
}