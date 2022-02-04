const parallaxElements = document.querySelectorAll('.scroll')

// The parallax function
const parallax = elements => {
    if ('undefined' !== elements && elements.length > 0 && window.innerWidth > 720) {
      elements.forEach( element => {
        let y = window.innerHeight - element.getBoundingClientRect().top
        if (y > 0) {
            console.log(window.innerWidth)
          element.style.transform = 'translate3d(0, -' + (0.12 * y) + 'px ,0)'
        }
      })
    }
  }

//If element is in viewport, set its position
parallax(parallaxElements)

var menu1 = document.getElementById("menu-1");
var menu2 = document.getElementById("menu-2");
var menu3 = document.getElementById("menu-3");
var menu4 = document.getElementById("menu-4");

window.onscroll = function(){

    parallax(parallaxElements);

    var top = window.scrollY;
    // console.log(top)
    if (top >= menu1.offsetTop && top <= menu2.offsetTop ){
        document.getElementById("nav-1").classList.add("nav-food_link--active");
    } else {
        document.getElementById("nav-1").classList.remove("nav-food_link--active");
    }

    if (top >= menu2.offsetTop && top <= menu3.offsetTop ){
        document.getElementById("nav-2").classList.add("nav-food_link--active");
    } else {
        document.getElementById("nav-2").classList.remove("nav-food_link--active");
    }

    if (top >= menu3.offsetTop && top <= menu4.offsetTop ){
        document.getElementById("nav-3").classList.add("nav-food_link--active");
    } else {
        document.getElementById("nav-3").classList.remove("nav-food_link--active");
    }

    if (top >= menu4.offsetTop){
        document.getElementById("nav-4").classList.add("nav-food_link--active");
    } else {
        document.getElementById("nav-4").classList.remove("nav-food_link--active");
    }
}

//Ajouter un produit au panier
function add_product(idProduct) {
    this.incrementeCart();
    var request = new XMLHttpRequest();
    var url = "my_resto/addProductShoppingCard/"+idProduct;
    request.open("GET", url, true);
    //request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    request.send();
}

function incrementeCart() {
    cart = document.getElementById('cart-count');
    var cartCount = Number(document.getElementById('cart-count').innerHTML);
    var newCount = cartCount + 1;
    cart.innerHTML = newCount;

    cart2 = document.getElementById('cart-count-nav');
    var cartCount = Number(document.getElementById('cart-count-nav').innerHTML);
    var newCount = cartCount + 1;
    cart2.innerHTML = newCount;
}
