// Head First Design Patterns
// Chapter 3
// Using the 'decorator' design pattern
// - Starbuzz coffee example
// - Using decorators to add up different combinations of coffee orders (base coffee type + extras)

/* Test Code:

StarbuzzCoffee.new;"";

*/

Beverage {

	// an abstract component

	var description = "Unknown Beverage";
	var cost;

	getDescription {
		^description
	}

/*	cost {

	}*/

}

Espresso : Beverage {

	// a concrete component
	// extends beverage as HouseBlend is a kind of beverage

	*new {
		^super.new.init;
	}

	init {
		description = "Espresso"; // description instance variable inherited from Beverage
	}

	cost {
		^1.99
	}

}

HouseBlend : Beverage {

	// a concrete component
	// extends beverage as HouseBlend is a kind of beverage

	*new {
		^super.new.init;
	}

	init {
		description = "House Blend"; // description instance variable inherited from Beverage
	}

	cost {
		^0.89
	}

}

DarkRoast : Beverage {

	// a concrete component
	// extends beverage as HouseBlend is a kind of beverage

	*new {
		^super.new.init;
	}

	init {
		description = "Dark Roast"; // description instance variable inherited from Beverage
	}

	cost {
		^1.29
	}

}

Decaf : Beverage {

	// a concrete component
	// extends beverage as HouseBlend is a kind of beverage

	*new {
		^super.new.init;
	}

	init {
		description = "Decaf"; // description instance variable inherited from Beverage
	}

	cost {
		^0.99
	}

}


CondimentDecorator : Beverage {

	// abstract decorator
	// extends beverage class so as to be interchangable with a beverage
	// should reimplement getDescription method (why, it works without doing this...)
	// i don't quite understand why we need this, why not just subclass Beverage directly?

}

Mocha : CondimentDecorator {

	// concrete decorator

	var beverage;

	*new {arg argBeverage;
		^super.new.init(argBeverage);
	}

	init {arg argBeverage;
		beverage = argBeverage;
	}

	getDescription {
		^beverage.getDescription ++ ", Mocha";
	}

	cost {
		^(0.20 + beverage.cost);
	}
}

Soy : CondimentDecorator {

	// concrete decorator

	var beverage;

	*new {arg argBeverage;
		^super.new.init(argBeverage);
	}

	init {arg argBeverage;
		beverage = argBeverage;
	}

	getDescription {
		^beverage.getDescription ++ ", Soy";
	}

	cost {
		^(0.25 + beverage.cost);
	}
}

Whip : CondimentDecorator {

	// concrete decorator

	var beverage;

	*new {arg argBeverage;
		^super.new.init(argBeverage);
	}

	init {arg argBeverage;
		beverage = argBeverage;
	}

	getDescription {
		^beverage.getDescription ++ ", Whip";
	}

	cost {
		^(0.32 + beverage.cost);
	}
}

StarbuzzCoffee {

	// test class

	*new {
		^super.new.init;
	}

	init {
		this.orderEspresso;
		this.orderDarkRoast;
		this.orderHouseBlend;
	}

	orderEspresso {
		var beverage;
		beverage = Espresso.new;
		(beverage.getDescription ++ " $" ++ beverage.cost).postln;
	}

	orderDarkRoast {
		var beverage;
		beverage = DarkRoast.new;
		beverage = Mocha.new(beverage);
		beverage = Mocha.new(beverage);
		beverage = Whip.new(beverage);
		(beverage.getDescription ++ " $" ++ beverage.cost).postln;
	}

	orderHouseBlend {
		var beverage;
		beverage = HouseBlend.new;
		beverage = Soy.new(beverage);
		beverage = Mocha.new(beverage);
		beverage = Whip.new(beverage);
		(beverage.getDescription ++ " $" ++ beverage.cost).postln;
	}

}
