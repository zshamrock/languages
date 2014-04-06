## Motivation
Practice is always required to gain an experience with any language. While you are learning the language, the best way when you are more or less confident with it, build the sample application (probably the same one or slightly modified) you've used to build before with different language.

So you know the 'business' of the application, and just focus on the language details to implement it. Unless you have the idea of the project to build, there are some exercises I find useful to implement (is this dojo?) to understand language's features.

There is no **only right way to do it**. The idea is to **experiment** and **explore** the language, get the **feeling** of it. Learn how to **build**, **structure**, and **test** the programs written in the languages of choice.

> **Note:** exercises will be added and improved through the time.

> Try it, even if you think you know how to do it. You may encounter *aha* moments :)

---

## Exercises
### 1. factorial of N
Simply create the program to calculate the factorial of N. The idea is to explore language's features, which are useful to do the task. You can either print the result of one factorial, or print the list of factorials, or accept the user input from stdin/console.

### 2. Read till 42
Read numbers from stdin (one at a time) and print it back to stdout. Stop when you read the number 42.

### 3. Testing
Explore the way how to do testing in your language. Add tests for the *factorial of N* exercise.

### 4. Reading stdin args
Pass arguments, and find out how to read them in the program. You can update the *factorial of N* exercise to provide the number N from stdin.

### 5. Interoperability (i14y)
Interop with the host platform if any. Clojure<->Java, Go<->C, Elixir<->Erlang, etc.

### 6. Access to system properties
Does the language provide the way to access system properties?

### 7. Handling errors/exceptions
What kind of facilities the language provides for handling errors/exceptions?
Worth to try:
  - division by zero,
  - access array element greater than array length,
  - access array element by negative index (if it is not a language feature, of course :))
  - reading non existent file

How can you recover/proper handle these errors in your language?

### 8. Understand core data structures and how to work with them
Every modern language should have maps/hashes, sets, lists/arrays.
Try to:
  - how to add element into the each of the data structure: map, set, list
  - how to remove element from the map, set, list
  - how access element in the map, set, list
  - converts lists/arrays to sets,
  - create map from list, where the key is the element index
  - create set from map, where elements are map's keys

### 9. Concurrency support
Understand what features the language provides to support concurrency.

### 10. Memory management, recursion implication.

### 11. Building simple web application.
Can convert *factorial of N* into web application, where the value of n comes from web request param.
