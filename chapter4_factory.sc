// Head First Design Patterns
// Chapter 4: Using the 'factory' design pattern

// a. Factory Method Pattern
// - defines an interface for creating an object, but lets subclasses decide which class to instantiate
// - lets a class defer instantiation to subclasses

// Design principle: Depend on abstractions. Do not depend on concrete classes

// Pizza Store example (v2):

// in v1: Factory Method for Creating Regional Pizza Stores
// - the abstract creator provides an interface with a 'factory method' for creating objects
// - any other methods in the abstract creator will operate on products produced by the factory method
// - the abstract creator class is written without knowledge of the actual products that will be created
// - instead, subclasses of the abstract creator are responsible for implementing the factory method and creating products

// in v2: Abstract Factory for Creating Families of Pizza Ingredients
// - abstract factory provides a means of creating a family of ingredients for pizzas
// - decouples code from the actual factory that creates the products
// - allows a variety of factories for different contexts e.g. regional difference
// - definition: The Abstract Factory Pattern: "provides an interface for creating families of related or dependent objects without specifying their concrete classes"
/*

PizzaTestDrive.new;"";

*/

PizzaStore {

	// abstract creator

	orderPizza {arg type;
		var pizza;
		pizza = this.createPizza(type);
		pizza.prepare;
		pizza.bake;
		pizza.cut;
		pizza.box;
		"Pizza done, ready to take next order...".postln;
		^pizza;
	}

}

NewYorkStylePizzaStore : PizzaStore {

	// concrete creator

	createPizza {arg type;
		// factory method
		// creates own style of pizza
		var pizza, ingredientFactory;
		ingredientFactory = NewYorkPizzaIngredientFactory.new;
		^case
		{type == \cheese} {
			pizza = CheesePizza(ingredientFactory);
			pizza.setName("New York Style Cheese Pizza");
		}
		{type == \clam} {
			pizza = ClamPizza(ingredientFactory);
			pizza.setName("New York Style Clam Pizza");
		}
	}

}

ChicagoStylePizzaStore : PizzaStore {

	// concrete creator

	createPizza {arg type;
		// factory method
		// creates own style of pizza
		var pizza, ingredientFactory;
		ingredientFactory = ChicagoPizzaIngredientFactory.new;
		^case
		{type == \cheese} {
			pizza = CheesePizza(ingredientFactory);
			pizza.setName("Chicago Style Cheese Pizza");
		}
		{type == \clam} {
			pizza = ClamPizza(ingredientFactory);
			pizza.setName("Chicago Style Clam Pizza");
		}
	}

}

Pizza {

	// abstract product

	var name, dough, sauce, veggies, cheese, pepperoni, clam;

	prepare {

	}

	bake {
		("Baking for 25 minutes at 350").postln;
	}

	cut {
		("Cutting the pizza into diagonal slices").postln;
	}

	box {
		("Placing pizza in official PizzaStore box").postln;
	}

	setName {arg argName;
		name = argName;
	}

	getName {
		^name;
	}
}

CheesePizza : Pizza {

	// concrete product?
	var ingredientFactory;

	*new {arg argIngredientFactory;
		^super.new.initCheesePizza(argIngredientFactory);
	}

	initCheesePizza {arg argIngredientFactory;
		ingredientFactory = argIngredientFactory;
	}

	prepare {
		// veggies missing
		("Preparing " ++ name).postln;
		dough = ingredientFactory.createDough;
		("Using dough: " ++ dough).postln;
		sauce = ingredientFactory.createSauce;
		("Using sauce: " ++ sauce).postln;
		cheese = ingredientFactory.createCheese;
		("Using cheese: " ++ cheese).postln;
	}

}

ClamPizza : Pizza {

	// concrete product?
	var ingredientFactory;

	*new {arg argIngredientFactory;
		^super.new.initClamPizza(argIngredientFactory);
	}

	initClamPizza {arg argIngredientFactory;
		ingredientFactory = argIngredientFactory;
	}

	prepare {
		// veggies missing
		("Preparing " ++ name).postln;
		dough = ingredientFactory.createDough;
		("Using dough: " ++ dough).postln;
		sauce = ingredientFactory.createSauce;
		("Using sauce: " ++ sauce).postln;
		cheese = ingredientFactory.createCheese;
		("Using cheese: " ++ cheese).postln;
		clam = ingredientFactory.createClam;
		("Using clam: " ++ clam).postln;
	}

}

// // ingredient factory

PizzaIngredientFactory {

	// abstract factory

	// do we need these methods? they show what is being overridden, but that's all
	createDough {}
	createSauce {}
	createCheese {}
	createVeggies {}
	createPepperoni {}
	createClam {}

}

NewYorkPizzaIngredientFactory : PizzaIngredientFactory {

	// concrete factory

	createDough {
		^ThinCrustDough.new;
	}

	createSauce {
		^MarinaraSauce.new;
	}

	createCheese {
		^ReggianoCheese.new;
	}

	createVeggies {
		^[Garlic.new, Onion.new, Mushroom.new, RedPepper.new];
	}

	createPepperoni {
		^SlicedPepperoni.new;
	}

	createClam {
		^FreshClams.new;
	}

}

ChicagoPizzaIngredientFactory : PizzaIngredientFactory {

	// concrete factory

	createDough {
		^ThickCrustDough.new;
	}

	createSauce {
		^PlumTomatoSauce.new;
	}

	createCheese {
		^MozzarellaCheese.new;
	}

	createVeggies {
		^[Spinach.new, Eggplant.new, BlackOlives.new];
	}

	createPepperoni {
		^SlicedPepperoni.new;
	}

	createClam {
		^FrozenClams.new;
	}
}

// // ingredients

// chicago ingredients

FrozenClams {}

PlumTomatoSauce {}

ThickCrustDough {}

MozzarellaCheese {}

// new york ingredients

FreshClams {}

MarinaraSauce {}

ThinCrustDough {}

ReggianoCheese {}

// california ingredients

Camari {}

BruschettaSauce {}

VeryThinCrust {}

GoatCheese {}

// test class

PizzaTestDrive {

	*new {
		this.orderForEthan;
		this.orderForJoel;
	}

	*orderForEthan {
		var newYorkStylePizzaStore = NewYorkStylePizzaStore.new;
		var pizza = newYorkStylePizzaStore.orderPizza(\cheese);
		("Ethan ordered a " ++ pizza.getName).postln;
		Char.nl.postln;
	}

	*orderForJoel {
		var chicagoStylePizzaStore = ChicagoStylePizzaStore.new;
		var pizza = chicagoStylePizzaStore.orderPizza(\clam);
		("Joel ordered a " ++ pizza.getName).postln;
		Char.nl.postln;
	}

}

