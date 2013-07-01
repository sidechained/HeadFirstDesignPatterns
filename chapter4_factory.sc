// Head First Design Patterns
// Chapter 4: Using the 'factory' design pattern
// - Pizza Store example

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
		^case
		{type == \cheese} { NewYorkStyleCheesePizza.new }
	}

}

ChicagoStylePizzaStore : PizzaStore {

	// concrete creator

	createPizza {arg type;
		// factory method
		// creates own style of pizza
		^case
		{type == \cheese} { ChicagoStyleCheesePizza.new }
	}

}

Pizza {

	// abstract product

	var name, dough, sauce, toppings;

	*new {
		^super.new.initPizza
	}

	initPizza {
		// init class has a unique name here (if just called init it will get called from other classes)
		toppings = Array.new;
	}

	prepare {
		("Preparing " ++ name).postln;
		("Tossing dough").postln;
		("Adding sauce").postln;
		("Adding toppings: " ++ toppings.asString.drop(2).drop(-2)).postln;
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

	getName {
		^name;
	}
}

NewYorkStyleCheesePizza : Pizza {

	// concrete product

	*new {
		^super.new.initNewYorkStyleCheesePizza
	}

	initNewYorkStyleCheesePizza {
		// init class has a unique name here (if just called init it will get called from other classes)
		name = "NY Style Sauce and Cheese Pizza";
		dough = "Thin Crust Dough";
		sauce = "Marinara Sauce";
		toppings = toppings.add("Grated Reggiano Cheese");
	}

}

ChicagoStyleCheesePizza : Pizza {

	// concrete product

	*new {
		^super.new.initChicagoStyleCheesePizza
	}

	initChicagoStyleCheesePizza {
		// init class has a unique name here (if just called init it will get called from other classes)
		name = "Chicago Style Deep Dish Pizza";
		dough = "Extra Thick Crust Dough";
		sauce = "Plum Tomato Sauce";
		toppings = toppings.add("Shredded Mozzarella Cheese");
	}

	cut {
		("Cutting the pizza into square slices").postln;
	}

}

PizzaTestDrive {

	*new {
		this.orderForEthan;
		this.orderForJoel;
	}

	*orderForEthan {
		var newYorkPizzaStore = NewYorkStylePizzaStore.new;
		var pizza = newYorkPizzaStore.orderPizza(\cheese);
		("Ethan ordered a " ++ pizza.getName).postln;
		Char.nl.postln;
	}

	*orderForJoel {
		var chicagoPizzaStore = ChicagoStylePizzaStore.new;
		var pizza = chicagoPizzaStore.orderPizza(\cheese);
		("Joel ordered a " ++ pizza.getName).postln;
		Char.nl.postln;
	}

}

