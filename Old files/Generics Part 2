WILDCARDS:

public static void printList2(List<? extends Number> list) {}
It allows:
AtomicInteger, AtomicLong, BigDecimal, BigInteger, Byte, Double, Float, Integer, Long, Short and Number

In generic code, the question mark (?), called the wildcard, represents an unknown type.
The wildcard can be used in a variety of situations: as the type of a parameter, field, or local variable; sometimes as a return type (though it is better programming practice to be more specific). The wildcard is never used as a type argument for a generic method invocation, a generic class instance creation, or a supertype.

UPPER BOUNDED WILDCARDS
You can use an upper bounded wildcard to relax the restrictions on a variable.

static void process(List<? extends Foo> list) {}

The upper bounded wildcard, <? extends Foo>, where Foo is any type, matches Foo and any subtype of Foo.

class A{
	static void show(List<? extends Number> list) {}
}

List<Integer> intList = new ArrayList<>();
List<String> strList = new ArrayList<>();

A.show(strList); // Invalid
A.show(intList); // Valid

Example:

The sumOfList method returns the sum of the numbers in a list:

public static double sumOfList(List<? extends Number> list) {
    double s = 0.0;
    for (Number n : list)
        s += n.doubleValue();
    return s;
}
The following code, using a list of Integer objects, prints sum = 6.0:

List<Integer> li = Arrays.asList(1, 2, 3);
System.out.println("sum = " + sumOfList(li));
A list of Double values can use the same sumOfList method. The following code prints sum = 7.0:

List<Double> ld = Arrays.asList(1.2, 2.3, 3.5);
System.out.println("sum = " + sumOfList(ld));


public static void printList(List<?> list) {
    for (Object elem : list) {
        System.out.print(elem + " ");
    }
    System.out.println();
}


Note:

List<Integer> is not a List<Object>
List<String> is not a List<Object> 

print(List<Object> list){}
print(intList) // Invalid
print(strList) // Invalid
print(objList); // Valid

printList(List<?> list){}
print(intList) // Valid
print(strList) // Valid
Print(objList); // Valid


Example:

class Animal{}
class Dog extends Animal{}
class Wolf extends Dog{}

class Seed{}
class Plant extends Seed{}
class Tree extends Plant{}

class Hunter{
    
    public static <T extends Animal> void  hunt(T t){
        // Allows Animal and it's subclasses, Dog and Wolf.
        System.out.println("Brutally hunted : "+t);
    }
	
    public static void huntAnimals(List<? extends Animal> list){
        // Allows Animals List and it's sub classes List.
        list.forEach((animal) -> {
            hunt(animal);
        });
    }
}


public class NewMain {
    public static void main(String[] args) {
        
        //Working with Single Objects
        
        Tree tree = new Tree();
        Seed seed = new Seed();
        Animal ani = new Animal();
        Dog dog  = new Dog();
		
        Hunter.hunt(t);  Uncompilable source code - Erroneous sym type
        Hunter.hunt(seed); Uncompilable source code - Erroneous sym type
        Hunter.hunt(ani);  Valid
        Hunter.hunt(dog);  Valid
        
        // Working with List/Collections
        
        List<Dog> dogs = new ArrayList<>();
        List<Seed> seeds = new ArrayList<>();
        List<Animal> animals = new ArrayList<>();
        List<Tree> trees = new ArrayList<>();
		
        dogs.add(new Dog()); 		// Valid
        animals.add(new Dog());		// Valid
        animals.add(new Wolf());	// Valid 
        animals.add(new Animal());	// Valid
        animals.<Animal>add(new Animal());	// Valid

        Hunter.huntAnimals(animals); 	// Valid
        Hunter.huntAnimals(dogs); 	 	// Valid
        Hunter.huntAnimals(trees); 		// Invalid
        Hunter.huntAnimals(seeds); 		// Invalid
    }
}





LOWER BOUNDED WILDCARDS

A lower bounded wildcard is expressed using the wildcard character ('?'), following by the super keyword, followed by its lower bound: <? super A>.

public static void addNumbers(List<? super Integer> list) {
    for (int i = 1; i <= 10; i++) {
        list.add(i);
    }
}


Note:
-> extends
G -> F -> E -> D -> C -> B -> A -> Object

List<? super D> list:
A,B,C,D

List<? extends D> list:
D,E,F,G







