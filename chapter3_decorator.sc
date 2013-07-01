// Head First Design Patterns
// Chapter 3
// Using the 'decorator' design pattern
// - Starbuzz coffee example
// - Using decorators to add up different combinations of coffee orders (base coffee type + extras)
// - v2 adds a size variable to each base coffee type and extra (doesn't work)

/* Test Code:

StarbuzzCoffee.new;"";

*/

Beverage {

	// an abstract component

	var description = "Unknown Beverage", cost, size;

	getDescription {
		^description
	}

	setSize {arg argSize;
		size = argSize;
	}

	getSize {
		^size
	}

}

Espresso : Beverage {

	// a concrete component
	// extends beverage as HouseBlend is a kind of beverage

	*new {arg argSize;
		^super.new.init(argSize);
	}

	init {arg argSize;
		this.setSize(argSize);
		description = (this.getSize.asString + "Espresso"); // description instance variable inherited from Beverage
	}

	cost {
		^case
		{size == \Tall} { 1.25}
		{size == \Grande} { 1.50}
		{size == \Venti} { 1.99}
	}

}

DarkRoast : Beverage {

	// a concrete component
	// extends beverage as HouseBlend is a kind of beverage

	*new {arg argSize;
		^super.new.init(argSize);
	}

	init {arg argSize;
		this.setSize(argSize);
		description = (this.getSize.asString + "Dark Roast"); // description instance variable inherited from Beverage
	}

	cost {
		^case
		{size == \Tall} { 1.25}
		{size == \Grande} { 1.50}
		{size == \Venti} { 1.99}
	}

}

HouseBlend : Beverage {

	// a concrete component
	// extends beverage as HouseBlend is a kind of beverage

	*new {arg argSize;
		^super.new.init(argSize);
	}

	init {arg argSize;
		this.setSize(argSize);
		description = (this.getSize.asString + "House Blend"); // description instance variable inherited from Beverage
	}

	cost {
		^case
		{size == \Tall} { 1.25}
		{size == \Grande} { 1.50}
		{size == \Venti} { 1.99}
	}

}

Decaf : Beverage {

	// a concrete component
	// extends beverage as HouseBlend is a kind of beverage

	*new {arg argSize;
		^super.new.init(argSize);
	}

	init {arg argSize;
		this.setSize(argSize);
		description = (this.getSize.asString + "Espresso"); // description instance variable inherited from Beverage
	}

	cost {
		^case
		{size == \Tall} { 1.25}
		{size == \Grande} { 1.50}
		{size == \Venti} { 1.99}
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
		^beverage.getDescription + "+ Mocha";
	}

	cost {
		^(this.calculateCostBySize + beverage.postln.cost);
	}

	calculateCostBySize {
		var size = beverage.getSize;
		^case
		{size == \Tall} { 0.25 }
		{size == \Grande} { 0.35 }
		{size == \Venti} { 0.50 }
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
		^beverage.getDescription + "+ Soy";
	}

	cost {
		^(this.calculateCostBySize + beverage.cost);
	}

	calculateCostBySize {
		var size = beverage.getSize;
		^case
		{size == \Tall} { 0.19 }
		{size == \Grande} { 0.38 }
		{size == \Venti} { 0.46 }
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
		^beverage.getDescription + "+ Whip";
	}

	cost {
		^(this.calculateCostBySize + beverage.cost);
	}

	calculateCostBySize {
		var size = beverage.getSize;
		^case
		{size == \Tall} { 0.36 }
		{size == \Grande} { 0.50 }
		{size == \Venti} { 1.00 }
	}

}

StarbuzzCoffee {

	// test class

	*new {
		^super.new.init;
	}

	init {
		//this.orderGrandeEspresso;
		//this.orderVentiEspressoWithMocha;
		this.orderTallDarkRoastWithDoubleMochaWhip;
		//this.orderGrandeHouseBlendWithSoyMochaWhip;
	}

	orderGrandeEspresso {
		var beverage;
		beverage = Espresso.new(\Grande);
		this.printCost(beverage);
	}

	orderVentiEspressoWithMocha {
		var beverage;
		beverage = Espresso.new(\Venti);
		beverage = Mocha.new(beverage);
		this.printCost(beverage);
	}

	orderTallDarkRoastWithDoubleMochaWhip {
		var beverage;
		beverage = DarkRoast.new(\Tall);
		beverage = Mocha.new(beverage);
		beverage = Mocha.new(beverage);
		//beverage = Whip.new(beverage);
		this.printCost(beverage);
	}

	orderGrandeHouseBlendWithSoyMochaWhip {
		var beverage;
		beverage = HouseBlend.new(\Grande);
		beverage = Soy.new(beverage);
		beverage = Mocha.new(beverage);
		beverage = Whip.new(beverage);
		this.printCost(beverage);
	}

	printCost {arg beverage;
		(beverage.getDescription ++ " $ " ++ beverage.cost).postln;
	}

}
