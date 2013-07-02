// Head First Design Patterns
// Chapter 5: Using the Singleton Pattern

// Singleton key points:
// - ensures a class has only one instance, and provide a global point of access to it
// - has no public constuctor
// - constructor is declared private
// - don't instantiate, just ask for an instance using a static method (class method)
// - responsible both for managing its own instance and performing
// - note: in multithreaded situations it is possible for two different instances to be returned (in Java at least)

// Singleton's in SuperCollider:
// - http://new-supercollider-mailing-lists-forums-use-these.2681727.n2.nabble.com/singleton-design-pattern-td4925493.html
// - http://new-supercollider-mailing-lists-forums-use-these.2681727.n2.nabble.com/Singleton-using-class-methods-td4567099.html

// Miguel comment: Stuff like delegation, target-action, model-view-controller, would be useful.  Also a good explanation of “is a” versus “has a” would be very useful for newbies.

/*

Singleton.getInstance

*/

Singleton {

	// approach taken in Head First Design Patterns

	classvar uniqueInstance; // holds the one and only instance
	// other useful variables to be declared here (instance vars?)

	*new {
		// overriden (no public constructor)
	}

	*getInstance {
		// if we never need the instance, it never gets created (lazy instantiation)
		uniqueInstance ?? {\here.postln; uniqueInstance = super.new};
		^uniqueInstance // return the one and only instance
	}

	// other useful methods to be declared here
	// (instance methods?)

}

Singleton2 {

	// an approach by Scott Carver
	// from http://new-supercollider-mailing-lists-forums-use-these.2681727.n2.nabble.com/Singleton-using-class-methods-td4567099.html

	classvar instance;
	var someObject, someCollection, someOtherStuff;

	*new {
		| a,b,c |
		^super.newCopyArgs(a,b,c).init();
	}

	*get {
		^( instance ?? { instance = Singleton.new })  // If it doesn't exist yet, create it.
	}

	init {
		someObject = blah;
		someCollection = .....;
		someOtherStuff = ......;
	}

	*someMethod {
		| ... args |
		this.get.someMethod( *args )
	}

	someMethod { } // actual implementation

	*someOtherMethod {
		| ... args |
		this.get.someOtherMethod( *args )
	}

	someOtherMethod { }
}

Singleton3 {

	// Dionysis' approach
	// - see http://new-supercollider-mailing-lists-forums-use-these.2681727.n2.nabble.com/Singleton-using-class-methods-td4567099.html

	*new{"You can not have an instance of Singleton".error}

	*method1{ "do something" }

	*method2{ "do something else" }

} 